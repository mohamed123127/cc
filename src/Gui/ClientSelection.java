package Gui;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class ClientSelection extends JFrame {
    public ClientSelection(Consumer<String> onClientSelected) {
        setTitle("Select Client");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);

        // قائمة العملاء
        String[] clients = {"Client 1", "Client 2", "Client 3", "Client 4"};
        JList<String> clientList = new JList<>(clients);
        clientList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(clientList);

         
        JButton confirmButton = new JButton("Confirm Selection");
        confirmButton.addActionListener(e -> {
            String selectedClient = clientList.getSelectedValue();
            if (selectedClient != null) {
                onClientSelected.accept(selectedClient); // تمرير العميل المختار
                dispose(); // إغلاق النافذة
            } else {
                JOptionPane.showMessageDialog(this, "Please select a client!");
            }
        });

        
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(confirmButton, BorderLayout.SOUTH);

        setVisible(true);
    }
}
