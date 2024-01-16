package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_item")
@Builder
@ToString
@Getter
@Setter
public class Order implements Serializable {

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "orderNo")
    private String orderNo;
    @Column(name = "itemId")
    private String itemId;
    @Column(name = "qty")
    private Integer qty;

}
