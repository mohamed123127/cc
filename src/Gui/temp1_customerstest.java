package Gui;

import Entities_CRUD.User_CRUD;
import java.sql.ResultSet;
import javax.swing.*;


public class temp1_customerstest extends JFrame{
    public temp1_customerstest(){
        setTitle("Add User");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        
        //Set data grid view parameter
        String[] columnNames = {"#","Nom", "Prenom", "Email","Mot de passe","Role"};
        ResultSet data = User_CRUD.GetData();

       //create data grid view
        /*DataGridView  grid = new DataGridView (columnNames, data);
        grid.setBounds(20, 20, 550, 250);

        setLayout(new BorderLayout());
        add(grid, BorderLayout.CENTER);
        setVisible(true);*/
    }

    public static void main(String[] args) {
        new User();
    }
}
