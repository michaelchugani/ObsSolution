package com.example.demo.controller;


import com.example.demo.model.Item;
import com.example.demo.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    public ItemRepository itemRepository;


    @GetMapping("/getItemById")
    public ResponseEntity<Item> getByIdItemDetail (@RequestParam String id) {

        Optional<Item> item  = itemRepository.findById(id);

        return new ResponseEntity(item.get(), HttpStatus.OK);
    }


    @PostMapping("/saveItem")
    public ResponseEntity<Void> saveItemDetail (@RequestBody Item item) {

        itemRepository.save(item);

        return new ResponseEntity(HttpStatus.OK);
    }


    @PostMapping("/editItem")
    public ResponseEntity<Void> editItemDetail (@RequestBody Item itemRequest) {

        Optional<Item> item  = itemRepository.findById(itemRequest.getId());

        item.get().setName(itemRequest.getName());
        item.get().setPrice(itemRequest.getPrice());

        itemRepository.save(item.get());

        return new ResponseEntity(HttpStatus.OK);
    }


    @DeleteMapping("/deleteItem")
    public ResponseEntity<Void> deleteItemDetail(@RequestParam String id) {

        Optional<Item> item = itemRepository.findById(id);

        itemRepository.delete(item.get());

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/listItem")
        public ResponseEntity<List<Item>> getItemDetailList(@RequestParam int itemStart, @RequestParam int itemEnd) {


        final List<Item> list = itemRepository.findAll();

        List<Item> finalList = IntStream.range(itemStart, itemEnd).mapToObj(i -> list.get(i)).collect(Collectors.toList());

        return new ResponseEntity(finalList, HttpStatus.OK);
    }




}
