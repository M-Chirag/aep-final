package edu.berkeley.aepfinal;

import org.junit.Test;

import static org.junit.Assert.*;

public class StoreManagementTest {

    static StoreManagement stores = new StoreManagement();

    static {
        stores.addStore("Costco", "Berkeley");
        stores.addStore("Safeway", "Albany");
        stores.addStore("Whole Foods", "Alameda");


    }

    @Test
    public void storesShouldCountainCostco() {
        assertTrue(stores.containsStore("Costco"));
    }

    @Test
    public void costcoShouldContainFiveApples() {
        Item item = new Item("apple", 10.0);
        stores.addItemToStore("Costco", item, 5);
        assertEquals(5, stores.storeContainsItem("Costco", item));
    }

}
