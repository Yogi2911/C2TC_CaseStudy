package com.tnsif.fooddelivery;

import java.util.*;

public class RecommendationService {
    
    public static void recommend(Map<Integer, Restaurant> restaurants, double budget, Boolean veg) {
        ConsoleDesign.printHeader("RECOMMENDATIONS");
        String pref = veg == null ? "ALL" : veg ? "VEG" : "NON-VEG";
        System.out.printf("Budget: Rs.%.0f | %s\n\n", budget, pref);
        
        boolean found = false;
        for (Restaurant r : restaurants.values()) {
            for (FoodItem f : r.getMenu()) {
                if (f.getPrice() <= budget && (veg == null || f.isVeg() == veg)) {
                    System.out.printf("✨ %s → %s (Rs.%.0f) %s\n", 
                        r.getName(), f.getName(), f.getPrice(), f.isVeg() ? "🟢" : "🔴");
                    found = true;
                }
            }
        }
        if (!found) System.out.println("No matches!");
    }
}