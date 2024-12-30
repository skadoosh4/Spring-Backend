package com.example.demo.product.model;

import lombok.Data;

@Data
public class UpdateCommandPair {
    private Integer id;
    private Product product;

    public UpdateCommandPair(Integer id, Product product) {
        this.id = id;
        this.product = product;
    }
}
