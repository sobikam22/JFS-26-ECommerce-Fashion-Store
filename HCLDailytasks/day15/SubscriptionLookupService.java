
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SubscriptionLookupService {
    private final Map<Long, SubscriptionUser> userDatabase = new HashMap<>();

    public SubscriptionLookupService() {
        userDatabase.put(101L, new SubscriptionUser(101L, "user1@example.com", "PREMIUM", LocalDateTime.now().plusDays(30)));
    }

    // Returns Optional instead of null
    public Optional<SubscriptionUser> findById(Long id) {
        return Optional.ofNullable(userDatabase.get(id));
    }

    // Uses orElseThrow with custom exception
    public SubscriptionUser getUserOrThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
    }

    public String getUserExpiryFormatted(Long id) {
        return findById(id)
                .map(user -> "Subscription expires at: " + user.getExpiryDate().toString())
                .orElse("No active subscription record found.");
    }
}