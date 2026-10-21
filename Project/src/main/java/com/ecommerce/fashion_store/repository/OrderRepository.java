package com.ecommerce.fashion_store.repository;

import com.ecommerce.fashion_store.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // Find orders by customer email
    List<Order> findByCustomerEmail(String customerEmail);

    // Find orders by status (e.g., PENDING, COMPLETED, CANCELLED)
    List<Order> findByStatus(String status);
}