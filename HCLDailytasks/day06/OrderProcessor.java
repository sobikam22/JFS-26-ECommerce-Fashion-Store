public class OrderProcessor {
    private int availableStock = 10;

    public void processOrder(String item, int quantity) throws InsufficientStockException {
        // 1. Unchecked Exception validation
        if (quantity <= 0) {
            throw new InvalidQuantityException("Error: Quantity must be greater than zero. Received: " + quantity);
        }

        // 2. Checked Exception validation with chaining
        if (quantity > availableStock) {
            Throwable cause = new IllegalArgumentException("Requested quantity exceeds warehouse capacity limit.");
            throw new InsufficientStockException("Failed to process order for " + item + ". Stock is insufficient.", cause);
        }

        availableStock -= quantity;
        System.out.printf("Success! Processed %d units of %s. Remaining stock: %d\n", quantity, item, availableStock);
    }
}