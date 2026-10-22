
import java.time.LocalDateTime;

public class SubscriptionUser {
    private Long id;
    private String email;
    private String subscriptionType;
    private LocalDateTime expiryDate;

    public SubscriptionUser(Long id, String email, String subscriptionType, LocalDateTime expiryDate) {
        this.id = id;
        this.email = email;
        this.subscriptionType = subscriptionType;
        this.expiryDate = expiryDate;
    }

    public Long getId() { return id; }
    public String getEmail() { return email; }
    public String getSubscriptionType() { return subscriptionType; }
    public LocalDateTime getExpiryDate() { return expiryDate; }
}