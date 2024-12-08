package Gui;

import java.awt.*;
import java.util.function.Consumer;
import javax.swing.*;

public class ClientSelection extends JFrame {
 
    public ClientSelection(Consumer<Object[]> onClientSelected) {
        setTitle("Select Client");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);

  
        Object[][] clients = {
            {1, "Nazim", "nazim@gmail.com"},
            {2, "Yasser", "yasser@gmail.com"},
            {3, "Moh.L", "mohl@gmail.com"},
            {4, "Moh.T", "moht@gmail.com"},
            {5, "Moh.S", "mohS@gmail.com"}
        };
        String[] columnNames = {"ID", "Name", "Email"};

    
        JTable clientTable = new JTable(clients, columnNames);
        JScrollPane scrollPane = new JScrollPane(clientTable);

   
        JButton confirmButton = new JButton("Confirm Selection");
        confirmButton.addActionListener(e -> {
            int selectedRow = clientTable.getSelectedRow();
            if (selectedRow != -1) {
             
                Object[] selectedClient = {
               clientTable.getValueAt(selectedRow, 0), // ID
                 clientTable.getValueAt(selectedRow, 1), // Name
                    clientTable.getValueAt(selectedRow, 2)  // Email
                };
                onClientSelected.accept(selectedClient); 
                dispose(); 
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
