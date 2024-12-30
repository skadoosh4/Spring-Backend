package com.example.demo.product.repository;

import com.example.demo.product.model.Product;
import com.example.demo.product.model.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product , Integer> {
    @Query("SELECT p FROM Product p WHERE p.price < :maxPrice")
    Iterable<Product> findProductsWithPriceLessThan(@Param("maxPrice") double maxPrice);

    @Query("SELECT new com.example.demo.product.model.ProductDTO(p.name , p.description , p.price) FROM Product p")
    Iterable<ProductDTO> getAllProductDTOs();

    //Query Method to write our own SQL Query
    @Query("SELECT p FROM Product p WHERE p.description LIKE %:description%")
    Iterable<Product> customQueryMethod(@Param(value = "description") String description);

    //Spring Data JPA to have Spring generate it for us
    Iterable<Product> findByDescriptionContaining(String keyword);
}
