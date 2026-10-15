public class PaymentTest {
    public static void main(String[] args) {
        System.out.println("=== Day 5: Inheritance & Polymorphism ===");

        // Runtime polymorphism using abstract parent type
        Payment p1 = new CardPayment(450.75, "4111222233334444");
        Payment p2 = new UpiPayment(120.00, "sobika@okhdfcbank");

        p1.pay();
        p1.pay("USD"); // Overloaded method call
        ((Refundable) p1).refund(50.00);

        System.out.println("----------------------------------------");

        p2.pay();
        ((Refundable) p2).refund(120.00);
    }
}