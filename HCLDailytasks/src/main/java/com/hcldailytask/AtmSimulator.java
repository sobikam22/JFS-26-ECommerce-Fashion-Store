package com.hcldailytask;
import java.util.Scanner;
public class AtmSimulator {
    private static double balance = 10000.00;
    private static final int PIN = 1234;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter PIN: ");
        int enteredPin = scanner.nextInt();

        if (enteredPin != PIN) {
            System.out.println("Incorrect PIN. Access Denied.");
            return;
        }

        System.out.println("Login successful.");
        boolean running = true;

        while (running) {
            System.out.println("\n========== ATM MENU ==========");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Mini Statement");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.printf("Current balance: ₹%.2f\n", balance);
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    if (depositAmount > 0) {
                        balance += depositAmount;
                        System.out.println("Deposit successful.");
                        System.out.printf("Current balance: ₹%.2f\n", balance);
                    } else {
                        System.out.println("Invalid amount.");
                    }
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = scanner.nextDouble();

                    // DAY 4 DEBUGGING TASK: Set a conditional breakpoint here (withdrawAmount > 50)
                    if (withdrawAmount > 0 && withdrawAmount <= balance) {
                        balance -= withdrawAmount;
                        System.out.println("Withdrawal successful.");
                        System.out.printf("Current balance: ₹%.2f\n", balance);
                    } else {
                        System.out.println("Insufficient balance or invalid amount.");
                    }
                    break;
                case 4:
                    System.out.println("=== Mini Statement ===");
                    System.out.printf("Available Balance: ₹%.2f\n", balance);
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
    }
}
