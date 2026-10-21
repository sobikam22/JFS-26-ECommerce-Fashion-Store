# 🛍️ JFS-26: E-Commerce Fashion Store

## Summary
An enterprise-grade online apparel store featuring product management, relational database mappings, shopping cart workflows, atomic order processing with live inventory stock deduction, custom exception handling, and RESTful APIs.

---

## 👥 Roles
- **Admin:** Manages catalog items, inventory stock levels, and system configurations.
- **Customer:** Browses products, manages cart items, executes checkout, and tracks orders.
- **Support:** Handles user inquiries and order status updates.

---

## 🏗️ Technical Architecture (N-Tier Backend)
Built on an enterprise-grade N-Tier Spring Boot architecture:

- **Controller Layer (`com.ecommerce.fashion_store.controller`):** Exposes RESTful endpoints (`/api/products`, `/api/orders`), handles HTTP request mapping, and triggers validation rules.
- **Service Layer (`com.ecommerce.fashion_store.service`):** Contains core business logic, transactional order workflows (`@Transactional`), and inventory stock validation.
- **Repository Layer (`com.ecommerce.fashion_store.repository`):** Manages data persistence using Spring Data JPA (`ProductRepository`, `OrderRepository`).
- **DTO Layer (`com.ecommerce.fashion_store.dto`):** Decouples entities from API payloads using request and response contracts (`ProductRequestDTO`, `OrderRequestDTO`, `OrderResponseDTO`).
- **Model Layer (`com.ecommerce.fashion_store.model`):** Core domain entities mapped with JPA relationships (`Product`, `Order`, `OrderItem`).
- **Exception Layer (`com.ecommerce.fashion_store.exception`):** Centralized error handling via `GlobalExceptionHandler` supporting validation failures, resource not found, and custom stock validation errors.

---

## 🛠️ Core Features & Modules
- **Product Inventory Management:** CRUD operations for fashion products with real-time stock tracking.
- **Transactional Order Processing:** Multi-entity persistence (`@OneToMany`, `@ManyToOne`) ensuring atomicity during checkout.
- **Automatic Stock Deduction & Validation:** Real-time stock verification throwing custom `InsufficientStockException` when demand exceeds supply.
- **Validation & Security:** Standardized payload constraints using Jakarta Bean Validation (`@Valid`, `@NotNull`, `@Min`, `@Email`).
- **Global Error Handling:** Structured JSON error responses with proper HTTP status codes (`400 Bad Request`, `404 Not Found`, `500 Internal Server Error`).

---

## ⚙️ Getting Started
1. **Clone the repository:**
   ```bash
   git clone [https://github.com/sobikam22/JFS-26-ECommerce-Fashion-Store.git](https://github.com/sobikam22/JFS-26-ECommerce-Fashion-Store.git)