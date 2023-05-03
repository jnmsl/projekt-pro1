package fim.eshop.controller;

import fim.eshop.model.Item;
import fim.eshop.model.Receipt;
import fim.eshop.model.Shop;
import fim.eshop.model.StoreContact;
import fim.eshop.view.ShopView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ShopController {
    private Shop shop;
    private ShopView view;

    public ShopController(Shop shop, ShopView view) {
        this.shop = shop;
        this.view = view;
        initView();
        initController();

        // Add sample items to the shop
        addSampleItems();

        // Add sample contact info to the shop
        addSampleContactInfo();

        // Update the view with the initial data from the model
        view.updateStock(shop.getStock().getItems());
    }

    private void initView() {
        // Update the view with the initial data from the model
    }

    private void initController() {
        // Add action listeners for user interactions in the view
        view.getAddToCartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToCart();
            }
        });

        view.getRemoveFromCartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeFromCart();
            }
        });

        view.getSaveToFileButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveToFile();
            }
        });

        view.getLoadFromFileButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadFromFile();
            }
        });

        view.getMergeFileButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mergeFile();
            }
        });

        view.getUpdateContactInfoButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateContactInfo();
            }
        });

        view.getPrintReceiptButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printReceipt();
            }
        });
    }

    public void addToCart() {
        Item selectedItem = view.getSelectedItem();
        int quantity = view.getSelectedQuantity();
        if (selectedItem != null && quantity > 0) {
            shop.addToCart(selectedItem, quantity);
            List<Item> cartItems = shop.getCartItems();
            double totalPrice = 0;

            DefaultTableModel model = (DefaultTableModel) view.getCartItemsTable().getModel();
            model.setRowCount(0);
            for (Item item : cartItems) {
                model.addRow(new Object[]{
                        item.getId(),
                        item.getName(),
                        item.getPrice(),
                        item.getQuantity(),
                });
                totalPrice += item.getPrice() * item.getQuantity();
            }
            view.getTotalLabel().setText("Total: " + String.format("%.2f", totalPrice) + " €");
        } else {
            JOptionPane.showMessageDialog(view, "Please select an item and enter a valid quantity.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeFromCart() {
        // Get the selected item from the view
        Item selectedItem = view.getSelectedCartItem();

        // Remove the item from the shopping cart
        shop.getShoppingCart().removeItem(selectedItem);

        // Update the view to reflect the change
        view.updateCart(shop.getShoppingCart().getItems());
    }

    private void saveToFile() {
        // Get the selected file from the view
        File selectedFile = view.getSelectedFile();

        // Save the data to the file
        try {
            shop.saveToFile(selectedFile);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(view, "Error saving data to file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadFromFile() {
        // Get the selected file from the view
        File selectedFile = view.getSelectedFile();

        // Load the data from the file
        try {
            shop.loadFromFile(selectedFile);
            view.updateStock(shop.getStock().getItems());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(view, "Error loading data from file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mergeFile() {
        // Get the selected file from the view
        File selectedFile = view.getSelectedFile();

        // Load the data from the file and merge it with the current stock
        try {
            shop.mergeFile(selectedFile);
            view.updateStock(shop.getStock().getItems());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(view, "Error merging data from file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateContactInfo() {
        // Get the updated contact information from the view
        StoreContact updatedContact = view.getUpdatedContactInfo();

        // Update the store's contact information
        shop.getStoreContact().setStoreName(updatedContact.getStoreName());
        shop.getStoreContact().setStoreAddress(updatedContact.getStoreAddress());
        shop.getStoreContact().setStoreEmail(updatedContact.getStoreEmail());
        shop.getStoreContact().setStorePhone(updatedContact.getStorePhone());
    }

    private void printReceipt() {
        // Generate a receipt based on the shopping cart and store contact information
        Receipt receipt = new Receipt(shop.getShoppingCart(), shop.getStoreContact().getStoreName(), shop.getStoreContact().getStoreAddress(), shop.getStoreContact().getStoreEmail());
        String receiptContent = receipt.generateReceipt();

        // Display the receipt in a pop-up window
        view.displayReceipt(receiptContent);
    }

    private void addSampleItems() {
        shop.getStock().addItem(new Item(1, "Laptop", 750.0, 10));
        shop.getStock().addItem(new Item(2, "Smartphone", 500.0, 15));
        shop.getStock().addItem(new Item(3, "Headphones", 80.0, 25));
        shop.getStock().addItem(new Item(4, "Keyboard", 40.0, 30));
        shop.getStock().addItem(new Item(5, "Mouse", 20.0, 35));
    }

    private void addSampleContactInfo() {
        shop.getStoreContact().setStoreName("My Store");
        shop.getStoreContact().setStoreAddress("123 Main Street, City, Country");
        shop.getStoreContact().setStoreEmail("email@email.com");
        shop.getStoreContact().setStorePhone("123456789");
    }


// Other methods for handling user interactions
}