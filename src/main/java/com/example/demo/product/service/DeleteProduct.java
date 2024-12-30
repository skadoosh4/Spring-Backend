package com.example.demo.product.service;

import com.example.demo.ICommand;
import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.product.model.Product;
import com.example.demo.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteProduct implements ICommand<Integer , ResponseEntity> {

    private final ProductRepository productRepository;

    @Autowired
    public DeleteProduct(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity execute(Integer id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()){
            throw new ProductNotFoundException();
        }
        productRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
