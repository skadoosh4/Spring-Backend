package com.example.demo.product.controller;

import com.example.demo.product.model.ProductDTO;
import com.example.demo.product.model.UpdateCommandPair;
import com.example.demo.product.model.Product;
import com.example.demo.product.repository.ProductRepository;
import com.example.demo.product.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/products")
public class ProductController {

    private final GetAllProducts getAllProducts;
    private final GetProductById getProductById;
    private final CreateProduct createProduct;
    private final UpdateProduct updateProduct;
    private final DeleteProduct deleteProduct;
    private final ProductRepository productRepository;

    @Autowired
    public ProductController(GetAllProducts getAllProducts , GetProductById getProductById , CreateProduct createProduct , UpdateProduct updateProduct , DeleteProduct deleteProduct, ProductRepository productRepository) {
        this.getAllProducts = getAllProducts;
        this.getProductById = getProductById;
        this.createProduct = createProduct;
        this.updateProduct = updateProduct;
        this.deleteProduct = deleteProduct;
        this.productRepository = productRepository;
    }

    @GetMapping
    public ResponseEntity<Iterable<ProductDTO>> getProduct(){
        return getAllProducts.execute(null);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Integer id){
        return getProductById.execute(id);
    }

    @GetMapping("/search/{maxPrice}")
    public ResponseEntity<Iterable<Product>> filterProductByPrice(@PathVariable Double maxPrice){
        return ResponseEntity.ok(productRepository.findProductsWithPriceLessThan(maxPrice));
    }

    @GetMapping("/search")
    public ResponseEntity<Iterable<Product>> searchProducts(@RequestParam(value = "description") String description){
        return ResponseEntity.ok(productRepository.findByDescriptionContaining(description));
    }

    @PostMapping
    public ResponseEntity createProduct(@RequestBody Product product){
        return createProduct.execute(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateProduct(@PathVariable Integer id , @RequestBody Product product){
        UpdateCommandPair command = new UpdateCommandPair(id , product);
        return updateProduct.execute(command);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable Integer id){
        return deleteProduct.execute(id);
    }
}