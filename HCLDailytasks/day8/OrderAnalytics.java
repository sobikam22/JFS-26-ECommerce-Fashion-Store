import java.util.*;
import java.util.stream.Collectors;

public class OrderAnalytics {
    public static void main(String[] args) {
        System.out.println("=== Day 8: Maps & Java 8 Streams ===");

        // 1. HashMap demonstration (Key-Value pairing)
        Map<String, String> customerDirectory = new HashMap<>();
        customerDirectory.put("ORD-501", "Sobika M.");
        customerDirectory.put("ORD-502", "Joshua");
        customerDirectory.put("ORD-503", "Ananya R.");

        System.out.println("\n--- Customer Directory (HashMap) ---");
        for (Map.Entry<String, String> entry : customerDirectory.entrySet()) {
            System.out.println("Order ID: " + entry.getKey() + " -> Customer: " + entry.getValue());
        }

        // 2. Stream API demonstration
        List<Order> orders = Arrays.asList(
                new Order("ORD-501", "Sobika M.", 1500.0, "DELIVERED"),
                new Order("ORD-502", "Joshua", 450.0, "PENDING"),
                new Order("ORD-503", "Ananya R.", 1200.0, "DELIVERED"),
                new Order("ORD-504", "Karthik P.", 300.0, "CANCELLED"),
                new Order("ORD-505", "Priya S.", 2200.0, "DELIVERED")
        );

        // Filter orders with amount > 500 using Streams
        System.out.println("\n--- Filtered Orders (Amount > $500) ---");
        List<Order> highValueOrders = orders.stream()
                .filter(o -> o.getAmount() > 500.0)
                .collect(Collectors.toList());

        highValueOrders.forEach(System.out::println);

        // Calculate total revenue of delivered orders using mapToDouble & sum
        double totalDeliveredRevenue = orders.stream()
                .filter(o -> "DELIVERED".equals(o.getStatus()))
                .mapToDouble(Order::getAmount)
                .sum();

        System.out.println("\nTotal Revenue from Delivered Orders: $" + totalDeliveredRevenue);
    }
}