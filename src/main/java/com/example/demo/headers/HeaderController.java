package com.example.demo.headers;


import com.example.demo.product.model.Product;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeaderController {

    @GetMapping(value = "/header" , produces = {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Product> getProduct() {
        Product product = new Product();
        product.setId(1);
        product.setName("myProduct");
        product.setDescription("greatest product ever");

        return ResponseEntity.ok(product);
    }
}
