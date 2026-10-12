import java.util.Scanner;

public class OrderApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        OrderProcessor processor = new OrderProcessor();

        System.out.println("=== Day 6: Exception Handling & Recovery ===");

        boolean continuing = true;
        while (continuing) {
            try {
                System.out.print("\nEnter item name: ");
                String item = scanner.next();

                System.out.print("Enter quantity to order: ");
                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid input token. Please enter a valid number.");
                    scanner.next(); // clear invalid input
                    continue;
                }
                int qty = scanner.nextInt();

                processor.processOrder(item, qty);
                continuing = false; // Exit on success

            } catch (InsufficientStockException | InvalidQuantityException e) {
                // Multi-catch block demonstration
                System.out.println("[Handled Business Exception]: " + e.getMessage());
                if (e.getCause() != null) {
                    System.out.println("Root Cause: " + e.getCause().getMessage());
                }
                System.out.println("Please try placing the order again with a valid quantity.");
            } catch (Exception e) {
                // Generic fallback catch
                System.out.println("[Unexpected Error]: " + e.getMessage());
            } finally {
                // Finally audit block semantics
                System.out.println("[Audit Log]: Transaction attempt cycle completed.");
            }
        }

        scanner.close();
        System.out.println("Exiting Order Application safely.");
    }
}