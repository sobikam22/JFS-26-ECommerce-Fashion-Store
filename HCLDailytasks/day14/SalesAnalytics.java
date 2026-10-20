package day14;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Order {
    private String orderId;
    private String customerName;
    private String category;
    private double amount;
    private String status;
    private List<String> products;

    public Order(String orderId, String customerName, String category, double amount, String status, List<String> products) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.category = category;
        this.amount = amount;
        this.status = status;
        this.products = products;
    }

    public String getOrderId() { return orderId; }
    public String getCustomerName() { return customerName; }
    public String getCategory() { return category; }
    public double getAmount() { return amount; }
    public String getStatus() { return status; }
    public List<String> getProducts() { return products; }
}

public class SalesAnalytics {

    public static List<Order> generateOrders() {
        List<String> categories = Arrays.asList("Apparel", "Jewelry", "Footwear", "Accessories");
        List<String> customers = Arrays.asList("Alice", "Bob", "Charlie", "Diana", "Evan", "Fiona");
        List<String> statuses = Arrays.asList("COMPLETED", "PENDING", "CANCELLED");
        List<List<String>> productPool = Arrays.asList(
                Arrays.asList("Silk Saree", "Designer Kurti"),
                Arrays.asList("Silver Jhumka", "Pearl Necklace"),
                Arrays.asList("Embroidered Mojaris", "Stilettos"),
                Arrays.asList("Leather Handbag", "Silk Scarf")
        );

        Random random = new Random(42);
        return IntStream.range(0, 1000)
                .mapToObj(i -> {
                    String cat = categories.get(random.nextInt(categories.size()));
                    String cust = customers.get(random.nextInt(customers.size()));
                    String stat = statuses.get(random.nextInt(statuses.size()));
                    double amt = 25.0 + (450.0 - 25.0) * random.nextDouble();
                    List<String> prods = productPool.get(random.nextInt(productPool.size()));
                    return new Order("ORD" + (1000 + i), cust, cat, amt, stat, prods);
                })
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Order> orders = generateOrders();

        Map<String, Double> revenueByCategory = orders.stream()
                .collect(Collectors.groupingBy(Order::getCategory, Collectors.summingDouble(Order::getAmount)));
        System.out.println("=== 1. Revenue by Category ===");
        revenueByCategory.forEach((cat, rev) -> System.out.printf("%s: $%.2f\n", cat, rev));

        List<Map.Entry<String, Double>> topCustomers = orders.stream()
                .collect(Collectors.groupingBy(Order::getCustomerName, Collectors.summingDouble(Order::getAmount)))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(3)
                .collect(Collectors.toList());
        System.out.println("\n=== 2. Top 3 Customers ===");
        topCustomers.forEach(e -> System.out.printf("%s: $%.2f\n", e.getKey(), e.getValue()));

        Map<Boolean, List<Order>> partitionedByStatus = orders.stream()
                .collect(Collectors.partitioningBy(o -> "COMPLETED".equals(o.getStatus())));
        System.out.println("\n=== 3. Order Partitioning (Completed vs Rest) ===");
        System.out.println("Completed Orders Count: " + partitionedByStatus.get(true).size());
        System.out.println("Other Orders Count: " + partitionedByStatus.get(false).size());

        DoubleSummaryStatistics stats = orders.stream()
                .collect(Collectors.summarizingDouble(Order::getAmount));
        System.out.println("\n=== 4. Basket Size Statistics ===");
        System.out.printf("Average Order Value: $%.2f (Total Revenue: $%.2f)\n", stats.getAverage(), stats.getSum());

        String commaJoinedProducts = orders.stream()
                .flatMap(o -> o.getProducts().stream())
                .distinct()
                .collect(Collectors.joining(", "));
        System.out.println("\n=== 5. Comma-Joined Distinct Products ===");
        System.out.println(commaJoinedProducts);

        System.out.println("\n=== 6. Sequential vs Parallel Performance ===");
        long startSeq = System.nanoTime();
        orders.stream().map(o -> cpuHeavyTransform(o.getAmount())).reduce(0.0, Double::sum);
        long endSeq = System.nanoTime();
        System.out.printf("Sequential Time: %.2f ms\n", (endSeq - startSeq) / 1_000_000.0);

        long startPar = System.nanoTime();
        orders.parallelStream().map(o -> cpuHeavyTransform(o.getAmount())).reduce(0.0, Double::sum);
        long endPar = System.nanoTime();
        System.out.printf("Parallel Time:   %.2f ms\n", (endPar - startPar) / 1_000_000.0);
    }

    private static double cpuHeavyTransform(double val) {
        double temp = val;
        for (int i = 0; i < 1500; i++) {
            temp = Math.sin(temp) * Math.cos(temp);
        }
        return temp + val;
    }
}