package com.liana.DaouShopping.order.model;

import lombok.Builder;
import lombok.Data;

@Data
public class OrderItemInfo {
    private long itemSeq;
    private String orderId;
    private long itemId;
    private long optionId;
    private int qty;

    private String itemImage;
    private String itemName;
    private double price;
    private double salePrice;
}
