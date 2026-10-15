import java.util.Scanner;

public class AtmSimulator {
    private static final int CORRECT_PIN = 1234;
    private static double balance = 5000.00;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int pinAttempts = 0;
        boolean isAuthenticated = false;

        System.out.println("=== HCL Secure ATM Simulator ===");

        // Authentication loop (Max 3 attempts using break)
        while (pinAttempts < 3) {
            System.out.print("Enter your 4-digit PIN: ");
            int enteredPin = scanner.nextInt();

            if (enteredPin == CORRECT_PIN) {
                isAuthenticated = true;
                System.out.println("Authentication Successful!\n");
                break;
            } else {
                pinAttempts++;
                System.out.println("Incorrect PIN. Attempts remaining: " + (3 - pinAttempts));
            }
        }

        if (!isAuthenticated) {
            System.out.println("Card blocked due to multiple incorrect PIN attempts.");
            scanner.close();
            return;
        }

        // Transaction Menu using do-while and switch
        boolean running = true;
        double[] recentTransactions = { 200.00, -150.00, 500.00, -50.00 };

        do {
            System.out.println("\n--- ATM Menu ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Funds");
            System.out.println("3. Withdraw Funds");
            System.out.println("4. View Recent Transactions");
            System.out.println("5. Exit");
            System.out.print("Choose an option (1-5): ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next(); // Clear invalid token
                continue;
            }

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.printf("Current Balance: $%.2f\n", balance);
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ");
                    double deposit = scanner.nextDouble();
                    if (deposit > 0) {
                        balance += deposit;
                        System.out.printf("Successfully deposited $%.2f\n", deposit);
                    } else {
                        System.out.println("Invalid deposit amount.");
                    }
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawal = scanner.nextDouble();
                    if (withdrawal > 0 && withdrawal <= balance) {
                        balance -= withdrawal;
                        System.out.printf("Successfully withdrew $%.2f\n", withdrawal);
                    } else {
                        System.out.println("Insufficient funds or invalid amount.");
                    }
                    break;
                case 4:
                    System.out.println("--- Recent Transactions Summary ---");
                    for (double tx : recentTransactions) {
                        String type = (tx > 0) ? "CREDIT" : "DEBIT";
                        System.out.printf("[%s] $%.2f\n", type, Math.abs(tx));
                    }
                    break;
                case 5:
                    running = false;
                    System.out.println("Thank you for banking with us. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select between 1 and 5.");
            }
        } while (running);

        scanner.close();
    }
}