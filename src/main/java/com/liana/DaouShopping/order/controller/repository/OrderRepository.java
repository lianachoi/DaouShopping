package com.liana.DaouShopping.order.controller.repository;

import com.liana.DaouShopping.order.model.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface OrderRepository {

    @Insert("INSERT INTO orders(order_id, user_id, order_date, use_point, " +
            "use_coupon, coupon_seq, new_point, status, use_card, v_account, user_name, address1, address2) " +
            "VALUES ( #{orderId}, #{userId}, #{orderDate}, #{usePoint}," +
            " #{useCoupon}, #{couponSeq}, #{newPoint}, #{status}, #{useCard}, #{vAccount}, #{userName}, #{address1}, #{address2})")
    int insert(Order order);

    @Select("SELECT * FROM ORDERS WHERE ORDER_ID = #{id}")
    Order findById(String id);

    @Update("UPDATE ORDERS SET STATUS=#{status} WHERE ORDER_ID=#{id}")
    void setStatus(int status, String id);
}

