import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class PriceAggregator {

    public record SupplierQuote(String supplierName, Optional<Double> price, String status) {}

    private record SupplierSpec(String name, long delayMillis, double price, boolean shouldFail) {}

    private final List<SupplierSpec> suppliers;

    public PriceAggregator() {
        this.suppliers = List.of(
                new SupplierSpec("Supplier-1 (StyleHub)", 1200, 1499.0, false),
                new SupplierSpec("Supplier-2 (TrendVault)", 1500, 1399.0, true),
                new SupplierSpec("Supplier-3 (UrbanWeave)", 1800, 1549.0, false)
        );
    }

    // Simulated slow I/O call to a supplier
    private double fetchFromSupplier(SupplierSpec spec) {
        try {
            Thread.sleep(spec.delayMillis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Interrupted while querying " + spec.name, e);
        }

        if (spec.shouldFail) {
            throw new RuntimeException("Timeout/503 error from " + spec.name);
        }
        return spec.price;
    }

    // 1. Sequential fetch
    public List<SupplierQuote> fetchSequential(String itemCode) {
        List<SupplierQuote> results = new ArrayList<>();
        for (SupplierSpec spec : suppliers) {
            try {
                double price = fetchFromSupplier(spec);
                results.add(new SupplierQuote(spec.name, Optional.of(price), "OK (" + itemCode + ")"));
            } catch (RuntimeException ex) {
                results.add(new SupplierQuote(spec.name, Optional.empty(), "FAILED: " + ex.getMessage()));
            }
        }
        return results;
    }

    // 2. ExecutorService + Callable + Future
    public List<SupplierQuote> fetchWithExecutorCallable(String itemCode) {
        ExecutorService executor = Executors.newFixedThreadPool(suppliers.size());
        List<SupplierQuote> results = new ArrayList<>();
        try {
            List<Callable<SupplierQuote>> tasks = new ArrayList<>();
            for (SupplierSpec spec : suppliers) {
                tasks.add(() -> {
                    try {
                        double price = fetchFromSupplier(spec);
                        return new SupplierQuote(spec.name, Optional.of(price), "OK (" + itemCode + ")");
                    } catch (RuntimeException ex) {
                        return new SupplierQuote(spec.name, Optional.empty(), "FAILED: " + ex.getMessage());
                    }
                });
            }

            List<Future<SupplierQuote>> futures = executor.invokeAll(tasks);
            for (Future<SupplierQuote> future : futures) {
                try {
                    results.add(future.get());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    results.add(new SupplierQuote("Unknown", Optional.empty(), "INTERRUPTED"));
                } catch (ExecutionException e) {
                    results.add(new SupplierQuote("Unknown", Optional.empty(), "EXECUTION_ERROR: " + e.getCause().getMessage()));
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            executor.shutdown();
            try {
                if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
        return results;
    }

    // 3. CompletableFuture.allOf + exceptionally (non-crashing partial failure handling)
    public List<SupplierQuote> fetchWithCompletableFutureAllOf(String itemCode) {
        ExecutorService ioPool = Executors.newFixedThreadPool(suppliers.size());
        try {
            List<CompletableFuture<SupplierQuote>> quoteFutures = suppliers.stream()
                    .map(spec -> CompletableFuture
                            .supplyAsync(() -> fetchFromSupplier(spec), ioPool)
                            .thenApply(price -> new SupplierQuote(spec.name, Optional.of(price), "OK (" + itemCode + ")"))
                            .exceptionally(ex -> {
                                Throwable cause = (ex.getCause() != null) ? ex.getCause() : ex;
                                return new SupplierQuote(spec.name, Optional.empty(), "FALLBACK: " + cause.getMessage());
                            }))
                    .collect(Collectors.toList());

            CompletableFuture<Void> allDone = CompletableFuture.allOf(
                    quoteFutures.toArray(new CompletableFuture[0])
            );

            return allDone.thenApply(v -> quoteFutures.stream()
                    .map(CompletableFuture::join)
                    .collect(Collectors.toList())).join();
        } finally {
            ioPool.shutdown();
            try {
                if (!ioPool.awaitTermination(5, TimeUnit.SECONDS)) {
                    ioPool.shutdownNow();
                }
            } catch (InterruptedException e) {
                ioPool.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }

    // Concept Hook: Parallel bulk notification / availability dispatch using CompletableFuture
    public List<String> sendBulkNotificationsAsync(List<String> recipients, String message) {
        Objects.requireNonNull(recipients, "Recipients list must not be null");
        ExecutorService notifyPool = Executors.newFixedThreadPool(Math.min(Math.max(recipients.size(), 1), 4));
        try {
            List<CompletableFuture<String>> futures = recipients.stream()
                    .map(user -> CompletableFuture.supplyAsync(() -> {
                        try {
                            Thread.sleep(500); // Simulate async push/email I/O
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return "FAILED -> " + user + " (interrupted)";
                        }
                        if (user.contains("invalid")) {
                            throw new RuntimeException("Delivery bounce for " + user);
                        }
                        return "SENT -> " + user + ": " + message;
                    }, notifyPool).exceptionally(ex -> "SKIPPED -> " + user + " (" + ex.getCause().getMessage() + ")"))
                    .collect(Collectors.toList());

            return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                    .thenApply(v -> futures.stream().map(CompletableFuture::join).collect(Collectors.toList()))
                    .join();
        } finally {
            notifyPool.shutdown();
            try {
                if (!notifyPool.awaitTermination(3, TimeUnit.SECONDS)) {
                    notifyPool.shutdownNow();
                }
            } catch (InterruptedException e) {
                notifyPool.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }
}