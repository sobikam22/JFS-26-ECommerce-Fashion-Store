package com.hcldailytask;

public class PaymentTester {
    public static void main(String[] args) {

        // Runtime Polymorphism: Storing child objects in parent reference
        Payment card = new CardPayment("TXN1001", 2500.00, "1234567890123456");
        Payment upi = new UpiPayment("TXN1002", 1200.00, "user@upi");

        card.processPayment();
        upi.processPayment();

        // Demonstrating Overloaded pay() method
        card.pay(500.00);
        card.pay(500.00, "Credit Card");

        // Interface capabilities
        Refundable refundableCard = (Refundable) card;
        refundableCard.processRefund(500.00);
    }
}