public class Product implements Comparable<Product> {
    private String productId;
    private String name;
    private double price;

    public Product(String productId, String name, double price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    public String getProductId() { return productId; }
    public String getName() { return name; }
    public double getPrice() { return price; }

    @Override
    public int compareTo(Product other) {
        // Natural ordering by price
        return Double.compare(this.price, other.price);
    }

    @Override
    public String toString() {
        return "Product{ID='" + productId + "', Name='" + name + "', Price=$" + price + "}";
    }
}