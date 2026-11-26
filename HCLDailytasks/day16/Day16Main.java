import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Day16Main {

    public static void main(String[] args) {
        PriceAggregator aggregator = new PriceAggregator();
        String sku = "JKT-DENIM-01";

        System.out.println("=== 1. Sequential Fetch ===");
        long t1Start = System.nanoTime();
        List<PriceAggregator.SupplierQuote> seqQuotes = aggregator.fetchSequential(sku);
        long seqMs = (System.nanoTime() - t1Start) / 1_000_000;
        printQuotes(seqQuotes);
        System.out.println("Sequential Time: " + seqMs + " ms\n");

        System.out.println("=== 2. ExecutorService + Callable + Future ===");
        long t2Start = System.nanoTime();
        List<PriceAggregator.SupplierQuote> execQuotes = aggregator.fetchWithExecutorCallable(sku);
        long execMs = (System.nanoTime() - t2Start) / 1_000_000;
        printQuotes(execQuotes);
        System.out.println("ExecutorService Time: " + execMs + " ms\n");

        System.out.println("=== 3. CompletableFuture.allOf + exceptionally ===");
        long t3Start = System.nanoTime();
        List<PriceAggregator.SupplierQuote> cfQuotes = aggregator.fetchWithCompletableFutureAllOf(sku);
        long cfMs = (System.nanoTime() - t3Start) / 1_000_000;
        printQuotes(cfQuotes);
        System.out.println("CompletableFuture.allOf Time: " + cfMs + " ms\n");

        System.out.println("=== Timing Comparison ===");
        System.out.printf("Sequential: %d ms | ExecutorService: %d ms | CompletableFuture.allOf: %d ms%n",
                seqMs, execMs, cfMs);

        // Quick thenCombine demo: compare best valid price against a loyalty voucher discount
        CompletableFuture<Double> bestPriceFuture = CompletableFuture.supplyAsync(() ->
                cfQuotes.stream()
                        .flatMap(q -> q.price().stream())
                        .mapToDouble(Double::doubleValue)
                        .min()
                        .orElse(0.0)
        );
        CompletableFuture<Double> voucherFuture = CompletableFuture.supplyAsync(() -> 150.0);
        double finalPrice = bestPriceFuture.thenCombine(voucherFuture, (price, voucher) -> Math.max(0.0, price - voucher)).join();
        System.out.println("Best Available Price after ₹150 Voucher (thenCombine): ₹" + finalPrice + "\n");

        // Concept Hook: Bulk notification with CompletableFuture
        System.out.println("=== Concept Hook: Bulk Notification ===");
        List<String> statuses = aggregator.sendBulkNotificationsAsync(
                List.of("alice@example.com", "invalid-user@example.com", "bob@example.com"),
                "Price drop alert on " + sku
        );
        statuses.forEach(System.out::println);
    }

    private static void printQuotes(List<PriceAggregator.SupplierQuote> quotes) {
        for (PriceAggregator.SupplierQuote q : quotes) {
            String priceText = q.price().map(p -> "₹" + p).orElse("N/A");
            System.out.println(" - " + q.supplierName() + " -> Price: " + priceText + " | " + q.status());
        }
    }
}