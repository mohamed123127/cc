package Gui;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import java.sql.SQLException;
import java.util.Arrays;

import Helpers.DbOperation;

import java.awt.*;
import java.sql.ResultSet;
public class StatistiquesRevenue extends JPanel {
    public int [] getTotalmois(ResultSet rs) {
        int value[] = new int[12];
        
        try {
            // Move the cursor to the third row
            while (rs.next()) {
               
                
                switch (rs.getDate("mois").toLocalDate().getMonthValue()) {
                    case 1:
                    value[0]=rs.getInt("somme_montant");
                        break;
                case 2:
                    value[1]=rs.getInt("somme_montant");
                        break;
                case 3:
                    value[2]=rs.getInt("somme_montant");
                        break;
                case 4:
                    value[3]=rs.getInt("somme_montant");
                        break;
                case 5:
                    value[4]=rs.getInt("somme_montant");
                        break;
                case 6:
                    value[5]=rs.getInt("somme_montant");
                        break;
                case 7:
                    value[6]=rs.getInt("somme_montant");
                        break;
                case 8:
                    value[7]=rs.getInt("somme_montant");
                        break;
                case 9:
                    value[8]=rs.getInt("somme_montant");
                        break;
                case 10:
                    value[9]=rs.getInt("somme_montant");
                        break;
                case 11:
                    value[10]=rs.getInt("somme_montant");
                        break;
                case 12:
                    value[11]=rs.getInt("somme_montant");
                        break;
                
                    
                }
               
                
              
            }
        } catch (SQLException e) {
            // Handle the SQLException
            System.err.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        }
        
        return value;
    } 

