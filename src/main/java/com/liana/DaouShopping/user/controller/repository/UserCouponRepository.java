package com.liana.DaouShopping.user.controller.repository;

import com.liana.DaouShopping.payments.model.Coupon;
import com.liana.DaouShopping.user.model.User;
import com.liana.DaouShopping.user.model.UserCoupon;
import com.liana.DaouShopping.user.model.UserCouponInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserCouponRepository {
    @Select("SELECT C.*, UC.EXP_DATE EXPIRE, UC.USED_FLAG FROM COUPONS C " +
            "INNER JOIN USER_COUPONS UC ON C.COUPON_ID=UC.COUPON_ID" +
            "WHERE USER_ID = #{id} AND USED_FLAG=FALSE")
    List<UserCouponInfo> findById(String id);
}
