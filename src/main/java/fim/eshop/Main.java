package fim.eshop;

import fim.eshop.controller.ShopController;
import fim.eshop.model.Shop;
import fim.eshop.view.ShopView;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        // Create the model, view, and controller objects
        Shop shop = new Shop();
        ShopView shopView = new ShopView();
        ShopController shopController = new ShopController(shop, shopView);

        // Set up the main application window
        SwingUtilities.invokeLater(() -> {
            shopView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            shopView.pack();
            shopView.setLocationRelativeTo(null);
            shopView.setVisible(true);
        });
    }
}
