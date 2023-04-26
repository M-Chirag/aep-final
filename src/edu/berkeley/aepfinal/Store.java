package edu.berkeley.aepfinal;

import java.util.HashMap;
import java.util.Map;

//Understands the name, address, stock and revenue for a store
public class Store {
    private String name;
    private String address;
    private Map<Item, Integer> items;
    private double revenue;

    public Store(String name, String address) {
        this.name = name;
        this.address = address;
        this.items = new HashMap<>();
        this.revenue = 0.0;
    }
}
