package edu.berkeley.aepfinal;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class StoreManagementTest {

    static StoreManagement stores = new StoreManagement();
    static Store Costco = new Store("Costco", "Berkeley");
    static Store WholeFoods = new Store("Whole Foods", "Alameda");
    static Store Safeway = new Store("Safeway", "Albany");
    static Item bread = new Item("Bread", 8.0);
    static Item eggs = new Item("Eggs", 10.0);
    static Item apples = new Item("Apples", 5.0);
    static Item cakes = new Item("Cakes", 7.0);

    static {
        stores.addStore("Costco", Costco);
        stores.addStore("Whole Foods", WholeFoods);
        stores.addStore("Safeway", Safeway);

        stores.addItemToStore("Costco", apples, 10);
        stores.addItemToStore("Costco", bread, 5);
        stores.addItemToStore("Costco", eggs, 10);
        stores.addItemToStore("Safeway", cakes, 10);
        stores.addItemToStore("Safeway", apples, 20);
        stores.addItemToStore("Safeway", bread, 5);
        stores.addItemToStore("Whole Foods", eggs, 5);
        stores.addItemToStore("Whole Foods", bread, 10);

        // Make some sales in each store
        stores.purchaseItem("Costco", "Michael", "Scott", bread, 3);
        stores.purchaseItem("Costco", "Jim", "Halpert", apples, 7);
        stores.purchaseItem("Safeway", "Gordon", "Ramsay", cakes, 2);
        stores.purchaseItem("Whole Foods", "John", "Doe", bread, 2);
        stores.purchaseItem("Whole Foods", "John", "Doe", eggs, 3);
        stores.purchaseItem("Whole Foods", "Jane", "Doe", bread, 1);
        stores.purchaseItem("Safeway", "Michael", "Scott", bread, 2);
        stores.purchaseItem("Safeway", "Michael", "Scott", cakes, 2);
        stores.purchaseItem("Safeway", "Jim", "Halpert", apples, 5);

    }

    @Test
    public void storesShouldCountainCostco() {
        assertTrue(stores.containsStore("Costco"));
    }

    @Test
    public void costcoShouldContainThreeApples() {
        assertEquals(3, stores.storeContainsItem("Costco", apples));
    }

    @Test
    public void costcoShouldNotContainEggs() {
        stores.removeItemFromStore("Costco", eggs);
        assertEquals(0, stores.storeContainsItem("Costco", eggs));
    }

    @Test
    public void wholeFoodsShouldContainTwelveEggs() {
        stores.updateStockInStore("Whole Foods", eggs, 10);
        assertEquals(12, stores.getStores().get("Whole Foods").getStock(eggs));
    }

    @Test
    public void safewayShouldHaveSixCakesAfterPurchase() {
        assertEquals(6, stores.storeContainsItem("Safeway", cakes));
    }

    @Test
    public void costcoTotalRevenueShouldBe64() {
        assertEquals(69.0, stores.getStores().get("Safeway").calculateRevenue(),0.01);
    }

    @Test
    public void topSellingItemsInSafewayShouldBeCakesAndApples() {
        // Get the top selling items and check that they are in the correct order
        List<Item> topSellingItems = stores.getTopSellingItemInStore("Safeway", 2, false);
        assertEquals(apples.getName(), topSellingItems.get(0).getName());
        assertEquals(cakes.getName(), topSellingItems.get(1).getName());
    }

    @Test
    public void topSellingItemsInSafewayByRevenueShouldBe() {
        // Get the top selling items and check that they are in the correct order
        List<Item> topSellingItems = stores.getTopSellingItemInStore("Safeway", 2, true);
        assertEquals(apples.getName(), topSellingItems.get(0).getName());
        assertEquals(cakes.getName(), topSellingItems.get(1).getName());
    }

    @Test
    public void testGetTopSellingItemsAmongAllStores() {
        // Geting the top selling items among all stores and check that they are in the correct order
        List<Item> topSellingItems = stores.getTopSellingItemsAcrossStores(3, false);
        assertEquals(apples.getName(), topSellingItems.get(0).getName());
        assertEquals(bread.getName(), topSellingItems.get(1).getName());
        assertEquals( cakes.getName(), topSellingItems.get(2).getName());
    }

    @Test
    public void testTopThreeItemsAcrossAllStoresByRevenue() {
        // Geting the top selling items among all stores and check that they are in the correct order
        List<Item> topSellingItemsByRevenue = stores.getTopSellingItemsAcrossStores(3, true);
        assertEquals(bread.getName(), topSellingItemsByRevenue.get(0).getName());
        assertEquals(apples.getName(), topSellingItemsByRevenue.get(1).getName());
        assertEquals( eggs.getName(), topSellingItemsByRevenue.get(2).getName());
    }

}
