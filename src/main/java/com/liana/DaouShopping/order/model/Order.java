package com.liana.DaouShopping.order.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
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
    private String userName;
    private String address1;
    private String address2;

    @Builder
    public Order(String orderId, String userId, Date orderDate,
                 long usePoint, double useCoupon, long couponSeq, double newPoint,
                 int status, boolean useCard, String vAccount,
                 String userName, String address1, String address2){
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
        this.userName = userName;
        this.address1 = address1;
        this.address2 = address2;
    }
}
