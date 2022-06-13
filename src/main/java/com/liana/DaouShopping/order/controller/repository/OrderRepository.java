package com.liana.DaouShopping.order.controller.repository;

import com.liana.DaouShopping.order.model.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OrderRepository {

    @Insert("INSERT INTO orders(order_id, user_id, order_date, use_point, " +
            "use_coupon, new_point, status, use_card, v_account) " +
            "VALUES ( #{orderId}, #{userId}, #{orderDate}, #{usePoint}," +
            " #{useCoupon}, #{newPoint}, #{status}, #{useCard}, #{vAccount})")
    int insert(Order order);

    @Select("SELECT * FROM ORDERS WHERE ORDER_ID = #{id}")
    Order findById(String id);
}

