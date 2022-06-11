package com.liana.DaouShopping.item.model;

import lombok.Getter;

@Getter
public class Item {
    private long itemId;
    private String itemName;
    private double price;
    private double salePrice;
    private String itemImage;
    private String descImage;
    private String itemDesc;
    private long parentId;
    private String pGroupName;
    private int limitQty;
}
