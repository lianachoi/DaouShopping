package com.liana.DaouShopping.order.service;

import com.liana.DaouShopping.global.exception.IdNotFoundException;
import com.liana.DaouShopping.order.controller.repository.OrderItemRepository;
import com.liana.DaouShopping.order.controller.repository.OrderRepository;
import com.liana.DaouShopping.order.model.Order;
import com.liana.DaouShopping.order.model.OrderInfo;
import com.liana.DaouShopping.order.model.OrderItemInfo;
import com.liana.DaouShopping.payments.controller.repository.AccountRepository;
import com.liana.DaouShopping.payments.model.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Transactional
    public OrderInfo getOrderById(String id) {
        Order order = orderRepository.findById(id);
        if (order == null) {
            throw new IdNotFoundException("주문 내역을");
        }
        List<OrderItemInfo> items = orderItemRepository.findById(order.getOrderId());

        if (items.size() == 0) {
            throw new IdNotFoundException("주문 상세 내역을");
        }
        //결제정보도 추가 필요
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrder(order);
        orderInfo.setItemList(items);
        return orderInfo;
    }
}
