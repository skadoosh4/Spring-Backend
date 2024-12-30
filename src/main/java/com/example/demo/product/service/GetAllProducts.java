package com.example.demo.product.service;


import com.example.demo.IQuery;
import com.example.demo.product.model.ProductDTO;
import com.example.demo.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetAllProducts implements IQuery<Void , Iterable<ProductDTO>> {

    private final ProductRepository productRepository;

    @Autowired
    public GetAllProducts(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<Iterable<ProductDTO>> execute(Void input) {
//        List<ProductDTO> productDTOS = productRepository
//                .findAll()
//                .stream()
//                .map(ProductDTO::new)
//                .toList();
        Iterable<ProductDTO> productDTOS = productRepository.getAllProductDTOs();
        return ResponseEntity.ok(productDTOS);
    }
}
