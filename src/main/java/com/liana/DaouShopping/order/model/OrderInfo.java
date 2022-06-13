package com.liana.DaouShopping.order.model;

import lombok.Data;

import java.util.List;

@Data
public class OrderInfo {
    private Order order;
    private List<OrderItem> list;
    private List<OrderItemInfo> itemList;
}
