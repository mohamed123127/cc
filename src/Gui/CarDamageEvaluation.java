package Gui;

import CustomControle.*;
import Entities_CRUD.Car_CRUD;
import Entities_CRUD.Locations_CRUD;
import Entities_CRUD.Payment_CRUD;
import Entities_CRUD.Retour_CRUD;
import Helpers.Validator;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javax.swing.*;

public class CarDamageEvaluation extends JFrame{

    String calculateLateFee(int pricePerDay, String dateDebut) {
        long daysLate = ChronoUnit.DAYS.between(LocalDate.parse(dateDebut), LocalDate.now());
        return daysLate > 0 ? String.valueOf(daysLate * pricePerDay) : "0";
    }

    CarDamageEvaluation(Object[] LocationData,Locations instance){
        this.setSize(450, 350);
        this.setTitle("Évaluation de l'etat de voiture");
        this.getContentPane().setBackground(new Color(245, 245, 245)); // خلفية فاتحة

        Container mainContainer = this.getContentPane();
        //mainContainer.setLayout(new BoxLayout(mainContainer,BoxLayout.Y_AXIS));
        //add inputs
        int CarId = Integer.parseInt(LocationData[8].toString());
        int LocationCarPrice = Car_CRUD.getLocationPrice(CarId);
        String Date = LocationData[6].toString();
        String lateReturnFees = calculateLateFee(LocationCarPrice, Date);
        JOptionPane.showMessageDialog(null, lateReturnFees);
        Container InputsContainer = new Container();

        mainContainer.add(InputsContainer,BorderLayout.CENTER);
        InputsContainer.setLayout(new FlowLayout(FlowLayout.CENTER,10,3));
        Container C1 = new Container();
        C1.setLayout(new FlowLayout(FlowLayout.LEFT));
        LabelStyle1 CarStatusLabel = new LabelStyle1("Etat de voiture:");
        CarStatusLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 5));  // إضافة margin حول الـ LabelStyle1
        CarStatusLabel.setFont(new Font(getName(), 1, 14));
        DefaultComboBoxModel<String> carStatusComboBoxItems = new DefaultComboBoxModel<String>();
        carStatusComboBoxItems.addElement("Bon état");
        carStatusComboBoxItems.addElement("maintenance");
        JComboBox<String> carStatusComboBox = new JComboBox<>(carStatusComboBoxItems);
        carStatusComboBox.setPreferredSize(new Dimension(155, 45));
        carStatusComboBox.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));  // إضافة margin حول الـ LabelStyle1
        carStatusComboBox.setSelectedIndex(0);
        C1.add(CarStatusLabel);
        C1.add(carStatusComboBox);
        InputsContainer.add(C1);

        Container C2 = new Container();
        C2.setLayout(new FlowLayout(FlowLayout.LEFT));
        LabelStyle1 damageFeesLabel = new LabelStyle1("Frais de dommages:");
        damageFeesLabel.setFont(new Font(getName(), 1, 14));
        TextFieldStyle1 damageFeesTextField1 = new TextFieldStyle1();
        damageFeesTextField1.setEnabled(false);
        damageFeesTextField1.setText("0");
        //damageFeesTextField1.setPreferredSize(new Dimension(200, 25)); 
        C2.add(damageFeesLabel);
        C2.add(damageFeesTextField1);
        InputsContainer.add(C2);

        Container C3 = new Container();
        C3.setLayout(new FlowLayout(FlowLayout.LEFT));
        LabelStyle1 lateReturnFeesLabel = new LabelStyle1("Frais de retard:");
        lateReturnFeesLabel.setFont(new Font(getName(), 1, 14));
        TextFieldStyle1 lateReturnFeesTextField2 = new TextFieldStyle1();
        //lateReturnFeesTextField2.setPreferredSize(new Dimension(200, 25)); 
        lateReturnFeesTextField2.setText(lateReturnFees);
        C3.add(lateReturnFeesLabel);
        C3.add(lateReturnFeesTextField2);
        InputsContainer.add(C3);

        Container C5 = new Container();
        C5.setLayout(new FlowLayout(FlowLayout.LEFT));
        LabelStyle1 paymentMethodLabel = new LabelStyle1("Mode de paiement:");
        paymentMethodLabel.setFont(new Font(getName(), 1, 14));
        DefaultComboBoxModel<String> paymentMethodComboBoxItems = new DefaultComboBoxModel<String>();
        paymentMethodComboBoxItems.addElement("espèces");
        paymentMethodComboBoxItems.addElement("carte bancaire");
        JComboBox<String> paymentMethodComboBox = new JComboBox<String>(paymentMethodComboBoxItems);
        paymentMethodComboBox.setPreferredSize(new Dimension(155, 30));
        paymentMethodComboBox.setSelectedIndex(0);
        C5.add(paymentMethodLabel);
        C5.add(paymentMethodComboBox);
        InputsContainer.add(C5);

        //add action to combobox
        carStatusComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    // Get the selected item
                    String selectedItem = (String) carStatusComboBox.getSelectedItem();
                    if (selectedItem == "Bon état") {
                        damageFeesTextField1.setText("0");
                        damageFeesTextField1.setEnabled(false);  
                    }else{
                        damageFeesTextField1.setEnabled(true);
                    }
                }
            }
        });

        Container C6 = new Container();
        C6.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton addButton = new JButton("add");
        addButton.addActionListener(e ->{
            int reservitionId = Integer.parseInt(LocationData[0].toString());
            int clientId = Integer.parseInt(LocationData[7].toString());
            int carId = Integer.parseInt(LocationData[8].toString());
            String carStatus = carStatusComboBox.getSelectedItem().toString();
            int damageFees = 0;
            if (Validator.isPrice(damageFeesTextField1.getText())) {
                // إذا كانت القيمة المدخلة صالحة، نقوم بتحويلها إلى عدد صحيح
                damageFees = Integer.parseInt(damageFeesTextField1.getText());
            } else {
                // إذا كانت القيمة المدخلة غير صالحة، يمكن عرض رسالة خطأ أو تعيين قيمة افتراضية
                JOptionPane.showMessageDialog(null, "Veuillez entrer un montant valide pour les dommages.");
                damageFees = 0;  // تعيين قيمة افتراضية
            }

            //lateReturnFees = Integer.parseInt(lateReturnFeesTextField2.getText());
            //System.err.println(lateReturnFeesTextField2.getText());
            if(!lateReturnFeesTextField2.getText().isEmpty()){
                //System.err.println("\n"+lateReturnFeesTextField2.getText());
            
        }
            
            //int rentalExtras = damageFees + lateReturnFees;
            String modePayment = paymentMethodComboBox.getSelectedItem().toString();
            /*if(carStatus == "Disponible"){
                damageFeesTextField1.setText("0");
            }
            int additionalAmount = 0 ;
            if(Validator.isPrice(damageFeesTextField1.getText())){
                additionalAmount = Integer.parseInt(damageFeesTextField1.getText());
            }else{
                JOptionPane.showMessageDialog(null, "Le prix saisi est incohérent.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }*/
            int totalAmount = Integer.parseInt(LocationData[4].toString());
            String dateDebut = LocationData[5].toString();
            if(damageFees != 0) Payment_CRUD.AddFraisDammage(reservitionId, damageFees, modePayment);
            if(lateReturnFeesTextField2.getText() != "0") Payment_CRUD.AddlateReturnFees(reservitionId, Integer.parseInt(lateReturnFeesTextField2.getText()), modePayment);
            if (carStatus == "Bon état") {
                Car_CRUD.UpdateStatus(carId, "disponible");
            }else{
                Car_CRUD.UpdateStatus(carId, "en maintenance");
            }
            Retour_CRUD.add(reservitionId,carStatus);
            Locations_CRUD.UpdateStatus(reservitionId, "terminée");
            ResultSet data = Locations_CRUD.GetAll();
            instance.DataGridView.SetDataSource(data);
            dispose();
        });
        JButton closeButton = new JButton("close"){
            @Override
            protected void paintComponent(Graphics g) {
                // تخصيص مظهر الزر
                if (getModel().isPressed()) {
                    g.setColor(Color.DARK_GRAY); // عند الضغط
                } else {
                    g.setColor(new Color(70, 130, 180)); // اللون الأساسي
                }
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);

                // نص الزر
                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 14));
                FontMetrics fm = g.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(getText())) / 2;
                int y = (getHeight() + fm.getAscent()) / 2 - 4;
                g.drawString(getText(), x, y);
            }

            @Override
            protected void paintBorder(Graphics g) {
                // تخصيص الحواف
                g.setColor(new Color(50, 90, 140));
                g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
            }
        };

        // خصائص الزر
        closeButton.setFocusPainted(false);
        closeButton.setBorderPainted(false);
        closeButton.setContentAreaFilled(false);
        closeButton.addActionListener(e->{
            dispose();
        });
        C6.add(addButton);
        C6.add(closeButton);
        mainContainer.add(C6,BorderLayout.SOUTH);
    }
}