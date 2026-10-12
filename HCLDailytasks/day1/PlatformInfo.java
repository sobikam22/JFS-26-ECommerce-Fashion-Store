public class PlatformInfo {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();

        System.out.println("=========================================");
        System.out.println("      JAVA PLATFORM DIAGNOSTICS          ");
        System.out.println("=========================================");
        System.out.println("Java Version  : " + System.getProperty("java.version"));
        System.out.println("OS Name       : " + System.getProperty("os.name"));
        System.out.println("OS Arch       : " + System.getProperty("os.arch"));
        System.out.println("Processors    : " + runtime.availableProcessors());
        System.out.println("Max Heap (MB) : " + (runtime.maxMemory() / (1024 * 1024)));
        System.out.println("Free Heap (MB): " + (runtime.freeMemory() / (1024 * 1024)));
        System.out.println("Total Heap(MB): " + (runtime.totalMemory() / (1024 * 1024)));
        System.out.println("=========================================");
    }
}