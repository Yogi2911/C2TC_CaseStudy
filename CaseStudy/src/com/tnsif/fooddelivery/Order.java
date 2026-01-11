package com.tnsif.fooddelivery;

import java.util.HashMap;
import java.util.Map;

public class Order {
    private int orderId;
    private Customer customer;
    private Map<FoodItem, Integer> items;
    private String status = "Pending";
    private DeliveryPerson deliveryPerson;
    private String deliveryAddress;

    public Order(int orderId, Customer customer, String deliveryAddress) {
        this.orderId = orderId;
        this.customer = customer;
        this.items = new HashMap<>(customer.getCart().getItems());
        this.deliveryAddress = deliveryAddress;
    }

    // Getters
    public int getOrderId() { return orderId; }
    public Customer getCustomer() { return customer; }
    public Map<FoodItem, Integer> getItems() { return items; }
    public String getStatus() { return status; }
    public DeliveryPerson getDeliveryPerson() { return deliveryPerson; }
    public String getDeliveryAddress() { return deliveryAddress; }

    // Setters
    public void setStatus(String status) { this.status = status; }
    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public void assignDelivery(DeliveryPerson dp) {
        this.deliveryPerson = dp;
        this.status = "Assigned";
    }

    public void addItem(FoodItem item, int quantity) {
        items.put(item, items.getOrDefault(item, 0) + quantity);
    }

    public double getTotalAmount() {
        double total = 0;
        for (Map.Entry<FoodItem, Integer> entry : items.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }

    @Override
    public String toString() {
        int totalItems = items.values().stream().mapToInt(i -> i).sum();
        return String.format("Order #%d | %s | Items:%d | Rs.%.0f | %s | %s", 
            orderId, 
            customer.getUsername(), 
            totalItems,
            getTotalAmount(),
            status, 
            deliveryPerson != null ? deliveryPerson.getName() : "Not Assigned");
    }
}