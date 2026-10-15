public class OrderResourceCloser implements AutoCloseable {

    private final String resourceName;

    public OrderResourceCloser(String resourceName) {
        this.resourceName = resourceName;
        System.out.println("Opening resource: " + resourceName);
    }

    public void processResource() {
        System.out.println("Processing data for: " + resourceName);
    }

    @Override
    public void close() {
        System.out.println("Closing order resource safely: " + resourceName);
    }
}