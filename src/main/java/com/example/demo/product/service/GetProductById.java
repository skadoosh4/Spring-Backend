package com.example.demo.product.service;

import com.example.demo.IQuery;
import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.product.model.Product;
import com.example.demo.product.model.ProductDTO;
import com.example.demo.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetProductById implements IQuery<Integer , ProductDTO> {

    private final ProductRepository productRepository;

    @Autowired
    public GetProductById(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Cacheable("productCache")
    public ResponseEntity<ProductDTO> execute(Integer input) {
        Optional<Product> product = productRepository.findById(input);
        if (product.isPresent()){
            return ResponseEntity.ok(new ProductDTO(product.get()));
        }
        throw new ProductNotFoundException();
    }
}
