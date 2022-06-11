package com.liana.DaouShopping.user.model;

import com.liana.DaouShopping.payments.model.Coupon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class UserInfo {
    private String userId;
    private String userName;
    private String address1;
    private String address2;
    private long point;

    @Builder
    public UserInfo(String userId, String userName,
                    String address1, String address2, long point){
        this.userId = userId;
        this.userName = userName;
        this.address1 = address1;
        this.address2 = address2;
        this.point = point;
    }
}
