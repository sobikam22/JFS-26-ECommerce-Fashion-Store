# 🛍️ JFS-26: E-Commerce Fashion Store

## Summary
An online apparel store featuring product variant management (size/color), shopping cart, checkout, order tracking, returns processing, and admin catalog management.

---

## 👥 Roles
* **Admin:** Manages catalog items, inventory variants, orders, and system configurations.
* **Customer:** Browses product variants, manages shopping cart, executes checkout, tracks orders, and initiates returns.
* **Support:** Handles user inquiries, order status updates, and returns processing.

---

## 🏗️ Technical Architecture (N-Tier Backend)
Built on an enterprise-grade N-Tier Spring Boot architecture:

* **Controller Layer (`com.ecommerce.fashion_store.controller`):** Exposes RESTful endpoints, handles HTTP request mapping, and triggers validation rules.
* **Service Layer (`com.ecommerce.fashion_store.service`):** Contains business logic, variant processing, checkout workflows, and transaction management.
* **Repository Layer (`com.ecommerce.fashion_store.repository`):** Manages data persistence and JPA queries.
* **DTO Layer (`com.ecommerce.fashion_store.dto`):** Decouples entities from API payloads (e.g., `ProductRequestDTO`, `ProductResponseDTO`).
* **Model Layer (`com.ecommerce.fashion_store.model`):** Core domain entities mapped to database structures (e.g., `Product`, `Order`).

---

## 🛠️ Core Features & Modules
* **Product Variant Management:** Structured handling for sizes, colors, and stock availability.
* **Validation & Security:** Standardized payload constraints using Jakarta Bean Validation (`jakarta.validation.*`).
* **Order & Cart Processing:** Dynamic cart handling, checkout pipelines, and order tracking.

---

## ⚙️ Getting Started
1. **Clone the repository:**
   ```bash
   git clone [https://github.com/sobikam22/JFS-26-ECommerce-Fashion-Store.git](https://github.com/sobikam22/JFS-26-ECommerce-Fashion-Store.git)