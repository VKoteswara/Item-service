package com.nisum.eureka.client.application.controllers;


import com.nisum.eureka.client.application.domain.Item;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class ItemController {
    private static Map<String, List<Item>> goodsDB = new HashMap<String, List<Item>>();
    static {
        List<Item> electrinicalItems = new ArrayList<Item>();
        Item itemTv= new Item("Tv","Sony");
        electrinicalItems.add(itemTv);
        Item itemMobile= new Item("Mobile","Sony");
        electrinicalItems.add(itemMobile);
        goodsDB.put("Electrical",electrinicalItems);

        List<Item>  automobileItems = new ArrayList<>();
        Item audiCar = new Item("car","Audi");
        automobileItems.add(audiCar);
        Item tractor = new Item("tractor","TechMahendra");
        automobileItems.add(tractor);
        goodsDB.put("Automobiles",automobileItems);

    }

    @GetMapping("/getitemsdetailsforgood/{goodsName}")
    public ResponseEntity<Item> getItems(@PathVariable("goodsName") String goodsName){

        List<Item> items = goodsDB.get(goodsName);
        System.out.println(items);
        Optional<List<Item>> items1 = Optional.ofNullable(items);
        items1.orElseThrow(()->new IllegalArgumentException("No data found"));
        return new ResponseEntity(items1.get(), HttpStatus.OK);
    }
}
