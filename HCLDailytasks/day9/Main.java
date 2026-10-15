import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== 1. Testing Generic Toolkit & Bounded Types ===");
        GenericToolkit.Pair<String, Double> productPair = new GenericToolkit.Pair<>("Laptop", 1200.50);
        System.out.println("Pair -> Product: " + productPair.getKey() + ", Price: $" + productPair.getValue());

        GenericToolkit.Box<Integer> stockBox = new GenericToolkit.Box<>(50);
        System.out.println("Box (extends Number) -> Stock: " + stockBox.getItem());

        List<Integer> numbers = Arrays.asList(10, 45, 22, 89, 33);
        System.out.println("Max number found: " + GenericToolkit.findMax(numbers));
        System.out.println("Sum of numbers (wildcard): " + GenericToolkit.sumNumbers(numbers));

        System.out.println("\n=== 2. Testing Custom AutoCloseable Resource ===");
        try (OrderResourceCloser closer = new OrderResourceCloser("Daily-Order-Stream")) {
            closer.processResource();
        } // Automatically calls close() here

        System.out.println("\n=== 3. Testing Generic InMemory Repository ===");
        CrudRepository<String, Long> repo = new InMemoryRepository<>();
        repo.save(1L, "E-Commerce Fashion Item 1");
        System.out.println("Repository items: " + repo.findAll());

        System.out.println("\n=== 4. Testing CSV Data Loader (try-with-resources) ===");
        CSVDataLoader.loadProductsFromCSV("products.csv");
    }
}