     public JPanel PrintpanelsTop5(int rank, String carName,double statice) {
       
         // Create a panel for a specific rank
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 3, 10, 10)); // Grid layout with 1 row and 3 columns
        JLabel rankLabel = new JLabel( rank + "star : ");
        JLabel carNameLabel = new JLabel(carName);
        JLabel carstaticLabel = new JLabel(String.valueOf(statice)+"%");
        // Add labels to the panel
        panel.add(rankLabel);
        panel.add(carNameLabel);
        panel.add(carstaticLabel);
        return panel; // Return the created panel
    }
    public JPanel PrintpanelsUseRate(String carName,double statice) {
        JPanel panelMain = new JPanel();
        panelMain.setLayout(new GridLayout(1, 2, 10, 30)); // Grid layout with 1 row and 3 columns
        JPanel panel1_1 = new JPanel();
        
        JPanel panel1_2 = new JPanel();
        JLabel carNameLabel = new JLabel(carName);
        JLabel carstaticLabel = new JLabel(String.valueOf(statice)+"%");
        

        panel1_1.add(carNameLabel);
        panel1_2.add(carstaticLabel);
        panelMain.add(panel1_1);
        panelMain.add(panel1_2);
        panel1_2.setBackground(Color.GRAY); 
        return panelMain;
    }
    public JPanel StatistiquesRevenue(){
        DbOperation dbOperation = new DbOperation();
        JPanel centerPanel = new JPanel();
        // Center Panel for Charts and Tables and other component
        JPanel imegePanel = new JPanel();
        imegePanel.setLayout(new BorderLayout());
        JLabel titelimage = new JLabel("best Car Rental ", JLabel.CENTER);
         try {
            // Load the image from the img folder
            ImageIcon imageIcon = new ImageIcon(getClass().getResource("/img/Ford.jpg"));

            Image scaledImage = imageIcon.getImage().getScaledInstance(400, 300, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));

            // Add the image to the panel
            imegePanel.add(titelimage,BorderLayout.NORTH);
            imegePanel.add(imageLabel,BorderLayout.CENTER);
        } catch (NullPointerException e) {
            System.out.println("Image not found: Ensure the file exists in the img folder.");
        }

        /*---------------------------------------------------------------------------------------------------- */
        ResultSet rs2=dbOperation.GetSpecialData("SELECT DATE_FORMAT(date_paiement, '%Y-%m') AS mois, SUM(montant) AS somme_montant FROM paiement GROUP BY DATE_FORMAT(date_paiement, '%Y-%m') ORDER BY mois;");
        int totalmois []=getTotalmois(rs2);
        DefaultCategoryDataset barDataset = new DefaultCategoryDataset();
        
        barDataset.addValue(totalmois [0], "Damege", "01");
        barDataset.addValue(totalmois [1], "Damege", "02");
        barDataset.addValue(totalmois [2], "Damege", "03");
        barDataset.addValue(totalmois [3], "Damege", "04");
        barDataset.addValue(totalmois [4], "Damege", "05");
        barDataset.addValue(totalmois [5], "Damege", "06");
        barDataset.addValue(totalmois [6], "Damege", "07");
        barDataset.addValue(totalmois [7], "Damege", "08");
        barDataset.addValue(totalmois [8], "Damege", "09");
        barDataset.addValue(totalmois [9], "Damege", "10");
        barDataset.addValue(totalmois [10], "Damege", "11");
        barDataset.addValue(totalmois [11], "Damege", "12");
        
        JFreeChart barChart = ChartFactory.createBarChart("revenue by type", "Type", "Revenue", barDataset);
        ChartPanel barChartPanel = new ChartPanel(barChart);
        
        
        
        
        JPanel topValuePanel = new JPanel();
        
        JLabel titeltopValue = new JLabel("client rating", JLabel.CENTER);
        topValuePanel.setLayout(new GridLayout(6,1,20,10));
        
     
   
        topValuePanel.add(titeltopValue);
        topValuePanel.add(PrintpanelsTop5(1, "Mohamed ",50));
        topValuePanel.add(PrintpanelsTop5(2, "Mahdi ",26.3));
        topValuePanel.add(PrintpanelsTop5(3, "Toufik ",13));
        topValuePanel.add(PrintpanelsTop5(4, "Lotfi ",5));
        topValuePanel.add(PrintpanelsTop5(5, "Farouk ",2.2));
        topValuePanel.setBackground(Color.GRAY); 
        /*---------------------------------------------------------------------------------------------------- */
        JPanel UtilizationRatePanel = new JPanel();
        JLabel titeltopUse = new JLabel("Best 5 client", JLabel.CENTER);
        UtilizationRatePanel.setLayout(new GridLayout(6,1,20,10));
       

        UtilizationRatePanel.add(titeltopUse);
        UtilizationRatePanel.add(PrintpanelsUseRate( "Mohamed ",50));
        UtilizationRatePanel.add(PrintpanelsUseRate("Mahdi ",50));
        UtilizationRatePanel.add(PrintpanelsUseRate("Toufik ",50));
        UtilizationRatePanel.add(PrintpanelsUseRate("Mercides ",50));
        UtilizationRatePanel.add(PrintpanelsUseRate("Mercides ",50));
        UtilizationRatePanel.setBackground(Color.GREEN); 
        /*---------------------------------------------------------------------------------------------------- */
        JPanel MaintenanceFrequencyPanel = new JPanel();
        JLabel titelstat = new JLabel("methode of paiment ", JLabel.CENTER);
        MaintenanceFrequencyPanel.setLayout(new GridLayout(6,1,20,10));
        JPanel stat1 = PrintpanelsUseRate( "dahabia ",50);
        JPanel stat2 = PrintpanelsUseRate("check ",50);
        JPanel stat3 = PrintpanelsUseRate("espece ",50);
        JPanel stat4 = PrintpanelsUseRate("cart bancaire ",50);
        

        MaintenanceFrequencyPanel.add(titelstat);
        MaintenanceFrequencyPanel.add(stat1);
        MaintenanceFrequencyPanel.add(stat2);
        MaintenanceFrequencyPanel.add(stat3);
        MaintenanceFrequencyPanel.add(stat4);
        
        MaintenanceFrequencyPanel.setBackground(Color.RED); 
        /*---------------------------------------------------------------------------------------------------- */
        
        
        centerPanel.setLayout(new GridLayout(2, 3, 10, 10));

        // Bar Chart for Most Rented Vehicles
        ResultSet rs=dbOperation.GetSpecialData("SELECT r.id_reservation, p.montant, v.id_vehicule, v.type, v.marque FROM paiement p JOIN reservation r ON p.id_reservation = r.id_reservation JOIN vehicule v ON r.id_vehicule = v.id_vehicule ORDER BY r.id_reservation ASC;");
        int totalSUV=dbOperation.getTotalpaiment(rs,"SUV");
        DefaultCategoryDataset barDataset1 = new DefaultCategoryDataset();
        rs=dbOperation.GetSpecialData("SELECT r.id_reservation, p.montant, v.id_vehicule, v.type, v.marque FROM paiement p JOIN reservation r ON p.id_reservation = r.id_reservation JOIN vehicule v ON r.id_vehicule = v.id_vehicule ORDER BY r.id_reservation ASC;");
        int totalBerline=dbOperation.getTotalpaiment(rs,"Berline");
        barDataset1.addValue(totalSUV, "Damege", "SUV");
        barDataset1.addValue(totalBerline, "Damege", "Berline");
        
        JFreeChart barChart1 = ChartFactory.createBarChart("revenue by type", "Type", "Revenue", barDataset1);
        ChartPanel barChartPanel1 = new ChartPanel(barChart1);
         /*---------------------------------------------------------------------------------------------------- */
        // Pie Chart for Vehicle Utilization
        DefaultPieDataset pieDataset = new DefaultPieDataset();
        pieDataset.setValue("SUV", 60);
        pieDataset.setValue("Sedan", 20);
        pieDataset.setValue("Truck", 100);
        JFreeChart pieChart = ChartFactory.createPieChart("dameged car per type:", pieDataset, true, true, false);
        ChartPanel pieChartPanel = new ChartPanel(pieChart);
        centerPanel.add(imegePanel);
        centerPanel.add(barChartPanel1);
        centerPanel.add(UtilizationRatePanel);
        centerPanel.add(barChartPanel);
        centerPanel.add(MaintenanceFrequencyPanel);
        centerPanel.add(pieChartPanel);
        // Pie Chart for Vehicle Utilization
        
        
        add(centerPanel, BorderLayout.CENTER);
        return centerPanel;
    }
}
