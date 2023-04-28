package edu.berkeley.aepfinal;

import java.util.*;
public class StoreManagement {
    private Map<String, Store> stores;

    public StoreManagement() {
        stores = new HashMap<>();
    }
    public Map<String, Store> getStores() {
        return stores;
    }

    public boolean containsStore(String other){
        for(String name: stores.keySet()){
            if (name.equals(other)) return true;
        }
        return false;
    }
    public void addStore(String name, Store store) {
        stores.put(name, store);
    }

    public void addItemToStore(String storeName, Item item, int stock) {
        Store store = stores.get(storeName);
        if (store == null) {
            throw new IllegalArgumentException("Store not found: " + storeName);
        }
        store.addItem(item, stock);
    }

    public int storeContainsItem(String storeName, Item item){
        Store store = stores.get(storeName);
        if (store == null) {
            return -1;
        }
        return stores.get(storeName).getStock(item);
    }

    public void removeItemFromStore(String storeName, Item item) {
        Store store = stores.get(storeName);
        if (store == null) {
            throw new IllegalArgumentException("Store not found: " + storeName);
        }
        store.removeItem(item);
    }

    public void updateStockInStore(String storeName, Item item, int newStock) {
        Store store = stores.get(storeName);
        if (store == null) {
            throw new IllegalArgumentException("Store not found: " + storeName);
        }
        store.addStock(item, newStock);
    }

    public void purchaseItem(String storeName, String customerName, String customerLastName, Item item, int purchaseQ) {
        Store store = stores.get(storeName);
        if (store == null) {
            throw new IllegalArgumentException("Store not found: " + storeName);
        }
        Customer customer = new Customer(customerName, customerLastName);
        if (store.getStock(item) < purchaseQ) {
            throw new IllegalArgumentException("Not enough stock available for item: ");
        }
        store.purchaseItem(customer, item, purchaseQ);
    }

    public List<Item> getTopSellingItemInStore(String storeName, int n){
        Store store = stores.get(storeName);
        return store.getTopSellingItems(n);
    }

    public List<Item> getTopSellingItemsAcrossStores(int n) {
        Map<Item, Integer> itemSaleCounts = new HashMap<>();
        for (Store store : stores.values()) {
            for (Map.Entry<Customer, Map<Item, Integer>> entry : store.getSales().entrySet()) {
                for (Map.Entry<Item, Integer> saleEntry : entry.getValue().entrySet()) {
                    Item item = saleEntry.getKey();
                    int count = saleEntry.getValue();
                    itemSaleCounts.put(item, itemSaleCounts.getOrDefault(item, 0) + count);
                }
            }
        }

        List<Item> topSellingItems = new ArrayList<>(itemSaleCounts.keySet());
        topSellingItems.sort((item1, item2) -> itemSaleCounts.get(item2).compareTo(itemSaleCounts.get(item1)));

        return topSellingItems.subList(0, Math.min(n, topSellingItems.size()));
    }

}
