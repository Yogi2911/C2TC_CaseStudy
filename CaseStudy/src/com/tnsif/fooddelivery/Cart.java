package com.tnsif.fooddelivery;
import java.util.*;

public class Cart {
    private Map<FoodItem, Integer> items = new HashMap<>();

    public void addItem(FoodItem item, int qty) {
        items.put(item, items.getOrDefault(item, 0) + qty);
    }

    public void removeItem(FoodItem item) { items.remove(item); }

    public Map<FoodItem, Integer> getItems() { return items; }

    public void clear() { items.clear(); }

    public double getTotalCost() {
        return items.entrySet().stream()
            .mapToDouble(e -> e.getKey().getPrice() * e.getValue()).sum();
    }

    @Override
    public String toString() {
        if (items.isEmpty()) return "🛒 Cart is empty!";
        
        StringBuilder sb = new StringBuilder("\n🛒 CART:\n");
        for (Map.Entry<FoodItem, Integer> e : items.entrySet()) {
            sb.append(String.format("  %s x%d = Rs.%.0f\n", 
                e.getKey().getName(), e.getValue(), 
                e.getKey().getPrice() * e.getValue()));
        }
        sb.append("═══════════════════\n");
        sb.append(String.format("💰 TOTAL: Rs.%.0f\n", getTotalCost()));
        return sb.toString();
    }
}