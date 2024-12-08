package Gui;

import java.awt.*;
import java.util.function.Consumer;
import javax.swing.*;

public class CarSelection extends JFrame {
    public CarSelection(Consumer<Object[]> onCarSelected) {
        setTitle("Select Car");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Center the window

        // Data for the cars
        Object[][] cars = {
            {1, "Car 1", "Model A",499.0},
            {2, "Car 2", "Model B",400.0},
            {3, "Car 3", "Model C",299.0},
            {4, "Car 4", "Model D",999.0}
        };
        String[] columnNames = {"ID", "Name", "Model","montantParJour"};

        // Create a JTable for cars
        JTable carTable = new JTable(cars, columnNames);
        JScrollPane scrollPane = new JScrollPane(carTable);

        // Confirm button
        JButton confirmButton = new JButton("Confirm Selection");
        confirmButton.addActionListener(e -> {
            int selectedRow = carTable.getSelectedRow();
            if (selectedRow != -1) {
              
                Object[] selectedCar = {
                    carTable.getValueAt(selectedRow, 0), // ID
                    carTable.getValueAt(selectedRow, 1), // Name
                    carTable.getValueAt(selectedRow, 2),  // Model
                    carTable.getValueAt(selectedRow, 3) //montantParJour
                };
                onCarSelected.accept(selectedCar); 
                dispose(); 
            } else {
                JOptionPane.showMessageDialog(this, "Please select a car!");
            }
        });

        // Layout setup
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(confirmButton, BorderLayout.SOUTH);

        setVisible(true);
    }
}
