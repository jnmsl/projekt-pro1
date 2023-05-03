package fim.eshop.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Stock implements Serializable {
    private List<Item> items;

    public Stock() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public Item findItemById(int id) {
        for (Item item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public void merge(Stock otherStock) {
        for (Item item : otherStock.getItems()) {
            Item existingItem = findItemById(item.getId());
            if (existingItem != null) {
                existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
            } else {
                items.add(item);
            }
        }
    }
}
