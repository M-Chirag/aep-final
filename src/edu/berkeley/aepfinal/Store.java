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

    public void addItem(Item item, int stock) {
        if (items.containsKey(item)) {
            items.put(item, items.get(item) + stock);
        } else {
            items.put(item, stock);
        }
    }

    public int getStock(Item item) {
        return items.getOrDefault(item, 0);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public void addStock(Item item, int newStock) {
        if (items.containsKey(item)) {
            items.put(item, items.get(item) + newStock);
        } else {
            items.put(item, newStock);
        }
    }
}
