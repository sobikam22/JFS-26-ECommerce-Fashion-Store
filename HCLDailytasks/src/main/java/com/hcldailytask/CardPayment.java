package com.hcldailytask;

public class CardPayment extends Payment implements Refundable {
    private String cardNumber;

    public CardPayment(String transactionId, double amount, String cardNumber) {
        super(transactionId, amount);
        this.cardNumber = cardNumber;
    }

    @Override
    public void processPayment() {
        System.out.printf("[Card Payment] Transaction %s: Paid ₹%.2f using card ending in %s\n",
                transactionId, amount, cardNumber.substring(cardNumber.length() - 4));
    }

    @Override
    public void processRefund(double refundAmount) {
        System.out.printf("[Card Refund] Transaction %s: Refunded ₹%.2f back to card\n",
                transactionId, refundAmount);
    }
}