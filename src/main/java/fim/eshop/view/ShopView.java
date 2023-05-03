package fim.eshop.view;

import fim.eshop.model.Item;
import fim.eshop.model.StoreContact;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.util.List;

public class ShopView extends JFrame {
    private JTable itemsTable;
    private JTable cartItemsTable;
    private JTextField quantityField;
    private JButton addToCartButton;
    private JLabel totalLabel;
    private JButton saveButton;
    private JButton loadButton;
    private JButton mergeButton;
    private JButton contactButton;
    private JButton printReceiptButton;
    private JButton removeFromCartButton;
    private JButton saveToFileButton;
    private JButton loadFromFileButton;
    private JButton mergeFileButton;
    private JButton updateContactInfoButton;

    public ShopView() {
        setTitle("eShop");
        initComponents();
        buildLayout();
    }

    private void initComponents() {
        itemsTable = new JTable(new DefaultTableModel(new Object[][]{}, new String[]{"ID", "Name", "Price", "Quantity"}));
        cartItemsTable = new JTable(new DefaultTableModel(new Object[][]{}, new String[]{"ID", "Name", "Price", "Quantity"}));
        quantityField = new JTextField(5);
        addToCartButton = new JButton("Add to Cart");
        totalLabel = new JLabel("Total: 0.00 €");
        saveButton = new JButton("Save");
        loadButton = new JButton("Load");
        mergeButton = new JButton("Merge");
        contactButton = new JButton("Contact");
        printReceiptButton = new JButton("Print Receipt");
        addToCartButton = new JButton("Add to cart");
        removeFromCartButton = new JButton("Remove from cart");
        saveToFileButton = new JButton("Save to file");
        loadFromFileButton = new JButton("Load from file");
        mergeFileButton = new JButton("Merge file");
        updateContactInfoButton = new JButton("Update contact info");
        printReceiptButton = new JButton("Print receipt");
    }

    private void buildLayout() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(itemsTable);
        JScrollPane cartScrollPane = new JScrollPane(cartItemsTable);
        topPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel cartPanel = new JPanel();
        cartPanel.add(new JLabel("Quantity:"));
        cartPanel.add(quantityField);
        cartPanel.add(addToCartButton);
        cartPanel.add(totalLabel);
        topPanel.add(cartPanel, BorderLayout.SOUTH);
        topPanel.add(cartScrollPane, BorderLayout.EAST);

        JPanel cartItemsPanel = new JPanel(new BorderLayout());
        JScrollPane cartItemsScrollPane = new JScrollPane(cartItemsTable);
        cartItemsPanel.add(cartItemsScrollPane, BorderLayout.CENTER);
        cartItemsPanel.add(cartPanel, BorderLayout.SOUTH);

        add(cartItemsPanel, BorderLayout.EAST);

        add(topPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(saveToFileButton);
        bottomPanel.add(loadFromFileButton);
        bottomPanel.add(mergeFileButton);
        bottomPanel.add(updateContactInfoButton);
        bottomPanel.add(printReceiptButton);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    // Getter methods for components
    public JTable getItemsTable() {
        return itemsTable;
    }

    public JTable getCartItemsTable() {
        return cartItemsTable;
    }


    public JTextField getQuantityField() {
        return quantityField;
    }

    public JButton getAddToCartButton() {
        return addToCartButton;
    }


    public JButton getRemoveFromCartButton() {
        return removeFromCartButton;
    }

    public JButton getSaveToFileButton() {
        return saveToFileButton;
    }

    public JButton getLoadFromFileButton() {
        return loadFromFileButton;
    }

    public JButton getMergeFileButton() {
        return mergeFileButton;
    }

    public JButton getUpdateContactInfoButton() {
        return updateContactInfoButton;
    }

    public JLabel getTotalLabel() {
        return totalLabel;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getLoadButton() {
        return loadButton;
    }

    public JButton getMergeButton() {
        return mergeButton;
    }

    public JButton getContactButton() {
        return contactButton;
    }

    public JButton getPrintReceiptButton() {
        return printReceiptButton;
    }

    // Other getter methods for the missing components

    public Item getSelectedItem() {
        int selectedRow = itemsTable.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) itemsTable.getValueAt(selectedRow, 0);
            String name = (String) itemsTable.getValueAt(selectedRow, 1);
            double price = (double) itemsTable.getValueAt(selectedRow, 2);
            int quantity = (int) itemsTable.getValueAt(selectedRow, 3);
            return new Item(id, name, price, quantity);
        }
        return null;
    }

    public int getSelectedQuantity() {
        String quantityText = quantityField.getText();
        try {
            return Integer.parseInt(quantityText);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public void updateCart(List<Item> cartItems) {
        DefaultTableModel model = (DefaultTableModel) cartItemsTable.getModel(); // Change this line
        model.setRowCount(0);
        for (Item item : cartItems) {
            model.addRow(new Object[]{
                    item.getId(),
                    item.getName(),
                    item.getPrice(),
                    item.getQuantity(),
            });
        }
    }

    public Item getSelectedCartItem() {
        // Assuming that the same table is being used for both stock and cart items
        return getSelectedItem();
    }

    public File getSelectedFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }
        return null;
    }

    public StoreContact getUpdatedContactInfo() {
        // Assuming the contact information fields are in a separate dialog
        StoreContactDialog dialog = new StoreContactDialog(this);
        dialog.setVisible(true);
        if (dialog.isConfirmed()) {
            return dialog.getStoreContact();
        }
        return null;
    }

    public void displayReceipt(String receiptContent) {
        JTextArea textArea = new JTextArea(receiptContent);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setEditable(false);
        JOptionPane.showMessageDialog(this, scrollPane);
    }

    public void updateStock(List<Item> items) {
        DefaultTableModel tableModel = (DefaultTableModel) itemsTable.getModel();
        tableModel.setRowCount(0);

        for (Item item : items) {
            tableModel.addRow(new Object[]{
                    item.getId(),
                    item.getName(),
                    item.getPrice(),
                    item.getQuantity(),
            });
        }
    }
}
