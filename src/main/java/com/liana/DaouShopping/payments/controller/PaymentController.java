package com.liana.DaouShopping.payments.controller;

import com.liana.DaouShopping.global.exception.IdNotFoundException;
import com.liana.DaouShopping.order.controller.repository.OrderItemRepository;
import com.liana.DaouShopping.order.controller.repository.OrderRepository;
import com.liana.DaouShopping.order.model.Order;
import com.liana.DaouShopping.order.model.OrderInfo;
import com.liana.DaouShopping.order.model.OrderItemInfo;
import com.liana.DaouShopping.order.service.OrderService;
import com.liana.DaouShopping.payments.controller.repository.AccountRepository;
import com.liana.DaouShopping.payments.model.Account;
import com.liana.DaouShopping.payments.model.AccountHis;
import com.liana.DaouShopping.user.controller.repository.UserRepository;
import com.liana.DaouShopping.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pay")
@RequiredArgsConstructor
public class PaymentController {
    private final AccountRepository accountRepository;
    private final OrderService orderService;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @GetMapping("/account/{id}")
    public ResponseEntity<Account> getValanceOfAccountById(@PathVariable String id)  {
        Account account = accountRepository.findById(id);
        if(account==null)
        {
            throw new IdNotFoundException("계좌를");
        }
        return ResponseEntity.ok(account);
    }

    @GetMapping("/account/{id}/order")
    public ResponseEntity<OrderInfo> getOrderByVAccount(@PathVariable String id)  {
        String orderId = accountRepository.findOrderByAccount(id);
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    @PutMapping("/account")
    @Transactional
    public ResponseEntity<?> updateValance(@RequestBody AccountHis accountHis){
        accountRepository.insertHis(accountHis);

        Account fromAccount = accountRepository.findById(accountHis.getFromId());
        fromAccount.setValance(fromAccount.getValance()- accountHis.getTranAmount());
        accountRepository.update(fromAccount);
        Account toAccount = accountRepository.findById(accountHis.getToId());
        toAccount.setValance(toAccount.getValance()+ accountHis.getTranAmount());
        accountRepository.update(toAccount);

        String orderId = accountRepository.findOrderByAccount(accountHis.getToId());

        orderRepository.setStatus(2, orderId);

        Order order = orderRepository.findById(orderId);

        if (order.getUserId() != null){
            User getUser = userRepository.findById(order.getUserId());
            userRepository.updatePoint(getUser.getUserId(), getUser.getPoint() + order.getNewPoint());
        }
        return ResponseEntity.ok(302);
    }
}
