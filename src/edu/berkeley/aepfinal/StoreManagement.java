package edu.berkeley.aepfinal;

import java.util.*;
public class StoreManagement {
    private Map<String, Store> stores= new HashMap<>();

    public boolean containsStore(String other){
        for(String name: stores.keySet()){
            if (name.equals(other)) return true;
        }
        return false;
    }
    public void addStore(String name, String address) {
        stores.put(name, new Store(name, address));
    }

}
