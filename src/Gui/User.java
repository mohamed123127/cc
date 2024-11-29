package Gui;

import Entities_CRUD.User_CRUD;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class User extends JFrame {

    //add the methode who you will use it 
    public void actionPerformed(ActionEvent e) {
        User_CRUD.execute();
    }

    public User() {
        // Set up the Frame
        setTitle("User Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLayout(null);

        JTable table = 
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
        //add the commponents of frame
        /*JButton button = new JButton("Submit");
        button.setBounds(100, 80, 100, 30);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.this.actionPerformed(e);
            }
        });
        add(button);*/
        setVisible(true);
    }

    public static void main(String[] args) {
        new User();
    }
}
