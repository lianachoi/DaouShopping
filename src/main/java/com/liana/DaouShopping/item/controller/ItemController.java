package com.liana.DaouShopping.item.controller;

import com.liana.DaouShopping.global.exception.IdNotFoundException;
import com.liana.DaouShopping.item.controller.repository.ItemRepository;
import com.liana.DaouShopping.item.controller.repository.OptionRepository;
import com.liana.DaouShopping.item.model.Item;
import com.liana.DaouShopping.item.model.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemRepository itemRepository;
    private final OptionRepository optionRepository;

    // get all items
    @GetMapping
    public List<Item> getAllItems()
    {
        return itemRepository.findAll();
    }

    // get one item by id
    @GetMapping("/{id}")
    public ResponseEntity<List<Item>> getOneById(@PathVariable Long id) {
        List<Item> item = itemRepository.findById(id);
        if(item.size() == 0)
        {
            throw new IdNotFoundException("상품을");
        }
        return ResponseEntity.ok(item);
    }

    // get options of item by id
    @GetMapping("/{id}/options")
    public ResponseEntity<List<Option>> getOptionById(@PathVariable Long id) {
        List<Option> option = optionRepository.findById(id);
        if(option.size() == 0)
        {
            throw new IdNotFoundException("상품옵션을");
        }
        return ResponseEntity.ok(option);
    }
}
