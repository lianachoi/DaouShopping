package com.liana.DaouShopping.order.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
public class OrderItem {
    private long itemSeq;
    private String orderId;
    private long itemId;
    private long optionId;
    private int qty;

    @Builder
    public OrderItem(long itemSeq, String orderId,
                     long itemId, long optionId, int qty){
        this.itemSeq = itemSeq;
        this.orderId = orderId;
        this.itemId = itemId;
        this.optionId = optionId;
        this.qty = qty;
    }
}