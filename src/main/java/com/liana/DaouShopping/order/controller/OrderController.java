package com.liana.DaouShopping.order.controller;

import com.liana.DaouShopping.global.exception.CustomErrorResponse;
import com.liana.DaouShopping.global.exception.IdNotFoundException;
import com.liana.DaouShopping.item.model.Option;
import com.liana.DaouShopping.order.controller.repository.OrderItemRepository;
import com.liana.DaouShopping.order.controller.repository.OrderRepository;
import com.liana.DaouShopping.order.model.Order;
import com.liana.DaouShopping.order.model.OrderInfo;
import com.liana.DaouShopping.order.model.OrderItem;
import com.liana.DaouShopping.order.model.OrderItemInfo;
import com.liana.DaouShopping.user.controller.repository.UserCouponRepository;
import com.liana.DaouShopping.user.controller.repository.UserRepository;
import com.liana.DaouShopping.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Transactional
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserRepository userRepository;
    private final UserCouponRepository userCouponRepository;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderInfo orderInfo)  {
        Random random = new Random();
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyHHmmss");
        Order order = orderInfo.getOrder();
        List<OrderItem> list = orderInfo.getList();
        String dateToStr = dateFormat.format(order.getOrderDate());
        String newOrderId = dateToStr+"-";
        for(int i=0;i<6;i++) {
            newOrderId += random.nextInt(9);
        }
        order.setOrderId(newOrderId);
        int id = orderRepository.insert(order);
        if (id>0){
            for (OrderItem orderItem: list) {
                if (orderItem.getQty() == 0)
                    continue;
                orderItem.setOrderId(newOrderId);
                orderItemRepository.insert(orderItem);
            }
            User getUser = userRepository.findById(order.getUserId());

            // 유저 포인트 사용/적립 , 쿠폰 사용처리
            if(getUser!=null) {
                userRepository.updatePoint(getUser.getUserId(), getUser.getPoint() - order.getUsePoint() + order.getNewPoint());
                userCouponRepository.update(order.getCouponSeq());
            }
            // + 포인트 이력
        }
        return ResponseEntity.ok(order);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderInfo> getOrderById(@PathVariable String id)  {
        Order order = orderRepository.findById(id);
        if(order == null)
        {
            throw new IdNotFoundException("주문 내역을");
        }
        List<OrderItemInfo> items = orderItemRepository.findById(order.getOrderId());

        if(items.size() == 0)
        {
            throw new IdNotFoundException("주문 상세 내역을");
        }
        //결제정보도 추가 필요
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrder(order);
        orderInfo.setItemList(items);
        return ResponseEntity.ok(orderInfo);
    }
}
