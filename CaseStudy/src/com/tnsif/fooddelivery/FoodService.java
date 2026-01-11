package com.tnsif.fooddelivery;

import java.util.*;

public class FoodService {
    private Map<Integer, Restaurant> restaurants = new HashMap<>();

    public void addRestaurant(Restaurant r) {
        restaurants.put(r.getId(), r);
    }

    public Restaurant getRestaurant(int id) {
        return restaurants.get(id);
    }

    public Map<Integer, Restaurant> getAllRestaurants() {
        return restaurants;
    }

    public void printMenus() {
        if (restaurants.isEmpty()) {
            System.out.println("\nNo restaurants available!");
            return;
        }
        
        ConsoleDesign.printHeader("RESTAURANTS & MENUS");
        for (Restaurant r : restaurants.values()) {
            System.out.println(r);
            if (r.getMenu().isEmpty()) {
                System.out.println("  (No items)");
            } else {
                for (FoodItem f : r.getMenu()) {
                    System.out.println("  " + f);
                }
            }
            ConsoleDesign.printSeparator();
        }
    }
}