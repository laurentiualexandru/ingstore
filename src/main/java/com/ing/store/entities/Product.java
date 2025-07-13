package com.ing.store.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "Product")
@Data
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Float price;

    @Column(name = "quantity")
    private Integer quantity;

}
