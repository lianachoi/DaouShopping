package com.liana.DaouShopping.global.exception;

public class IdNotFoundException extends RuntimeException{
    public IdNotFoundException()
    {
        super("Id Not Found");
    }
    public IdNotFoundException(String id)
    {
        super(id + " 찾을 수 없습니다.");
    }
}
