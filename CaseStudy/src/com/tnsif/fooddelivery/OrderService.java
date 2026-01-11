package com.tnsif.fooddelivery;

import java.util.*;
import java.util.stream.Collectors;

public class OrderService {
    private Map<Integer, Order> orders = new HashMap<>();
    private int nextId = 1;

    public Order placeOrder(Customer c, String address) {
        Order o = new Order(nextId++, c, address);
        orders.put(o.getOrderId(), o);
        c.getCart().clear();
        return o;
    }

    public Order getOrder(int id) { return orders.get(id); }

    public Map<Integer, Order> getAllOrders() { return orders; }

    public List<Order> getOrdersByCustomer(Customer c) {
        return orders.values().stream()
            .filter(o -> o.getCustomer().getUserId() == c.getUserId())
            .collect(Collectors.toList());
    }

    public void printOrders() {
        if (orders.isEmpty()) { System.out.println("No orders!"); return; }
        ConsoleDesign.printHeader("ORDERS");
        orders.values().forEach(System.out::println);
    }
}