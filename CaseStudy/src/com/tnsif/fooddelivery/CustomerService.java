package com.tnsif.fooddelivery;

import java.util.HashMap;
import java.util.Map;

public class CustomerService {
    private Map<Integer, Customer> customers = new HashMap<>();

    public void addCustomer(Customer c) {
        customers.put(c.getUserId(), c);
    }

    public Customer getCustomer(int id) {
        return customers.get(id);
    }
}
