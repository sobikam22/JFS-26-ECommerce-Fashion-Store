package com.hcldailytask;

public abstract class Payment {
    protected String transactionId;
    protected double amount;

    public Payment(String transactionId, double amount) {
        this.transactionId = transactionId;
        this.amount = amount;
    }

    // Abstract method to be overridden by subclasses
    public abstract void processPayment();

    // Overloaded method (Method Overloading)
    public void pay(double amount) {
        System.out.printf("Processing base payment of ₹%.2f\n", amount);
    }

    public void pay(double amount, String paymentType) {
        System.out.printf("Processing %s payment of ₹%.2f\n", paymentType, amount);
    }
}