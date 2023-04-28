package edu.berkeley.aepfinal;

import org.junit.Test;

import static org.junit.Assert.*;

public class StoreManagementTest {

    static StoreManagement stores = new StoreManagement();
    static Store Costco = new Store("Costco", "Berkeley");
    static Store WholeFoods = new Store("Whole Foods", "Alameda");
    static Store Safeway = new Store("Safeway", "Albany");
    static Item eggs = new Item("Eggs", 10.0);
    static Item apples = new Item("Apples", 5.0);
    static Item cakes = new Item("Cakes", 7.0);

    static {
        stores.addStore("Costco", Costco);
        stores.addStore("Safeway", WholeFoods);
        stores.addStore("Whole Foods", Safeway);
    }

    @Test
    public void storesShouldCountainCostco() {
        assertTrue(stores.containsStore("Costco"));
    }

    @Test
    public void costcoShouldContainFiveApples() {
        stores.addItemToStore("Costco", apples, 5);
        assertEquals(5, stores.storeContainsItem("Costco", apples));
    }

    @Test
    public void costcoShouldNotContainEggs() {
        stores.addItemToStore("Costco", eggs, 10);
        stores.removeItemFromStore("Costco", eggs);
        assertEquals(0, stores.storeContainsItem("Costco", eggs));
    }

    @Test
    public void wholeFoodsShouldContainFifteenEggs() {
        stores.addItemToStore("Whole Foods", eggs, 5);
        stores.updateStockInStore("Whole Foods", eggs, 10);
        assertEquals(15, stores.getStores().get("Whole Foods").getStock(eggs));
    }

    @Test
    public void safewayShouldHaveThreeCakesAfterPurchase() {
        stores.addItemToStore("Safeway", cakes, 5);
        stores.purchaseItem("Safeway", "Gordon", "Ramsy", cakes, 2);
        assertEquals(3, stores.storeContainsItem("Safeway", cakes));
    }


}
