import java.util.*;

public class InventoryManager {
    public static void main(String[] args) {
        System.out.println("=== Day 7: Collections Framework (Lists & Sets) ===");

        // 1. ArrayList demonstration
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("P001", "Wireless Mouse", 25.99));
        productList.add(new Product("P002", "Mechanical Keyboard", 75.50));
        productList.add(new Product("P003", "Gaming Monitor", 299.99));
        productList.add(new Product("P001", "Wireless Mouse", 25.99)); // Duplicate allowed in List

        System.out.println("\n--- Product List (ArrayList) ---");
        for (Product p : productList) {
            System.out.println(p);
        }

        // 2. HashSet demonstration (removes duplicates based on hashCode/equals or unique entries)
        Set<String> categorySet = new HashSet<>();
        categorySet.add("Electronics");
        categorySet.add("Apparel");
        categorySet.add("Electronics"); // Duplicate, won't be added

        System.out.println("\n--- Unique Categories (HashSet) ---");
        System.out.println(categorySet);

        // 3. TreeSet demonstration (sorted by natural ordering via Comparable)
        Set<Product> sortedProducts = new TreeSet<>(productList);

        System.out.println("\n--- Sorted Products by Price (TreeSet) ---");
        for (Product p : sortedProducts) {
            System.out.println(p);
        }
    }
}