package Gui;

import javax.swing.*;
import java.awt.*;

import Entities_CRUD.User_CRUD;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import components.DataGridView;

public class User extends JFrame {
    public User() {
        
        setTitle("Add User");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
      

       
        String[] columnNames = {"ID", "Name", "Email", "Role"};

       
        Object[][] data = {
            {1, "moh", "wshhhhh@gmail.com", "User"},
            {2, "nazim", "ben@gmail.com", "User"}
        };

       
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
