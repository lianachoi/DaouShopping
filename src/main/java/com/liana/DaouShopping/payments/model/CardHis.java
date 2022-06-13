package com.liana.DaouShopping.payments.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
public class CardHis {
    private long seq;
    private String cardId;
    private Date tranDate;
    private double tranAmount;
    private String usageInfo;

    @Builder
    public CardHis(long seq, String cardId, Date tranDate,
                   double tranAmount, String usageInfo){
        this.seq = seq;
        this.cardId = cardId;
        this.tranDate = tranDate;
        this.tranAmount = tranAmount;
        this.usageInfo = usageInfo;
    }
}
