package com.tnsif.fooddelivery;

public class FoodItem {
    private int id;
    private String name;
    private double price;
    private boolean veg;

    public FoodItem(int id, String name, double price, boolean veg) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.veg = veg;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public boolean isVeg() { return veg; }

    @Override
    public String toString() {
        return String.format("ID %d | %s | Rs. %.0f | %s", 
            id, name, price, veg ? "Veg" : "Non-Veg");
    }
}
