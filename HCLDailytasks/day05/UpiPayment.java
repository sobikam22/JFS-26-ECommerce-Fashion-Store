public class UpiPayment extends Payment implements Refundable {
    private String upiId;

    public UpiPayment(double amount, String upiId) {
        super(amount);
        this.upiId = upiId;
    }

    @Override
    public void pay() {
        System.out.printf("Paid $%.2f instantly via UPI ID: %s\n", amount, upiId);
    }

    @Override
    public void refund(double amount) {
        System.out.printf("Refunded $%.2f back to UPI ID: %s\n", amount, upiId);
    }
}