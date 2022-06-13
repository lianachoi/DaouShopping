package com.liana.DaouShopping.payments.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
public class PointHis {
    private long seq;
    private Date tranDate;
    private String userId;
    private long pointCode;
    private double point;
    private String orderId;

    @Builder
    public PointHis(long seq, Date tranDate, String userId,
                    long pointCode, double point, String orderId){
        this.seq = seq;
        this.tranDate = tranDate;
        this.userId = userId;
        this.pointCode = pointCode;
        this.point = point;
        this.orderId = orderId;
    }
}
