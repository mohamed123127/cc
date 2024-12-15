package Gui;

import CustomControle.MainHeaderItem;
import Helpers.ImageHelper;
import java.awt.*;
import javax.swing.*;

public class MainPage extends JFrame {
    private String SelectedButton = "";

    public MainPage() {
        this.setSize(1200, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(new Color(245, 245, 245));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        JPanel TopPanel = new JPanel();
        TopPanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        TopPanel.setPreferredSize(new Dimension(200, 50));
        TopPanel.setBackground(new Color(98, 121, 255));
        this.add(TopPanel, BorderLayout.NORTH);
       
        ImageIcon carIcon = ImageHelper.convertIconColor("C:\\Users\\DELL\\Desktop\\tous\\University\\L3\\S5\\IHM\\Projet\\Project-Code\\CarLocation\\src\\Resources\\Icons\\car.png",25,25);
        JButton carButton = MainHeaderItem.getNewItem("Voiture", carIcon);
        ImageIcon CustomerIcon = ImageHelper.convertIconColor("C:\\Users\\DELL\\Desktop\\tous\\University\\L3\\S5\\IHM\\Projet\\Project-Code\\CarLocation\\src\\Resources\\Icons\\Customer.png",25,25);
        JButton CustomerButton = MainHeaderItem.getNewItem("Client", CustomerIcon);
        ImageIcon LocationIcon = ImageHelper.convertIconColor("C:\\Users\\DELL\\Desktop\\tous\\University\\L3\\S5\\IHM\\Projet\\Project-Code\\CarLocation\\src\\Resources\\Icons\\schedule.png",25,25);
        JButton LocationButton = MainHeaderItem.getNewItem("Reservation", LocationIcon);
        ImageIcon StatistiquesIcon = ImageHelper.convertIconColor("C:\\Users\\DELL\\Desktop\\tous\\University\\L3\\S5\\IHM\\Projet\\Project-Code\\CarLocation\\src\\Resources\\Icons\\Statistiques.png",25,25);
        JButton StatistiquesButton = MainHeaderItem.getNewItem("Statistiques", StatistiquesIcon);
        ImageIcon AdminIcon = ImageHelper.convertIconColor("C:\\Users\\DELL\\Desktop\\tous\\University\\L3\\S5\\IHM\\Projet\\Project-Code\\CarLocation\\src\\Resources\\Icons\\Admin.png",25,25);
        JButton AdminButton = MainHeaderItem.getNewItem("administration", AdminIcon);
            
        TopPanel.add(carButton);
        TopPanel.add(CustomerButton);
        TopPanel.add(LocationButton);
        TopPanel.add(AdminButton);
        TopPanel.add(StatistiquesButton);

    }

    public static void main(String[] args) {
        MainPage main = new MainPage();
        main.setVisible(true);
        
    }
}
