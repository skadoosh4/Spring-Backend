package com.example.demo.product.service;

import com.example.demo.ICommand;
import com.example.demo.exceptions.ProductNotValidException;
import com.example.demo.product.model.Product;
import com.example.demo.product.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class CreateProduct implements ICommand<Product , ResponseEntity> {

    private final ProductRepository productRepository;
    private static final Logger logger = LoggerFactory.getLogger(CreateProduct.class);

    @Autowired
    public CreateProduct(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity execute(Product product) {
        logger.info("Executing " + getClass() + "with " + product.toString());
        validateProduct(product);
        productRepository.save(product);
        return ResponseEntity.ok().build();
    }

    private void validateProduct(Product product){
        if(product.getName() == null ||  product.getName().isEmpty()){
            logger.error("Product not valid exception was thrown (invalid name) " + product.toString());
            throw new ProductNotValidException("Product name cannot be empty");
        }
        if(product.getDescription() == null ||  product.getDescription().isEmpty()){
            logger.error("Product not valid exception was thrown (invalid description)" + product.toString());
            throw new ProductNotValidException("Product description cannot be empty");
        }
        if(product.getPrice() <= 0.0){
            logger.error("Product not valid exception was thrown (invalid price)" + product.toString());
            throw new ProductNotValidException("Product price cannot be less than 0");
        }
        if(product.getQuantity() <=0){
            logger.error("Product not valid exception was thrown (invalid quantity)" + product.toString());
            throw new ProductNotValidException("Product quantity cannot be less than 0");
        }
    }
}
