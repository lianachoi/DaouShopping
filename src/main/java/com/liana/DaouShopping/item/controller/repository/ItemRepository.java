package com.liana.DaouShopping.item.controller.repository;

import com.liana.DaouShopping.item.model.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ItemRepository {
    @Select("select * from items where item_id = parent_Id")
    List<Item> findAll();
    @Select("SELECT * FROM items WHERE parent_id = #{id}")
    List<Item> findById(long id);

}
