
import java.time.*;
import java.time.format.DateTimeFormatter;

public class SubscriptionCalculator {
    private static final ZoneId IST_ZONE = ZoneId.of("Asia/Kolkata");
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");

    public LocalDateTime calculateExpiryDate(LocalDateTime startDate, int durationMonths) {
        if (startDate == null) {
            throw new IllegalArgumentException("Start date cannot be null");
        }
        return startDate.plusMonths(durationMonths);
    }

    public long calculateDaysRemaining(LocalDate today, LocalDate expiryDate) {
        if (today == null || expiryDate == null) {
            throw new IllegalArgumentException("Dates cannot be null");
        }
        if (today.isAfter(expiryDate)) {
            return 0;
        }
        return Period.between(today, expiryDate).getDays();
    }

    public boolean isInGracePeriod(LocalDate today, LocalDate expiryDate, int gracePeriodDays) {
        if (today == null || expiryDate == null) {
            throw new IllegalArgumentException("Dates cannot be null");
        }
        LocalDate gracePeriodEnd = expiryDate.plusDays(gracePeriodDays);
        return (today.isAfter(expiryDate) || today.isEqual(expiryDate)) && today.isBefore(gracePeriodEnd);
    }

    public String convertUtcToIstFormatted(ZonedDateTime utcDateTime) {
        if (utcDateTime == null) {
            throw new IllegalArgumentException("UTC DateTime cannot be null");
        }
        ZonedDateTime istDateTime = utcDateTime.withZoneSameInstant(IST_ZONE);
        return istDateTime.format(FORMATTER);
    }
}