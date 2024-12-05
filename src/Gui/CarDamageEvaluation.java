package Gui;

import Entities_CRUD.Historique_CRUD;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.*;

public class CarDamageEvaluation extends JFrame{
    CarDamageEvaluation(Object[] LocationData){
        this.setSize(350, 200);
        this.setTitle("Évaluation de l'etat de voiture");
        Container mainContainer = this.getContentPane();
        //mainContainer.setLayout(new BoxLayout(mainContainer,BoxLayout.Y_AXIS));
        //add inputs
        Container InputsContainer = new Container();

        mainContainer.add(InputsContainer,BorderLayout.CENTER);
        InputsContainer.setLayout(new FlowLayout(FlowLayout.CENTER,10,3));
        Container C1 = new Container();
        C1.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel CarStatusLabel = new JLabel("Etat de voiture:");
        CarStatusLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 5));  // إضافة margin حول الـ JLabel
        CarStatusLabel.setFont(new Font(getName(), 1, 14));
        DefaultComboBoxModel<String> comboBoxItems = new DefaultComboBoxModel<String>();
        comboBoxItems.addElement("Pas de dommage");
        comboBoxItems.addElement("Dommages mineurs");
        comboBoxItems.addElement("Dommages modérés");
        comboBoxItems.addElement("Dommages graves");
        JComboBox<String> comboBox = new JComboBox<String>(comboBoxItems);
        comboBox.setPreferredSize(new Dimension(155, 45));
        comboBox.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));  // إضافة margin حول الـ JLabel
        comboBox.setSelectedIndex(0);
        C1.add(CarStatusLabel);
        C1.add(comboBox);
        InputsContainer.add(C1);

        Container C2 = new Container();
        C2.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel amountOfLossesLabel = new JLabel("Montant estimé:");
        amountOfLossesLabel.setFont(new Font(getName(), 1, 14));
        JTextField textField1 = new JTextField(15);
        textField1.setPreferredSize(new Dimension(200, 25)); 
        C2.add(amountOfLossesLabel);
        C2.add(textField1);
        InputsContainer.add(C2);

        Container C3 = new Container();
        C3.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton addButton = new JButton("add");
        addButton.addActionListener(e ->{
            //Float amountSupplementere = Float.parseFloat(textField1.getText());
            //Float TotalPrice = amountSupplementere + Float.parseFloat(LocationData[4].toString());
            int clientId = Integer.parseInt(LocationData[7].toString());
            int carId = Integer.parseInt(LocationData[8].toString());
            String carStatus = comboBox.getSelectedItem().toString();
            int additionalAmount = Integer.parseInt(textField1.getText());
            int totalAmount = Integer.parseInt(LocationData[4].toString());
            String dateDebut = LocationData[5].toString();
            Historique_CRUD.add(clientId,carId,carStatus,additionalAmount,additionalAmount + totalAmount,dateDebut);
            dispose();
        });
        JButton closeButton = new JButton("close");
        closeButton.addActionListener(e->{
            dispose();
        });
        C3.add(addButton);
        C3.add(closeButton);
        mainContainer.add(C3,BorderLayout.SOUTH);
    }

}