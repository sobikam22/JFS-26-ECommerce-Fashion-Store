package com.ecommerce.fashion_store.repository;

import com.ecommerce.fashion_store.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Find products by category (e.g., "Men", "Women")
    List<Product> findByCategory(String category);

    // Find products within a specific price range
    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    // Search products by name keyword (case-insensitive)
    List<Product> findByNameContainingIgnoreCase(String name);
}