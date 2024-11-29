package Gui;

import Entities_CRUD.User_CRUD;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class Car extends JFrame{
      //add the methode who you will use it 
      public void actionPerformed(ActionEvent e) {
        User_CRUD.execute();
    }
    public Car(){

    }
    public static void main(String[] args) {
        new Car();
    }
    
}
