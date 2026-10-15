public class Order {
    private String orderId;
    private String customerName;
    private double amount;
    private String status;

    public Order(String orderId, String customerName, double amount, String status) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.amount = amount;
        this.status = status;
    }

    public String getOrderId() { return orderId; }
    public String getCustomerName() { return customerName; }
    public double getAmount() { return amount; }
    public String getStatus() { return status; }

    @Override
    public String toString() {
        return "Order{ID='" + orderId + "', Customer='" + customerName + "', Amount=$" + amount + ", Status='" + status + "'}";
    }
}