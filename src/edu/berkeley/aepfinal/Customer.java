package edu.berkeley.aepfinal;

import java.util.HashMap;
import java.util.Map;

//Understands details and shopping cart items for a customer.
public class Customer {
    private String firstName;
    private String lastName;

    private Map<Item, Integer> cart;

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cart = new HashMap<>();
    }

    public void addItemToCart(Item item, int quantity) {
        cart.put(item, cart.getOrDefault(item, 0) + quantity);
    }

}
