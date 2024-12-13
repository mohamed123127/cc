package Gui;

import components.DataGridView;
import CustomControle.ButtonStyle1;
import CustomControle.ComboBoxStyle1;
import CustomControle.LabelStyle1;
import CustomControle.TextFieldStyle1;
import Entities_CRUD.Car_CRUD;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import Helpers.DbOperation;

public class Car extends JFrame {

    public Car() {
        // Main window setup
        this.setTitle("Console de gestion des voitures");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 600);

        // Main panel creation
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Header label using LabelStyle1
        LabelStyle1 headerLabel = new LabelStyle1("Gestion des voitures", 300, 30);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(headerLabel, BorderLayout.NORTH);

        // Top panel creation
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));

        // Search field using TextFieldStyle1
        TextFieldStyle1 searchField = new TextFieldStyle1(200, 30);

        // Use ComboBoxStyle1 for categories
        String[] carCategories = { "Tous", "SUV", "Berline", "Hatchback", "Pickup" };
        ComboBoxStyle1 categoryComboBox = new ComboBoxStyle1(carCategories, 150, 30);

        // Buttons using ButtonStyle1
        ButtonStyle1 addButton = new ButtonStyle1("Ajouter");
        ButtonStyle1 editButton = new ButtonStyle1("Modifier");
        ButtonStyle1 deleteButton = new ButtonStyle1("Supprimer");

        // Button actions
        addButton.addActionListener(e -> {
            new AddCar();
        });

        // Add components to the top panel
        topPanel.add(new LabelStyle1("Rechercher:"));
        topPanel.add(searchField);
        topPanel.add(categoryComboBox);
        topPanel.add(addButton);
        topPanel.add(editButton);
        topPanel.add(deleteButton);

        // Add the top panel to the main panel
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Setup data table
        String[] columnNames = { "ID", "Marque", "Modele", "Annee", "Type", "Carburant", "Prix par jour", "Etat" };
        ResultSet data = Car_CRUD.GetAll();

        // Use DataGridView for displaying data
        DataGridView dataGridView = new DataGridView(columnNames, data);
        mainPanel.add(dataGridView, BorderLayout.CENTER);

        // Handle Edit button click
        editButton.addActionListener(e -> {
            Object[] selectedRow = dataGridView.getSelectedRowData();
            if (selectedRow != null) {
                // Retrieve selected data
                String id = selectedRow[0].toString();
                String marque = selectedRow[1].toString();
                String modele = selectedRow[2].toString();
                String annee = selectedRow[3].toString();
                String type = selectedRow[4].toString();
                String carburant = selectedRow[5].toString();
                String prix = selectedRow[6].toString();

                // Open AddCar window in edit mode
                AddCar editCarWindow = new AddCar(false);
                editCarWindow.setVisible(true);

                // TODO: Populate fields in AddCar with the selected data
                // editCarWindow.populateFields(id, marque, modele, annee, type, carburant, prix);
            } else {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner une ligne pour modifier.", "Avertissement",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        // Handle Delete button click
        deleteButton.addActionListener(e -> {
            Object[] selectedRow = dataGridView.getSelectedRowData();
            if (selectedRow != null) {
                int confirm = JOptionPane.showConfirmDialog(this,
                        "Êtes-vous sûr de vouloir supprimer la voiture avec ID: " + selectedRow[0] + "?",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    // TODO: Implement delete functionality
                    JOptionPane.showMessageDialog(this, "Voiture supprimée avec succès.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner une ligne pour supprimer.", "Avertissement",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        // Set up and display the main window
        this.add(mainPanel);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
