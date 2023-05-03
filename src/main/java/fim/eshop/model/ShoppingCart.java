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
        boolean itemExists = false;

        for (Item cartItem : items) {
            if (cartItem.getId() == item.getId()) {
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                itemExists = true;
                break;
            }
        }

        if (!itemExists) {
            Item newItem = new Item(item.getId(), item.getName(), item.getPrice(), quantity);
            items.add(newItem);
        }
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

    public int getItemQuantity(Item item) {
        for (Item cartItem : items) {
            if (cartItem.getId() == item.getId()) {
                return cartItem.getQuantity();
            }
        }
        return 0;
    }
}

