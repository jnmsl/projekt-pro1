package fim.eshop.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
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
        try (FileWriter fileWriter = new FileWriter(file)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(stock, fileWriter);
        }
    }

    public void loadFromFile(File file) throws IOException {
        try (FileReader fileReader = new FileReader(file)) {
            Gson gson = new Gson();
            Type stockType = new TypeToken<Stock>() {
            }.getType();
            stock = gson.fromJson(fileReader, stockType);
        }
    }

    public void mergeFile(File file) throws IOException {
        try (FileReader fileReader = new FileReader(file)) {
            Gson gson = new Gson();
            Type stockType = new TypeToken<Stock>() {
            }.getType();
            Stock mergedStock = gson.fromJson(fileReader, stockType);
            stock.merge(mergedStock);
        }
    }
}
