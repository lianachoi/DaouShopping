package com.liana.DaouShopping.user.controller.repository;

import com.liana.DaouShopping.item.model.Item;
import com.liana.DaouShopping.user.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface UserRepository {
    @Select("SELECT * FROM USERS WHERE USER_ID = #{id} ")
    User findById(String id);
}
