package com.example.demo.controller;

import com.example.demo.model.Order;
import com.example.demo.repository.OrderRepository;
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
@RequestMapping("/order")
public class OrderController {

    @Autowired
    public OrderRepository orderRepository;


    @GetMapping("/getOrderById")
    public ResponseEntity<Order> getByIdOrderDetail (@RequestParam String id) {

        Optional<Order> order  = orderRepository.findById(id);

        return new ResponseEntity(order.get(), HttpStatus.OK);
    }


    @PostMapping("/saveOrder")
    public ResponseEntity<Void> saveOrderDetail (@RequestBody Order order) {

        orderRepository.save(order);

        return new ResponseEntity(HttpStatus.OK);
    }


    @PostMapping("/editItem")
    public ResponseEntity<Void> editOrderDetail (@RequestBody Order orderRequest) {

        Optional<Order> order  = orderRepository.findById(orderRequest.getId());

        order.get().setOrderNo(orderRequest.getOrderNo());
        order.get().setId(orderRequest.getId());
        order.get().setQty(orderRequest.getQty());

        orderRepository.save(order.get());

        return new ResponseEntity(HttpStatus.OK);
    }


    @DeleteMapping("/deleteOrder")
    public ResponseEntity<Void> deleteOrderDetail(@RequestParam String id) {

        Optional<Order> item = orderRepository.findById(id);

        orderRepository.delete(item.get());

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/listItem")
    public ResponseEntity<List<Order>> getOrderDetailList(@RequestParam int itemStart, @RequestParam int itemEnd) {


        final List<Order> list = orderRepository.findAll();

        List<Order> finalList = IntStream.range(itemStart, itemEnd).mapToObj(i -> list.get(i)).collect(Collectors.toList());

        return new ResponseEntity(finalList, HttpStatus.OK);
    }



}
