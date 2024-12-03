package Gui;

import java.awt.*;
import java.text.SimpleDateFormat;
import javax.swing.*;

public class Locations extends JFrame{
      
    public boolean ToSelectClient = false;
    
    public Locations(){

       setTitle("Add Laction");
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setSize(600, 400);
       //set frame content
       JPanel panel= new JPanel(new FlowLayout(FlowLayout.LEFT));
       JButton selectClient = new JButton("select Client");
       selectClient.addActionListener(e -> {
        new temp1_customerstest();
       });
       JTextField SelectedClient = new JTextField(15);
       JComboBox<String> selectCar = new JComboBox<>(new String[]{"Car 1", "Car 2", "Car 3"});

       JLabel dateFin = new JLabel("la date de fin :");
       SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
       JFormattedTextField dateField = new JFormattedTextField(dateFormat);
       dateField.setColumns(10);
      
       panel.add(selectClient);
       panel.add(SelectedClient);
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