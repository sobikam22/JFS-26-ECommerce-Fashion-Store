public class BankApp {
    public static void main(String[] args) {
        System.out.println("=== Day 4: OOP Concepts & Encapsulation ===");

        BankAccount acc1 = new BankAccount("ACC-101", "Sobika M.", 1000.0);
        BankAccount acc2 = new BankAccount("ACC-102", "Joshua");

        System.out.println(acc1);
        System.out.println(acc2);

        acc1.deposit(500.0);
        acc1.withdraw(200.0);

        System.out.println("Total Bank Accounts Active: " + BankAccount.getTotalAccounts());
    }
}