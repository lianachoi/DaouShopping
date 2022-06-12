package com.liana.DaouShopping.user.model;

import lombok.Builder;
import lombok.Data;

@Data
public class UserCoupon {
    private long seq;
    private String coupon_id;
    private String user_id;
    private String used_flag;

    @Builder
    public UserCoupon(long seq, String coupon_id, String user_id, String used_flag){
        this.seq = seq;
        this.coupon_id = coupon_id;
        this.user_id = user_id;
        this.used_flag = used_flag;
    }
}
