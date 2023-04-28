package edu.berkeley.aepfinal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Understands the name, address, stock and revenue for a store
public class Store {
    private String name;
    private String address;
    private Map<Item, Integer> stock;

    private Map<Customer, Map<Item, Integer>> sales;

    public Store(String name, String address) {
        this.name = name;
        this.address = address;
        this.stock = new HashMap<>();
        this.sales = new HashMap<>();
    }

    void addItem(Item item, int stock) {
        if (this.stock.containsKey(item)) {
            this.stock.put(item, this.stock.get(item) + stock);
        } else {
            this.stock.put(item, stock);
        }
    }

    Map<Customer, Map<Item, Integer>> getSales() {
        return sales;
    }


    public int getStock(Item item) {
        return stock.getOrDefault(item, 0);
    }

    void removeItem(Item item) {
        stock.remove(item);
    }

    void addStock(Item item, int newStock) {
        if (stock.containsKey(item)) {
            stock.put(item, stock.get(item) + newStock);
        } else {
            stock.put(item, newStock);
        }
    }

    void purchaseItem(Customer customer, Item item, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        if (!stock.containsKey(item)) {
            throw new IllegalArgumentException("Item not found in store inventory");
        }
        stock.put(item, stock.get(item) - quantity);
        Map<Item, Integer> sales = this.sales.computeIfAbsent(customer, k -> new HashMap<>());
        sales.put(item, sales.getOrDefault(item, 0) + quantity);
        customer.addItemToCart(item, quantity);
    }

    double calculateRevenue() {
        double revenue = 0.0;
        for (Map<Item, Integer> sales : this.sales.values()) {
            for (Map.Entry<Item, Integer> entry : sales.entrySet()) {
                Item item = entry.getKey();
                int quantity = entry.getValue();
                revenue += item.getPrice() * quantity;
            }
        }
        return revenue;
    }

    List<Item> getTopSellingItems(int n, boolean byRevenue) {
        List<Item> itemsSold = new ArrayList<>();
        for (Map<Item, Integer> sales : this.sales.values()) {
            for (Map.Entry<Item, Integer> entry : sales.entrySet()) {
                Item item = entry.getKey();
                int quantity = entry.getValue();
                for (int i = 0; i < quantity; i++) {
                    itemsSold.add(item);
                }
            }
        }

        if (byRevenue) {
            Map<Item, Double> itemRevenues = new HashMap<>();
            for (Item item : itemsSold) {
                double revenue = item.getPrice() * (itemRevenues.getOrDefault(item, 0.0) + 1);
                itemRevenues.put(item, revenue);
            }
            List<Item> topSellingItems = new ArrayList<>(itemRevenues.keySet());
            topSellingItems.sort((item1, item2) -> itemRevenues.get(item2).compareTo(itemRevenues.get(item1)));
            return topSellingItems.subList(0, Math.min(n, topSellingItems.size()));
        } else {
            Map<Item, Integer> itemSaleCounts = new HashMap<>();
            for (Item item : itemsSold) {
                itemSaleCounts.put(item, itemSaleCounts.getOrDefault(item, 0) + 1);
            }
            List<Item> topSellingItems = new ArrayList<>(itemSaleCounts.keySet());
            topSellingItems.sort((item1, item2) -> itemSaleCounts.get(item2).compareTo(itemSaleCounts.get(item1)));
            return topSellingItems.subList(0, Math.min(n, topSellingItems.size()));
        }
    }

}
