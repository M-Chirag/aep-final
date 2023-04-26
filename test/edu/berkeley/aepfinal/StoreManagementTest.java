package edu.berkeley.aepfinal;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class StoreManagementTest {

    static StoreManagement stores = new StoreManagement();

    static {
        stores.addStore("Costco", "Berkeley");
    }

    @Test
    public void testAddStore() {
        assertTrue(stores.containsStore("Costco"));
    }
}
