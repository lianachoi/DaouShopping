package com.liana.DaouShopping.order.controller;

import com.liana.DaouShopping.global.exception.CustomErrorResponse;
import com.liana.DaouShopping.global.exception.IdNotFoundException;
import com.liana.DaouShopping.order.controller.repository.OrderRepository;
import com.liana.DaouShopping.order.model.Order;
import com.liana.DaouShopping.user.controller.repository.UserCouponRepository;
import com.liana.DaouShopping.user.controller.repository.UserRepository;
import com.liana.DaouShopping.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Random;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final UserCouponRepository userCouponRepository;

    @PostMapping
    public ResponseEntity<Integer> createOrder(@RequestBody Order order)  {
        Random random = new Random();
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");

        String dateToStr = dateFormat.format(order.getOrderDate());
        String newOrderId = dateToStr+"-";
        for(int i=0;i<6;i++) {
            newOrderId += random.nextInt(9);
        }
        order.setOrderId(newOrderId);
        int id = orderRepository.insert(order);
        if (id>0){
            User getUser = userRepository.findById(order.getUserId());

            // 유저 포인트 사용/적립 , 쿠폰 사용처리
            if(getUser!=null) {
                userRepository.updatePoint(getUser.getUserId(), getUser.getPoint() - order.getUsePoint() + order.getNewPoint());
                userCouponRepository.update(order.getCouponSeq());
            }
            // + 포인트 이력
        }
        userCouponRepository.update(order.getCouponSeq());
        return ResponseEntity.ok(id);
    }
}
