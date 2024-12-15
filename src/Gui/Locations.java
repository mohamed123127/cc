package Gui;

import Entities_CRUD.Locations_CRUD;
import Helpers.DateLabelFormatter;
import components.DataGridView;
import java.awt.*;
import java.sql.ResultSet;
import java.util.Properties;
import javax.swing.*;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class Locations extends JFrame {
    private JLabel selectedClientLabel; 
    public DataGridView DataGridView;

    public Locations() {
        setTitle("Ajouter une réservation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 500);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));

        JButton selectClientButton = new JButton("Sélectionner un client");
        selectedClientLabel = new JLabel("Client sélectionné : Aucun");
        selectClientButton.addActionListener(e -> {
            new ClientSelection(selectedClient -> {
                selectedClientLabel.setText("Client sélectionné : " + selectedClient);
            });
        });

        JLabel selectedCarLabel = new JLabel("Voiture sélectionnée :");
        JComboBox<String> selectCar = new JComboBox<>(new String[]{"Voiture 1", "Voiture 2", "Voiture 3"});
        JLabel dateLabel = new JLabel("Sélectionner une date :");

        Properties properties = new Properties();
        properties.put("text.today", "Aujourd'hui");
        properties.put("text.month", "Mois");
        properties.put("text.year", "Année");
        JDatePanelImpl datePanel = new JDatePanelImpl(new UtilDateModel(), properties);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        datePicker.setPreferredSize(new Dimension(150, 30));

        JButton addButton = new JButton("Ajouter");
        addButton.addActionListener(e->{
            String Role = Locations_CRUD.IsExsists("ali.ahmed@example.com", "password123");
            if(Role != null){
                JOptionPane.showMessageDialog(null, Role);
            }
        });
        JButton returnCarButton = new JButton("Restituer la voiture");
        returnCarButton.addActionListener(e -> {
            var selectedRow = DataGridView.getSelectedRowData();
            if (selectedRow != null) {
                CarDamageEvaluation instance = new CarDamageEvaluation(selectedRow, this);
                instance.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Aucune ligne sélectionnée");
            }
        });

        panel.add(selectedClientLabel);     
        panel.add(selectClientButton);      
        panel.add(selectedCarLabel); 
        panel.add(selectCar);               
        panel.add(dateLabel);               
        panel.add(datePicker);              
        panel.add(addButton); 
        panel.add(returnCarButton);
        add(panel, BorderLayout.NORTH);

        String[] columnNames = {"ID Réservation", "Client", "Voiture", "Statut", "Montant Total", "Date Début", "Date Fin", "ID Client", "ID Voiture"};
        ResultSet data = Locations_CRUD.GetAll();
        DataGridView = new DataGridView(columnNames, data, new int[]{7, 8});
        add(DataGridView, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Locations();
    }
}
