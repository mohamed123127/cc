package Gui;

import Helpers.DbOperation;
import CustomControle.TextFieldStyle1;
import Entities_CRUD.Car_CRUD;
import Entities_CRUD.Locations_CRUD;
import CustomControle.LabelStyle1;
import CustomControle.ComboBoxStyle1;
import CustomControle.ButtonStyle1;

import java.awt.*;
import javax.swing.*;

public class AddCar extends JFrame {
    public boolean isAddOperation = false;
    private JButton submitButton;

    public AddCar() {
        this.setTitle("Console de ajouter des voitures");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700, 600);

        UIManager.put("Panel.background", new Color(240, 244, 248));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(new Color(240, 244, 248));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(15, 15, 15, 15);

        // Use the custom LabelStyle1 and TextFieldStyle1
        LabelStyle1 marqueLabel = new LabelStyle1("Marque:");
        TextFieldStyle1 marqueField = new TextFieldStyle1();
        addComponent(mainPanel, marqueLabel, marqueField, gbc, 0);

        LabelStyle1 modeleLabel = new LabelStyle1("Modèle:");
        TextFieldStyle1 modeleField = new TextFieldStyle1();
        addComponent(mainPanel, modeleLabel, modeleField, gbc, 1);

        LabelStyle1 anneeLabel = new LabelStyle1("Année:");
        TextFieldStyle1 anneeField = new TextFieldStyle1();
        addComponent(mainPanel, anneeLabel, anneeField, gbc, 2);

        LabelStyle1 typeLabel = new LabelStyle1("Type:");
        String[] types = { "Électrique", "Turbo Charge", "Super Charge" };
        ComboBoxStyle1 typeComboBox = new ComboBoxStyle1(types);
        addComponent(mainPanel, typeLabel, typeComboBox, gbc, 3);

        LabelStyle1 carburantLabel = new LabelStyle1("Carburant:");
        String[] carburants = { "Diesel", "Essence", "Carburant Fusée" };
        ComboBoxStyle1 carburantComboBox = new ComboBoxStyle1(carburants);
        addComponent(mainPanel, carburantLabel, carburantComboBox, gbc, 4);

        LabelStyle1 prixLabel = new LabelStyle1("Prix par jour:");
        TextFieldStyle1 prixField = new TextFieldStyle1();
        addComponent(mainPanel, prixLabel, prixField, gbc, 5);

        // Submit button
        submitButton = new ButtonStyle1("Add");
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(submitButton, gbc);

        // Database connection test
        DbOperation dbOp = new DbOperation();
        if (!dbOp.testConnection()) {
            JOptionPane.showMessageDialog(this, "Échec de la connexion à la base de données. Vérifiez la connexion.",
                    "Erreur", JOptionPane.ERROR_MESSAGE);
        }

        if (isAddOperation) {
            submitButton.addActionListener(e -> {
                String marque = marqueField.getText();
                String modele = modeleField.getText();
                String annee = anneeField.getText();
                String type = (String) typeComboBox.getSelectedItem();
                String carburant = (String) carburantComboBox.getSelectedItem();
                String prix = prixField.getText();

                String tableName = "vehicule";
                String columns = "marque, modele, annee, type, carburant, prix_location_jour, etat";
                String values = "'" + marque + "', '" + modele + "', '" + annee + "', '" + type + "', '" + carburant
                        + "', '" + prix + "',' disponible'";

                if (dbOp.Insert(tableName, columns, values)) {
                    JOptionPane.showMessageDialog(this, "Véhicule ajouté avec succès!", "Succès",
                            JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Échec de l'ajout du véhicule.\nErreur: " + dbOp.getLastError(),
                            "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            });
        }

        this.add(mainPanel);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public AddCar(boolean IsAddOperation) {
        this();
        isAddOperation = IsAddOperation;
        if (!isAddOperation) {
            submitButton.setText("Edit");
        }
    }

    private void addComponent(JPanel panel, JLabel label, JComponent component, GridBagConstraints gbc, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1.0;
        panel.add(component, gbc);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AddCar::new);
    }
}
