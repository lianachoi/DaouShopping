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
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "Get Valance Of Account By Id / 계좌번호의 잔액 확인")
    public ResponseEntity<Account> getValanceOfAccountById(@PathVariable String id)  {
        Account account = accountRepository.findById(id);
        if(account==null)
        {
            throw new IdNotFoundException("계좌를");
        }
        return ResponseEntity.ok(account);
    }

    @GetMapping("/account/{id}/order")
    @ApiOperation(value = "Get Order By Virtual Account / 가상계좌로 주문 조회")
    public ResponseEntity<OrderInfo> getOrderByVAccount(@PathVariable String id)  {
        String orderId = accountRepository.findOrderByAccount(id);
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    @PutMapping("/account")
    @Transactional
    @ApiOperation(value = "Deposit / 입금"
            , notes = "Make a transfer and a record. Update order & user status." +
            "\n계좌 이체를 하고 이체 기록을 생성합니다. 주문과 유저정보를 업데이트합니다.")
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
