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

    public Item getItem(Item item){
        return items.containsKey(item)? item:
                null;
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

    public void purchaseItem(Customer customer, Item item, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        if (!items.containsKey(item)) {
            throw new IllegalArgumentException("Item not found in store inventory");
        }
        items.put(item, items.get(item) - quantity);
        double price = item.getPrice() * quantity;
        revenue += price;
        customer.addItemToCart(item, quantity);
    }

    public double getRevenue() {
        return revenue;
    }
}
