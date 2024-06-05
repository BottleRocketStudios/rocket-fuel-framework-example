package example.assertions;

/**
 * Created by ford.arnett on 8/30/18
 */
public enum AssertionCategories {
    Login("loginPage"),
    Cart("cartPage"),
    Checkout("checkoutPage"),
    ItemInventory("itemInventoryPage"),
    Inventory("inventoryPage");


    private String category;

    AssertionCategories(String category) {
        this.category = category;
    }

    public String toString() {
        return category;
    }
}
