public abstract class Payment {
    protected double amount;

    public Payment(double amount) {
        this.amount = amount;
    }

    // Abstract method to be implemented by subclasses
    public abstract void pay();

    // Overloaded method (demonstrating method overloading)
    public void pay(String currency) {
        System.out.println("Processing payment in currency: " + currency);
        pay();
    }
}