package com.example.demo.product.service;

import com.example.demo.ICommand;
import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.exceptions.ProductNotValidException;
import com.example.demo.product.model.Product;
import com.example.demo.product.model.ProductDTO;
import com.example.demo.product.model.UpdateCommandPair;
import com.example.demo.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateProduct implements ICommand<UpdateCommandPair, ResponseEntity> {

    private final ProductRepository productRepository;

    @Autowired
    public UpdateProduct(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @CachePut(value = "productCache" , key = "#command.getId()")
    public ResponseEntity execute(UpdateCommandPair command) {
        Optional<Product> optionalProduct = productRepository.findById(command.getId());
        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException();
        }
        validateProduct(optionalProduct.get());
        command.getProduct().setId(command.getId());
        productRepository.save(command.getProduct());
        return ResponseEntity.ok(new ProductDTO(command.getProduct())); // Cacheput always puts into the cache the value returned by the method
    }

    private void validateProduct(Product product){
        if(product.getName() == null ||  product.getName().isEmpty()){
            throw new ProductNotValidException("Product name cannot be empty");
        }
        if(product.getDescription() == null ||  product.getDescription().isEmpty()){
            throw new ProductNotValidException("Product description cannot be empty");
        }
        if(product.getPrice() <= 0){
            throw new ProductNotValidException("Product price cannot be less than 0");
        }
        if(product.getQuantity() <=0){
            throw new ProductNotValidException("Product quantity cannot be less than 0");
        }
    }
}
