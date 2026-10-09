package com.hcldailytask;

public class PlatformInfo {
    public static void main(String[] args) {
        System.out.println("=== Java Platform Information ===");
        System.out.println("Java Version : " + System.getProperty("java.version"));
        System.out.println("OS Name      : " + System.getProperty("os.name"));
        System.out.println("OS Arch      : " + System.getProperty("os.arch"));

        Runtime runtime = Runtime.getRuntime();
        System.out.println("Available Processors : " + runtime.availableProcessors());
        System.out.println("Max Memory (MB)      : " + (runtime.maxMemory() / (1024 * 1024)));
        System.out.println("Free Memory (MB)     : " + (runtime.freeMemory() / (1024 * 1024)));
        System.out.println("Total Memory (MB)    : " + (runtime.totalMemory() / (1024 * 1024)));
    }
}