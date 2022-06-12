package com.liana.DaouShopping.order.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
public class Order {
    private String orderId;
    private String userId;
    private Date orderDate;
    private long usePoint;
    private double useCoupon;
    private long couponSeq;
    private double newPoint;
    private int status;
    private boolean useCard;
    private String vAccount;

    @Builder
    public Order(String orderId, String userId, Date orderDate,
                 long usePoint, double useCoupon, long couponSeq, double newPoint,
                 int status, boolean useCard, String vAccount){
        this.orderId = orderId;
        this.userId = userId;
        this.orderDate = orderDate;
        this.usePoint = usePoint;
        this.useCoupon = useCoupon;
        this.couponSeq = couponSeq;
        this.newPoint = newPoint;
        this.status = status;
        this.useCard = useCard;
        this.vAccount = vAccount;
    }
}
