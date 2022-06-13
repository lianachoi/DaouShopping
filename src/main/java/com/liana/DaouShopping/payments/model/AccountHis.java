package com.liana.DaouShopping.payments.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class AccountHis {
    private long seq;
    private String fromId;
    private String toId;
    private Date tranDate;
    private double tranAmount;

    @Builder
    public AccountHis(long seq, String fromId, String toId,
                      Date tranDate, double tranAmount){
        this.seq = seq;
        this.fromId = fromId;
        this.toId = toId;
        this.tranDate = tranDate;
        this.tranAmount = tranAmount;
    }
}
