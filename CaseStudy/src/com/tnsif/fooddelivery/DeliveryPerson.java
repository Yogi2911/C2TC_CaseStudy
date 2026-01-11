package com.tnsif.fooddelivery;

public class DeliveryPerson {
    private int id;
    private String name;
    private long contactNo;

    public DeliveryPerson(int id, String name, long contactNo) {
        this.id = id;
        this.name = name;
        this.contactNo = contactNo;
    }

    public int getId() { return id; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return name + " (ID:" + id + ")";
    }
}
