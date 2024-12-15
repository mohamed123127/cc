package Gui;
import javax.swing.*;
import java.awt.*;
import Helpers.DbOperation;
import Entities_CRUD.User_CRUD;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class Statistiques extends JFrame {
    private JPanel centerPanel; // Current center panel reference

    public Statistiques() {
        setTitle("Car Rental Management System - Reporting Section");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setVisible(true);
        
        // Top Panel with ComboBox
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.BLUE);
        titlePanel.setLayout(new GridLayout(1, 2));
    
        JLabel titleLabel = new JLabel("Car Rental Management System", JLabel.LEFT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titlePanel.add(titleLabel);
    
        JPanel filterPanel = new JPanel();
        DefaultComboBoxModel<String> filter = new DefaultComboBoxModel<>();
        filter.addElement("Vehicle Statistics");
        filter.addElement("Customer Statistics");
        filter.addElement("Revenue Statistics");
    
        JComboBox<String> filterCombo = new JComboBox<>(filter);
        filterCombo.setSelectedItem("Vehicle Statistics"); // Set default selection
        filterCombo.setPreferredSize(new Dimension(200, 25));
        filterPanel.add(filterCombo);
        titlePanel.add(filterPanel);
    
        add(titlePanel, BorderLayout.NORTH);
    
        // Initial Center Panel with Vehicle Statistics
        centerPanel = new StatistiquesVehicule().StatistiquesVehicule();  // Set initial panel to Vehicle Statistics
        add(centerPanel, BorderLayout.CENTER);
    
        // South Panel (Rental History Table)
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BorderLayout());
    
        String[] columnNames = {"Vehicle Name", "Client Name", "Rental Dates", "Status"};
        Object[][] data = {
                {"SUV", "John Doe", "01/12/2024 - 03/12/2024", "Completed"},
                {"Sedan", "Jane Smith", "02/12/2024 - 04/12/2024", "Ongoing"},
                {"Truck", "Alice Johnson", "01/12/2024 - 02/12/2024", "Completed"}
        };
        JTable rentalHistoryTable = new JTable(data, columnNames);
        JScrollPane tableScrollPane = new JScrollPane(rentalHistoryTable);
        southPanel.add(tableScrollPane, BorderLayout.CENTER);
        southPanel.setPreferredSize(new Dimension(this.getWidth(), 150));
        add(southPanel, BorderLayout.SOUTH);
    
        // ActionListener for ComboBox
        filterCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) filterCombo.getSelectedItem();
                
                JPanel newCenterPanel = switchCenterPanel(selected); // Get the panel based on the selection
    
                replaceCenterPanel(newCenterPanel); // Replace the current center panel
            }
        });
    }
    

    // Replace the center panel
    private void replaceCenterPanel(JPanel newPanel) {
        remove(centerPanel); // Remove the old panel
        centerPanel = newPanel; // Update reference
        add(centerPanel, BorderLayout.CENTER); // Add the new panel
        revalidate(); // Refresh layout
        repaint(); // Repaint frame
    }

    // Switch center panel based on the ComboBox value
    private JPanel switchCenterPanel(String selected) {
        switch (selected) {
            
            case "Vehicle Statistics":
                return new StatistiquesVehicule().StatistiquesVehicule();
            case "Customer Statistics":
                return new StatistiquesCustomers().StatistiquesCustomers();
            case "Revenue Statistics":
                return new StatistiquesRevenue().StatistiquesRevenue(); 
            default:
                JPanel defaultPanel = new StatistiquesVehicule().StatistiquesVehicule();
                defaultPanel.setBackground(Color.LIGHT_GRAY);
                defaultPanel.add(new JLabel("Default Center Panel"));
                return defaultPanel;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Statistiques::new);
    }
}