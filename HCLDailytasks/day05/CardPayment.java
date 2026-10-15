public class CardPayment extends Payment implements Refundable {
    private String cardNumber;

    public CardPayment(double amount, String cardNumber) {
        super(amount);
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay() {
        System.out.printf("Paid $%.2f securely via Credit/Debit Card ending in %s\n",
                amount, cardNumber.substring(cardNumber.length() - 4));
    }

    @Override
    public void refund(double amount) {
        System.out.printf("Refunded $%.2f back to Card ending in %s\n",
                amount, cardNumber.substring(cardNumber.length() - 4));
    }
}