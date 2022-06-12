package com.liana.DaouShopping.item.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ItemInfo {
    private Item mainItem;
    private ArrayList<String> ItemGroup;
    private ArrayList<Item> addItem;
}
