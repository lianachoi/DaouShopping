package com.liana.DaouShopping.item.controller;

import com.liana.DaouShopping.global.exception.IdNotFoundException;
import com.liana.DaouShopping.item.controller.repository.ItemRepository;
import com.liana.DaouShopping.item.controller.repository.OptionRepository;
import com.liana.DaouShopping.item.model.Item;
import com.liana.DaouShopping.item.model.ItemInfo;
import com.liana.DaouShopping.item.model.Option;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemRepository itemRepository;
    private final OptionRepository optionRepository;

    // get all items
    @GetMapping
    @ApiOperation(value = "Get All Items 모든 상품 조회", notes = "Search products except additional products. \n추가 상품을 제외한 대표 상품만 조회됩니다.")
    public List<Item> getAllItems()
    {
        return itemRepository.findAll();
    }

    // get one item by id
    @GetMapping("/{id}")
    @ApiOperation(value = "Get One By Id 상품 상세 조회", notes = "Search the product's detail and make a group of additional products. \n제품의 상세 정보를 조회하고, 추가 상품들의 그룹을 만듭니다.")
    public ResponseEntity<ItemInfo> getOneById(@PathVariable Long id) {
        List<Item> itemList = itemRepository.findById(id);
        if(itemList.size() == 0)
        {
            throw new IdNotFoundException("상품을");
        }

        ItemInfo itemInfo = new ItemInfo();
        ArrayList<Item> items = new ArrayList<>();
        ArrayList<String> itemGroups = new ArrayList<>();
        for (Item i: itemList) {
            if(i.getItemId() == i.getParentId()){
                itemInfo.setMainItem(i);
            }else{
                items.add(i);
                if (itemGroups.size()>0){
                    if (!itemGroups.contains(i.getPGroupName()))
                        itemGroups.add(i.getPGroupName());
                }else{
                    itemGroups.add(i.getPGroupName());
                }
            }
        }

        itemInfo.setItemGroup(itemGroups);
        itemInfo.setAddItem(items);

        return ResponseEntity.ok(itemInfo);
    }

    // get options of item by id
    @GetMapping("/{id}/options")
    @ApiOperation(value = "Get Option By Id 상품 상세 조회", notes = "Search the product's options. \n제품의 옵션을 조회합니다.")
    public ResponseEntity<List<Option>> getOptionById(@PathVariable Long id) {
        List<Option> option = optionRepository.findById(id);
        if(option.size() == 0)
        {
            throw new IdNotFoundException("상품옵션을");
        }
        return ResponseEntity.ok(option);
    }
}
