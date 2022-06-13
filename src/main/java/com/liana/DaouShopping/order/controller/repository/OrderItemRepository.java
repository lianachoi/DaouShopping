package com.liana.DaouShopping.order.controller.repository;

import com.liana.DaouShopping.order.model.OrderItem;
import com.liana.DaouShopping.order.model.OrderItemInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface OrderItemRepository {

    @Insert("INSERT INTO ORDER_ITEMS(order_id, item_id, option_id, qty) " +
            "VALUES(#{orderId}, #{itemId}, #{optionId}, #{qty})")
    int insert(OrderItem orderItem);


    @Select("SELECT OI.*, MI.ITEM_IMAGE, MI.ITEM_NAME||'('|| O.OPTION_NAME||')' ITEM_NAME,\n" +
            "(MI.PRICE+O.PRICE) PRICE, MI.SALE_PRICE SALE_PRICE\n" +
            "FROM ORDER_ITEMS OI\n" +
            "LEFT OUTER JOIN ITEMS MI ON OI.ITEM_ID = MI.ITEM_ID\n" +
            "LEFT OUTER JOIN OPTIONS O ON OI.OPTION_ID = O.OPTION_ID \n" +
            "WHERE ORDER_ID = #{id}")
    List<OrderItemInfo> findById(String id);
}
