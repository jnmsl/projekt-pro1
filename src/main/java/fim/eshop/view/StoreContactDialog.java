package fim.eshop.view;

import fim.eshop.model.StoreContact;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StoreContactDialog extends JDialog {
    private JTextField storeNameField;
    private JTextField storeAddressField;
    private JTextField storeEmailField;
    private JTextField storePhoneField;
    private JButton okButton;
    private JButton cancelButton;

    private boolean confirmed = false;

    public StoreContactDialog(JFrame parent) {
        super(parent, "Update Contact Information", true);
        initComponents();
        buildLayout();
        setListeners();
        pack();
        setLocationRelativeTo(parent);
    }

    private void initComponents() {
        storeNameField = new JTextField(20);
        storeAddressField = new JTextField(20);
        storeEmailField = new JTextField(20);
        storePhoneField = new JTextField(20);
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
    }

    private void buildLayout() {
        setLayout(new BorderLayout());
        JPanel fieldsPanel = new JPanel(new GridLayout(4, 2));

        fieldsPanel.add(new JLabel("Store Name:"));
        fieldsPanel.add(storeNameField);
        fieldsPanel.add(new JLabel("Store Address:"));
        fieldsPanel.add(storeAddressField);
        fieldsPanel.add(new JLabel("Store Email:"));
        fieldsPanel.add(storeEmailField);
        fieldsPanel.add(new JLabel("Store Phone:"));
        fieldsPanel.add(storePhoneField);

        add(fieldsPanel, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(okButton);
        buttonsPanel.add(cancelButton);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    private void setListeners() {
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmed = true;
                setVisible(false);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmed = false;
                setVisible(false);
            }
        });
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public StoreContact getStoreContact() {
        return new StoreContact(
                storeNameField.getText(),
                storeAddressField.getText(),
                storeEmailField.getText(),
                storePhoneField.getText()
        );
    }
}
