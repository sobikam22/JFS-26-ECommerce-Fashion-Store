public class MonthlyUsageAnalyser {

        public static void main(String[] args) {
            int[] monthlyUsage = {120, 150, 90, 210, 340, 410, 520, 480, 310, 220, 180, 130};

            long totalUsage = 0;
            int maxUsage = monthlyUsage[0];
            int minUsage = monthlyUsage[0];

            for (int usage : monthlyUsage) {
                totalUsage += usage;
                if (usage > maxUsage) {
                    maxUsage = usage;
                }
                if (usage < minUsage) {
                    minUsage = usage;
                }
            }

            double averageUsage = (double) totalUsage / monthlyUsage.length;

            char efficiencyGrade = (averageUsage <= Constants.SLAB_TIER_2_LIMIT) ? 'A' :
                    (averageUsage <= Constants.SLAB_TIER_3_LIMIT) ? 'B' : 'C';

            System.out.println("=== Monthly Usage Analyser ===");
            System.out.println("Total Annual Usage: " + totalUsage + " kWh");
            System.out.println("Average Monthly Usage: " + String.format("%.2f", averageUsage) + " kWh");
            System.out.println("Peak Usage Month: " + maxUsage + " kWh");
            System.out.println("Lowest Usage Month: " + minUsage + " kWh");
            System.out.println("Efficiency Grade: " + efficiencyGrade);

            int[][] weeklyHouseUsage = {
                    {12, 15, 11, 14, 18, 22, 20},
                    {8,  9,  10, 8,  12, 15, 14},
                    {25, 28, 30, 24, 26, 35, 31}
            };

            System.out.println("\n=== 2-D Weekly House Consumption Analysis ===");
            for (int i = 0; i < weeklyHouseUsage.length; i++) {
                int houseTotal = 0;
                for (int j = 0; j < weeklyHouseUsage[i].length; j++) {
                    houseTotal += weeklyHouseUsage[i][j];
                }
                System.out.println("House " + (i + 1) + " Weekly Total: " + houseTotal + " kWh");
            }
        }
}
