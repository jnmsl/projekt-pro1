package fim.eshop.model;

import java.io.*;
import java.util.List;

public class Shop implements Serializable {
    private Stock stock;
    private ShoppingCart shoppingCart;
    private StoreContact storeContact;

    public Shop() {
        this.stock = new Stock();
        this.shoppingCart = new ShoppingCart();
        this.storeContact = new StoreContact();
    }

    public Stock getStock() {
        return stock;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public List<Item> getCartItems() {
        return shoppingCart.getItems();
    }

    public void addToCart(Item item, int quantity) {
        shoppingCart.addItem(item, quantity);
    }

    public StoreContact getStoreContact() {
        return storeContact;
    }

    public void saveToFile(File file) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(stock);
        }
    }

    public void loadFromFile(File file) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            if (obj instanceof Stock) {
                stock = (Stock) obj;
            } else {
                throw new IOException("Invalid data format in the file.");
            }
        } catch (ClassNotFoundException e) {
            throw new IOException("Failed to load data from the file.", e);
        }
    }

    public void mergeFile(File file) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            if (obj instanceof Stock) {
                Stock mergedStock = (Stock) obj;
                stock.merge(mergedStock);
            } else {
                throw new IOException("Invalid data format in the file.");
            }
        } catch (ClassNotFoundException e) {
            throw new IOException("Failed to merge data from the file.", e);
        }
    }
}
