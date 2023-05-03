package fim.eshop.model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Item> items;

    public ShoppingCart() {
        items = new ArrayList<>();
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item item, int quantity) {
        // Create a new Item instance to avoid modifying the original item in the stock
        Item itemInCart = new Item(item.getId(), item.getName(), item.getPrice(), quantity);
        items.add(itemInCart);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public void clear() {
        items.clear();
    }

    public double getTotal() {
        double total = 0.0;
        for (Item item : items) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }
}

