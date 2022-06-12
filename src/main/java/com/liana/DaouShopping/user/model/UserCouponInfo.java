package com.liana.DaouShopping.user.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class UserCouponInfo {
    private long seq;
    private long couponId;
    private String couponName;
    private double cMin;
    private boolean usePercent;
    private double cPercentLimit;
    private double cPrice;
    private int cPercent;

    private String userId;
    private Date expire;
    private boolean usedFlag;

    private double calValue;

    @Builder
    public UserCouponInfo(long seq, long couponId, String couponName, double cMin, boolean usePercent,
                      double cPercentLimit, double cPrice, int cPercent, String userId,
                          Date expire, boolean usedFlag, double calValue){
        this.seq = seq;
        this.couponId = couponId;
        this.couponName = couponName;
        this.cMin = cMin;
        this.usePercent = usePercent;
        this.cPercentLimit = cPercentLimit;
        this.cPrice = cPrice;
        this.cPercent = cPercent;
        this.userId = userId;
        this.expire = expire;
        this.usedFlag = usedFlag;
        this.calValue = calValue;
    }
}
