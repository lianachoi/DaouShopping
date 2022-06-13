package com.liana.DaouShopping.payments.model;

import lombok.Data;

@Data
public class Card {
    private String cardId;
    private int yy;
    private int mm;
    private int cvs;
    private int pw;
    private double usageAmount;
}
