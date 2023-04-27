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
    public void addStore(String name, String address) {
        stores.put(name, new Store(name, address));
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

}
