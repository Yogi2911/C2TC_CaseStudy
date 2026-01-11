package com.tnsif.fooddelivery;

public class ConsoleDesign {
    public static void printLogo() {
        System.out.println("\n" +
            "╔══════════════════════════════════════════════════════════════════════╗\n" +
            "║  🍕 ONLINE FOOD DELIVERY SYSTEM - CORE JAVA PROJECT 🍔               ║\n" +
            "║  Team Name: BUG SLAYERS    |    Representative: DIVYA M              ║\n" +
            "║  ════════════════════════════════════════════════════════════════════║\n" +
            "║  Group Members:                                                      ║\n" +
            "║  1. Divya M    2. Abinaya    3. Afreen    4. PriyaDharshini          ║\n" +
            "║  5. Rohini     6. Yogeswari                                          ║\n" +
            "║  Features: OOP | Collections | Smart Recommendations | Error-Free!   ║\n" +
            "╚══════════════════════════════════════════════════════════════════════╝\n");
    }

    public static void printProgressLogo() {
        System.out.println("\n" +
            "🎉 MISSION ACCOMPLISHED! 🎉\n" +
            "  ┌─────────────────────────────┐\n" +
            "  │   BUG SLAYERS ROCKS! 🔥     │\n" +
            "  │   Team: Divya M & Crew      │\n" +
            "  └─────────────────────────────┘\n");
    }

    public static void printHeader(String title) {
        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.printf("║                    %-27s║\n", title.toUpperCase());
        System.out.println("╠══════════════════════════════════════════════════════╣\n");
    }

    public static void printSuccess(String msg) {
        System.out.println("✅ " + msg.toUpperCase());
    }

    public static void printError(String msg) {
        System.out.println("❌ " + msg);
    }

    public static void printMenuItem(int num, String item) {
        System.out.printf("  %d. %-35s ➤\n", num, item);
    }

    public static void printSeparator() {
        System.out.println("╠══════════════════════════════════════════════════════╣");
    }
}