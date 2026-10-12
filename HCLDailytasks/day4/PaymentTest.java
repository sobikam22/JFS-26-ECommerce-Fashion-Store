interface Refundable {
    void processRefund(double amount);
}

abstract class Payment {
    protected double amount;

    public Payment(double amount) {
        this.amount = amount;
    }

    public abstract void pay();
}

class UpiPayment extends Payment implements Refundable {
    private String upiId;

    public UpiPayment(double amount, String upiId) {
        super(amount);
        this.upiId = upiId;
    }

    @Override
    public void pay() {
        System.out.printf("Paid $%.2f using UPI ID: %s\n", amount, upiId);
    }

    @Override
    public void processRefund(double amount) {
        System.out.printf("Refunded $%.2f to UPI ID: %s\n", amount, upiId);
    }
}

class CardPayment extends Payment implements Refundable {
    private String cardNumber;

    public CardPayment(double amount, String cardNumber) {
        super(amount);
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay() {
        System.out.printf("Paid $%.2f using Card ending in %s\n", amount, cardNumber.substring(cardNumber.length() - 4));
    }

    @Override
    public void processRefund(double amount) {
        System.out.printf("Refunded $%.2f to Card ending in %s\n", amount, cardNumber.substring(cardNumber.length() - 4));
    }
}

public class PaymentTest {
    public static void main(String[] args) {
        System.out.println("=== Day 4: OOP Concepts & Polymorphism ===");
        Payment p1 = new UpiPayment(150.00, "user@okaxis");
        Payment p2 = new CardPayment(450.00, "4111222233334444");

        p1.pay();
        ((Refundable) p1).processRefund(50.00);

        System.out.println("-------------------");

        p2.pay();
        ((Refundable) p2).processRefund(100.00);
    }
}