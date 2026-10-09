package com.hcldailytask;

public class UpiPayment extends Payment implements Refundable {
    private String upiId;

    public UpiPayment(String transactionId, double amount, String upiId) {
        super(transactionId, amount);
        this.upiId = upiId;
    }

    @Override
    public void processPayment() {
        System.out.printf("[UPI Payment] Transaction %s: Paid ₹%.2f via UPI ID (%s)\n",
                transactionId, amount, upiId);
    }

    @Override
    public void processRefund(double refundAmount) {
        System.out.printf("[UPI Refund] Transaction %s: Refunded ₹%.2f to UPI account\n",
                transactionId, refundAmount);
    }
}