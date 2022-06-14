package com.liana.DaouShopping.order.controller;


import com.liana.DaouShopping.order.controller.repository.OrderItemRepository;
import com.liana.DaouShopping.order.controller.repository.OrderRepository;
import com.liana.DaouShopping.order.model.Order;
import com.liana.DaouShopping.order.model.OrderInfo;
import com.liana.DaouShopping.order.model.OrderItem;
import com.liana.DaouShopping.order.service.OrderService;
import com.liana.DaouShopping.payments.controller.repository.AccountRepository;
import com.liana.DaouShopping.payments.model.Account;
import com.liana.DaouShopping.payments.model.AccountHis;
import com.liana.DaouShopping.payments.service.PaymentService;
import com.liana.DaouShopping.user.controller.repository.UserCouponRepository;
import com.liana.DaouShopping.user.controller.repository.UserRepository;
import com.liana.DaouShopping.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Transactional
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderService orderService;
    private final UserRepository userRepository;
    private final UserCouponRepository userCouponRepository;
    private final PaymentService paymentService;
    private final AccountRepository accountRepository;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderInfo orderInfo) {
        //화면에서 받은 정보
        Order order = orderInfo.getOrder();
        List<OrderItem> list = orderInfo.getList();
        //foreign key 때문에 없으면 null 로 넣어 줘야 함
        if (order.getUserId().equals("")) {
            order.setUserId(null);
        }
        //주문번호 만드는 부분
        Random random = new Random();
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyHHmmss");
        String dateToStr = dateFormat.format(order.getOrderDate());
        String newOrderId = "";
        for (int i = 0; i < 6; i++) {
            newOrderId += random.nextInt(9);
        }
        //결제 계좌번호는 생성한 난수로.
        if (!order.isUseCard()) {
            order.setVAccount(newOrderId);
            paymentService.createVAccount(newOrderId);
        }
        //주문번호 set, 계좌번호 set
        order.setOrderId(dateToStr + "-" + newOrderId);
        //주문 생성
        orderRepository.insert(order);
        for (OrderItem orderItem : list) {
            if (orderItem.getQty() == 0)
                continue;
            orderItem.setOrderId(order.getOrderId());
            orderItemRepository.insert(orderItem);
        }

        //주문할때 로그인한 유저 조회
        User getUser = userRepository.findById(order.getUserId());
        // 유저 포인트 사용/적립 , 쿠폰 사용처리
        if (getUser != null) {
            userRepository.updatePoint(getUser.getUserId(), getUser.getPoint() - order.getUsePoint());
            userCouponRepository.update(true, order.getCouponSeq());
        }
        // + 포인트 이력
        if (order.getStatus() == 2) { // 결제 완료면 포인트 적립 해줌
            getUser = userRepository.findById(order.getUserId());
            userRepository.updatePoint(getUser.getUserId(), getUser.getPoint() + order.getNewPoint());
        }
        return ResponseEntity.ok(order);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Order> cancelOrder(@PathVariable String id) {
        Order order = orderRepository.findById(id);
        User user = null;
        if (order.getUserId() != null) {
            user = userRepository.findById(order.getUserId());
        }
        if (user != null) {
            userRepository.updatePoint(user.getUserId(), user.getPoint() + order.getUsePoint());
            userCouponRepository.update(false, order.getCouponSeq());
        }
        orderRepository.setStatus(3, order.getOrderId());
        if (order.getStatus() == 2) {
            //환불로직 넣어야 함.
            AccountHis accountHis = accountRepository.findHisByAccount(order.getVAccount());
            Account userAccount = accountRepository.findById(accountHis.getFromId());
            userAccount.setValance(userAccount.getValance() + accountHis.getTranAmount());
            accountRepository.update(userAccount);
            accountHis = AccountHis.builder().fromId(accountHis.getToId())
                    .toId(accountHis.getFromId()).tranAmount(accountHis.getTranAmount())
                    .tranDate(new Date()).build();
            accountRepository.insertHis(accountHis);
            accountRepository.delete(order.getVAccount());
            if (user != null) {
                user = userRepository.findById(order.getUserId());
                userRepository.updatePoint(user.getUserId(), user.getPoint() - order.getNewPoint());
            }
        }
        order = orderRepository.findById(id);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderInfo> getOrderById(@PathVariable String id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }
}
