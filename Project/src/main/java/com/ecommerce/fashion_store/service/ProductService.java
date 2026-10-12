package com.ecommerce.fashion_store.service;

import com.ecommerce.fashion_store.dto.ProductRequestDTO;
import com.ecommerce.fashion_store.dto.ProductResponseDTO;
import com.ecommerce.fashion_store.exception.ResourceNotFoundException;
import com.ecommerce.fashion_store.model.Product;
import com.ecommerce.fashion_store.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public ProductResponseDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        return mapToResponseDTO(product);
    }

    public ProductResponseDTO saveProduct(ProductRequestDTO requestDTO) {
        Product product = mapToEntity(requestDTO);
        Product savedProduct = productRepository.save(product);
        return mapToResponseDTO(savedProduct);
    }

    // Helper mapping methods
    private ProductResponseDTO mapToResponseDTO(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory(),
                product.getSize(),
                product.getColor(),
                product.getStockQuantity()
        );
    }

    private Product mapToEntity(ProductRequestDTO requestDTO) {
        Product product = new Product();
        product.setName(requestDTO.getName());
        product.setDescription(requestDTO.getDescription());
        product.setPrice(requestDTO.getPrice());
        product.setCategory(requestDTO.getCategory());
        product.setSize(requestDTO.getSize());
        product.setColor(requestDTO.getColor());
        product.setStockQuantity(requestDTO.getStockQuantity());
        return product;
    }
}