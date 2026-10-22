
import java.time.*;

public class Day15Main {
    public static void main(String[] args) {
        System.out.println("=== Day 15 Task Execution ===");

        // 1. Test Calculator
        SubscriptionCalculator calc = new SubscriptionCalculator();
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Expiry Date (+6 months): " + calc.calculateExpiryDate(now, 6));

        // 2. Test Optional Service & Exceptions
        SubscriptionLookupService service = new SubscriptionLookupService();

        SubscriptionUser user = service.getUserOrThrow(101L);
        System.out.println("Found User: " + user.getEmail());

        try {
            service.getUserOrThrow(999L); // Triggers UserNotFoundException
        } catch (UserNotFoundException e) {
            System.out.println("Caught Custom Exception Successfully: " + e.getMessage());
        }
    }
}