package day13;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

// Sample Product Domain Model
class Product {
    private String name;
    private double price;
    private String category;
    private boolean inStock;

    public Product(String name, double price, String category, boolean inStock) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.inStock = inStock;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
    public boolean isInStock() { return inStock; }

    @Override
    public String toString() {
        return String.format("Product[name=%s, price=%.2f, category=%s, inStock=%b]", name, price, category, inStock);
    }
}

public class FilterEngine {

    // Composed Predicate filters
    public static Predicate<Product> priceLessThan(double maxPrice) {
        return p -> p.getPrice() < maxPrice;
    }

    public static Predicate<Product> categoryEquals(String category) {
        return p -> p.getCategory().equalsIgnoreCase(category);
    }

    public static Predicate<Product> inStockOnly() {
        return Product::isInStock; // Method reference
    }

    public static void main(String[] args) {
        List<Product> catalog = Arrays.asList(
                new Product("Silk Saree", 2500.0, "Apparel", true),
                new Product("Cotton Kurti", 800.0, "Apparel", true),
                new Product("Denim Jeans", 1500.0, "Apparel", false),
                new Product("Silver Jhumka", 450.0, "Jewelry", true)
        );

        // 1. Predicate Composition: In-stock Apparel under 2000
        Predicate<Product> vibrantApparelFilter = inStockOnly()
                .and(categoryEquals("Apparel"))
                .and(priceLessThan(2000.0));

        System.out.println("--- Filtered Products ---");
        List<Product> filtered = catalog.stream()
                .filter(vibrantApparelFilter)
                .collect(Collectors.toList());

        // 2. Consumer for Printing (Method Reference)
        Consumer<Product> printer = System.out::println;
        filtered.forEach(printer);

        // 3. Function Chain for Transformation (Uppercase product names with pricing tag)
        Function<Product, String> nameTransformer = p -> p.getName().toUpperCase();
        Function<String, String> formatTag = name -> "ITEM: " + name;
        Function<Product, String> finalFormatter = nameTransformer.andThen(formatTag);

        System.out.println("\n--- Transformed Product Names ---");
        catalog.stream()
                .map(finalFormatter)
                .forEach(System.out::println);

        // 4. Supplier for Default Fallback Product
        Supplier<Product> defaultProductSupplier = () -> new Product("Default Tee", 499.0, "Apparel", true);

        System.out.println("\n--- Supplier Fallback ---");
        Product fallback = catalog.stream()
                .filter(p -> p.getPrice() > 5000.0)
                .findFirst()
                .orElseGet(defaultProductSupplier);

        System.out.println(fallback);
    }
}