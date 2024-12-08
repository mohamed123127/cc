package Gui;

import Entities_CRUD.User_CRUD;
import components.DataGridView;
import java.awt.*;
import java.sql.ResultSet;
import javax.swing.*;

public class User extends JFrame {
    public User() {
        
        setTitle("Add User");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        
        String[] columnNames = {"#","Nom", "Prenom", "Email","Mot de passe","Role"};

       
        ResultSet data = User_CRUD.GetData();

       //create data grid view
        DataGridView  grid = new DataGridView (columnNames, data);
        grid.setBounds(20, 20, 550, 250);

        setLayout(new BorderLayout());
        add(grid, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton addButton = new JButton(" add user");
        JButton LocationButton = new JButton(" go to locatin page ");
   
        buttonsPanel.add(addButton);
        buttonsPanel.add(LocationButton);
        add(buttonsPanel, BorderLayout.SOUTH);
        
     
        
        addButton.addActionListener(e -> {
            grid.addRow(new Object[]{3, "moooooh", "43e@example.com", "user"});
        });
        LocationButton.addActionListener(e -> {
            new Locations();
            setVisible(false);//for hiding
            dispose(); //for closing
        });
        
        setVisible(true);
    }

    public static void main(String[] args) {
        new User();
    }
}
