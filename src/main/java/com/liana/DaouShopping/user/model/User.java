package com.liana.DaouShopping.user.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class User {
    private String userId;
    private String userPw;
    private String userName;
    private String address1;
    private String address2;
    private long point;

    @Builder
    public User(String userId, String userPw, String userName,
                String address1, String address2, long point){
        this.userId = userId;
        this.userPw = userPw;
        this.userName = userName;
        this.address1 = address1;
        this.address2 = address2;
        this.point = point;
    }
}
