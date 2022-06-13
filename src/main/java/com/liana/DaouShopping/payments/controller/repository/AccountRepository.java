package com.liana.DaouShopping.payments.controller.repository;

import com.liana.DaouShopping.payments.model.Account;
import com.liana.DaouShopping.payments.model.AccountHis;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AccountRepository {

    @Insert("INSERT INTO ACCOUNTS(account_id, valance) " +
            "VALUES ( #{accountId}, #{valance})")
    void insert(Account account);

    @Insert("INSERT INTO ACCOUNT_HIS(from_id, to_id, tran_date, tran_amount) " +
            "VALUES ( #{fromId}, #{toId}, #{tranDate}, #{tranAmount})")
    int insertHis(AccountHis accountHis);

    @Select("SELECT * FROM ACCOUNTS WHERE ACCOUNT_ID = #{id}")
    Account findById(String id);

    @Select("SELECT ORDER_ID FROM ORDERS WHERE V_ACCOUNT=#{id}")
    String findOrderByAccount(String id);

    @Update("UPDATE ACCOUNTS SET VALANCE=#{valance} WHERE ACCOUNT_ID=#{accountId}")
    void update(Account account);
}
