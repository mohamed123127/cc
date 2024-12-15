package Gui;

import javax.swing.*;
import java.awt.*;

import Entities_CRUD.User_CRUD;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import components.DataGridView;

public class Locations extends JFrame{
      
    public Locations(){

       setTitle("Add Laction");
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setSize(600, 400);
       
      
       
       JPanel panel= new JPanel(new FlowLayout(FlowLayout.LEFT));

       JButton selectClient = new JButton("select Client");
       
       JComboBox<String> selectCar = new JComboBox<>(new String[]{"Car 1", "Car 2", "Car 3"});

       JLabel dateFin = new JLabel("la date de fin :");
       SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
       JFormattedTextField dateField = new JFormattedTextField(dateFormat);
       dateField.setColumns(10);
      
       panel.add(selectClient);
       panel.add(selectCar);
       panel.add(dateFin);
       panel.add(dateField);
    
       add(panel, BorderLayout.NORTH);
       







        this.setVisible(true);


      

    }
    public static void main(String[] args) {
        new Locations();
    }
    
}