public class MonthlyUsageAnalyzer {
    public static void main(String[] args) {
        System.out.println("=== " + Constants.APP_NAME + " ===");

        // 1-D Array for 12 months of usage (in GB)
        double[] monthlyUsage = { 120.5, 95.0, 110.2, 130.4, 145.8, 160.0,
                155.5, 140.2, 125.0, 115.3, 105.1, 135.0 };

        double totalUsage = 0;
        double maxUsage = monthlyUsage[0];
        double minUsage = monthlyUsage[0];

        for (double usage : monthlyUsage) {
            totalUsage += usage;
            if (usage > maxUsage) maxUsage = usage;
            if (usage < minUsage) minUsage = usage;
        }

        // Type casting average calculation
        double averageUsage = totalUsage / monthlyUsage.length;
        int roundedAverage = (int) averageUsage; // Widening/Narrowing cast example

        // Ternary operator for evaluation grade
        char usageGrade = (averageUsage > 130.0) ? 'H' : (averageUsage > 100.0) ? 'M' : 'L';

        // Overflow prevention demonstration using long for large billing calculations
        long annualBillingCalculation = (long) (totalUsage * Constants.SLAB_RATE_PER_GB);

        System.out.println("----------------------------------------");
        System.out.printf("Total Annual Usage: %.2f GB\n", totalUsage);
        System.out.printf("Average Monthly Usage: %.2f GB (Rounded: %d)\n", averageUsage, roundedAverage);
        System.out.printf("Peak Month Usage: %.2f GB\n", maxUsage);
        System.out.printf("Lowest Month Usage: %.2f GB\n", minUsage);
        System.out.println("Usage Grade Category: " + usageGrade);
        System.out.println("Projected Annual Bill (INR): ₹" + annualBillingCalculation);

        // 2-D Array demonstration for multiple houses (e.g., 3 houses over 4 quarters)
        System.out.println("\n--- Multi-House Quarterly Usage (2-D Array) ---");
        double[][] multiHouseUsage = {
                { 350.5, 410.0, 390.2, 420.1 }, // House 1
                { 280.0, 290.5, 310.0, 305.2 }, // House 2
                { 450.2, 480.0, 460.5, 490.0 }  // House 3
        };

        for (int i = 0; i < multiHouseUsage.length; i++) {
            double houseTotal = 0;
            for (int j = 0; j < multiHouseUsage[i].length; j++) {
                houseTotal += multiHouseUsage[i][j];
            }
            System.out.printf("House %d Total Usage: %.2f GB\n", (i + 1), houseTotal);
        }
    }
}