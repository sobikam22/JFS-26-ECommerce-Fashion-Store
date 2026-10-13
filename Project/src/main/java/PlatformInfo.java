public class PlatformInfo {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();

        System.out.println("=== Day 1: Java Platform Basics & System Info ===");
        System.out.println("Java Version:       " + System.getProperty("java.version"));
        System.out.println("Java Vendor:        " + System.getProperty("java.vendor"));
        System.out.println("OS Name:            " + System.getProperty("os.name"));
        System.out.println("OS Architecture:    " + System.getProperty("os.arch"));
        System.out.println("User Name:          " + System.getProperty("user.name"));
        System.out.println("Processors (Cores): " + runtime.availableProcessors());
        System.out.println("Max Heap Memory:    " + (runtime.maxMemory() / (1024 * 1024)) + " MB");
        System.out.println("Free Heap Memory:   " + (runtime.freeMemory() / (1024 * 1024)) + " MB");
    }
}