package day12;

import java.util.*;
import java.util.stream.Collectors;

class InsufficientStockException extends Exception {
    public InsufficientStockException(String message) {
        super(message);
    }
}

class FashionProduct {
    private String productId;
    private String name;
    private double price;
    private int stockQuantity;
    private String category;

    public FashionProduct(String productId, String name, double price, int stockQuantity, String category) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.category = category;
    }

    public String getProductId() { return productId; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }
    public String getCategory() { return category; }

    @Override
    public String toString() {
        return String.format("[%s] %s - $%.2f (Stock: %d) [%s]", productId, name, price, stockQuantity, category);
    }
}

public class ECommerceConsoleApp {
    private static List<FashionProduct> catalog = new ArrayList<>();
    private static Map<FashionProduct, Integer> cart = new HashMap<>();

    public static void initializeCatalog() {
        catalog.add(new FashionProduct("P001", "Silk Saree", 120.00, 10, "Apparel"));
        catalog.add(new FashionProduct("P002", "Designer Kurti", 45.00, 25, "Apparel"));
        catalog.add(new FashionProduct("P003", "Silver Jhumka", 25.00, 50, "Jewelry"));
        catalog.add(new FashionProduct("P004", "Denim Jacket", 75.00, 2, "Apparel"));
    }

    public static void browseCatalog(String categoryFilter, double maxPrice) {
        System.out.println("\n--- Flow 1: Catalog Browsing & Filtering ---");
        List<FashionProduct> filtered = catalog.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(categoryFilter))
                .filter(p -> p.getPrice() <= maxPrice)
                .collect(Collectors.toList());

        if (filtered.isEmpty()) {
            System.out.println("No products found matching your criteria.");
        } else {
            filtered.forEach(System.out::println);
        }
    }

    public static void addToCart(String productId, int quantity) throws InsufficientStockException {
        FashionProduct product = catalog.stream()
                .filter(p -> p.getProductId().equals(productId))
                .findFirst()
                .orElse(null);

        if (product == null) {
            System.out.println("Product not found: " + productId);
            return;
        }

        if (product.getStockQuantity() < quantity) {
            throw new InsufficientStockException("Error: Not enough stock for " + product.getName() + ". Available: " + product.getStockQuantity());
        }

        cart.put(product, cart.getOrDefault(product, 0) + quantity);
        product.setStockQuantity(product.getStockQuantity() - quantity);
        System.out.println("Successfully added " + quantity + " of " + product.getName() + " to cart.");
    }

    public static void checkout() {
        System.out.println("\n--- Flow 2: Checkout & Order Summary ---");
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }

        double totalAmount = 0.0;
        for (Map.Entry<FashionProduct, Integer> entry : cart.entrySet()) {
            FashionProduct p = entry.getKey();
            int qty = entry.getValue();
            double subtotal = p.getPrice() * qty;
            totalAmount += subtotal;
            System.out.printf("- %s (Qty: %d) : $%.2f\n", p.getName(), qty, subtotal);
        }
        System.out.printf("Total Order Amount: $%.2f\n", totalAmount);
        System.out.println("Order placed successfully! Thank you for shopping.");
        cart.clear();
    }

    public static void main(String[] args) {
        initializeCatalog();

        browseCatalog("Apparel", 100.00);

        System.out.println("\n--- Flow 2: Processing Cart Items ---");
        try {
            addToCart("P002", 2);
            addToCart("P004", 5);
        } catch (InsufficientStockException e) {
            System.err.println("[Checkout Exception Caught] " + e.getMessage());
        }

        checkout();
    }
}