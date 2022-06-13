package com.liana.DaouShopping.payments.model;

import lombok.Data;
import lombok.Getter;

@Getter
public class Coupon {
    private long couponId;
    private String couponName;
    private int expDate;
    private double cMin;
    private boolean usePercent;
    private double cPercentLimit;
    private double cPrice;
    private int cPercent;
}