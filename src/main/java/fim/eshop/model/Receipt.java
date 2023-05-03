package fim.eshop.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Receipt {
    private ShoppingCart shoppingCart;
    private String storeName;
    private String storeAddress;
    private String storeEmail;
    private Date date;

    public Receipt(ShoppingCart shoppingCart, String storeName, String storeAddress, String storeEmail) {
        this.shoppingCart = shoppingCart;
        this.storeName = storeName;
        this.storeAddress = storeAddress;
        this.storeEmail = storeEmail;
        this.date = new Date();
    }

    public String generateReceipt() {
        StringBuilder receiptContent = new StringBuilder();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        receiptContent.append("====================================\n");
        receiptContent.append(storeName).append("\n");
        receiptContent.append(storeAddress).append("\n");
        receiptContent.append(storeEmail).append("\n");
        receiptContent.append("====================================\n");
        receiptContent.append(dateFormat.format(date)).append("\n");
        receiptContent.append("------------------------------------\n");

        for (Item item : shoppingCart.getItems()) {
            receiptContent.append(item.getName()).append("\n");
            receiptContent.append(item.getQuantity()).append(" x ");
            receiptContent.append(String.format("%.2f", item.getPrice())).append(" ");
        }

        receiptContent.append("------------------------------------\n");
        receiptContent.append("Total: ").append(String.format("%.2f", shoppingCart.getTotal())).append(" €\n");
        receiptContent.append("====================================\n");

        return receiptContent.toString();
    }
}
