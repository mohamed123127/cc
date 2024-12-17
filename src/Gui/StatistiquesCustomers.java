package Gui;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import Helpers.DbOperation;

import java.awt.*;
import java.sql.ResultSet;
public class StatistiquesCustomers extends JPanel{
    
    
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
    public JPanel StatistiquesCustomers() {
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
            ////////////////////////////////  bar char ta3 les reservation per gender       /////////////////////////////////////////////////////////////
             ResultSet rs2=dbOperation.GetSpecialData("SELECT c.sex AS Gender, COUNT(r.id_reservation) AS ReservationCount FROM client c JOIN reservation r ON c.id_client = r.id_client GROUP BY c.sex;");
                                  
             int countmen2=dbOperation.countsex("Gender","Men", rs2);
             rs2=dbOperation.GetSpecialData("SELECT c.sex AS Gender, COUNT(r.id_reservation) AS ReservationCount FROM client c JOIN reservation r ON c.id_client = r.id_client GROUP BY c.sex;");
             int countwomen2=dbOperation.countsex("Gender","Women", rs2);
             DefaultCategoryDataset barDataset2 = new DefaultCategoryDataset();
             barDataset2.addValue(countmen2, "Damege", "Men");
             barDataset2.addValue(countwomen2, "Damege", "Women");
             
             JFreeChart barChart2 = ChartFactory.createBarChart("Dameg per gender", "gender", "Dameg", barDataset2);
             ChartPanel barChartPanel2 = new ChartPanel(barChart2);
             ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
             /*---------------------------------------------------------------------------------------------------- */
             JPanel UtilizationRatePanel = new JPanel();
             JLabel titeltopUse = new JLabel("Top 5 clients by number of reservation", JLabel.CENTER);
             UtilizationRatePanel.setLayout(new GridLayout(6,1,20,10));
             ResultSet rs=dbOperation.GetSpecialData("SELECT c.id_client, c.nom, COUNT(*) AS reservation_count FROM reservation r JOIN client c ON r.id_client = c.id_client GROUP BY c.id_client, c.nom ORDER BY reservation_count DESC;");
             String values []=dbOperation.getValueFromRow(rs,"nom");
             rs=dbOperation.GetSpecialData("SELECT c.id_client, c.nom, COUNT(*) AS reservation_count FROM reservation r JOIN client c ON r.id_client = c.id_client GROUP BY c.id_client, c.nom ORDER BY reservation_count DESC;");
             double frequency[]=dbOperation.getSpecialValueFromRow(rs,"reservation_count");
 
             UtilizationRatePanel.add(titeltopUse);
             UtilizationRatePanel.add(PrintpanelsUseRate( values [0],frequency[0]));
             UtilizationRatePanel.add(PrintpanelsUseRate(values [1],frequency[1]));
             UtilizationRatePanel.add(PrintpanelsUseRate(values [2],frequency[2]));
             UtilizationRatePanel.add(PrintpanelsUseRate(values [3],frequency[3]));
             UtilizationRatePanel.add(PrintpanelsUseRate(values [4],frequency[4]));
             UtilizationRatePanel.setBackground(Color.GREEN); 
             /*---------------------------------------------------------------------------------------------------- */
             JPanel MaintenanceFrequencyPanel = new JPanel();
             JLabel titelstat = new JLabel("methode of paiment ", JLabel.CENTER);
             MaintenanceFrequencyPanel.setLayout(new GridLayout(5,1,20,10));
             ResultSet rs3=dbOperation.GetSpecialData("SELECT p.mode_paiement AS PaymentMethod, COUNT(p.id_paiement) AS PaymentCount FROM paiement p GROUP BY p.mode_paiement ORDER BY p.mode_paiement ASC;");
             String values2 []=dbOperation.getValueFromRow(rs3,"PaymentMethod");   
             rs3=dbOperation.GetSpecialData("SELECT p.mode_paiement AS PaymentMethod, COUNT(p.id_paiement) AS PaymentCount FROM paiement p GROUP BY p.mode_paiement ORDER BY p.mode_paiement ASC;");
             double frequency2[]=dbOperation.getSpecialValueFromRow(rs3,"PaymentCount");                  
             
             JPanel stat2 = PrintpanelsUseRate(values2 [0],frequency2[0]);
             JPanel stat3 = PrintpanelsUseRate(values2 [1],frequency2[1]);
             JPanel stat4 = PrintpanelsUseRate(values2 [2],frequency2[2]);
             
 
             MaintenanceFrequencyPanel.add(titelstat);
             MaintenanceFrequencyPanel.add(stat2);
             MaintenanceFrequencyPanel.add(stat3);
             MaintenanceFrequencyPanel.add(stat4);
             
             MaintenanceFrequencyPanel.setBackground(Color.RED); 
             /*---------------------------------------------------------------------------------------------------- */
             
             
             centerPanel.setLayout(new GridLayout(2, 3, 10, 10));
     
             // Bar Chart for Most Rented Vehicles
              ////////////////////////////////  bar char ta3 les damege per gender       ///////////////////////////////////////////////////////////////////////////////////////////////////////
              rs=dbOperation.GetData("c.id_client, c.nom, c.prenom, c.sex","retour r","JOIN reservation res ON r.id_reservation = res.id_reservation\n" + //
                                  "JOIN client c ON res.id_client = c.id_client WHERE r.etat_retour = 'Maintenance'");
                                  
             int countmen=dbOperation.countrows("sex","Men", rs);
             rs=dbOperation.GetData("c.id_client, c.nom, c.prenom, c.sex","retour r","JOIN reservation res ON r.id_reservation = res.id_reservation\n" + //
             "JOIN client c ON res.id_client = c.id_client WHERE r.etat_retour = 'Maintenance'");
             int countwomen=dbOperation.countrows("sex","Women", rs);
             DefaultCategoryDataset barDataset = new DefaultCategoryDataset();
             barDataset.addValue(countmen, "Damege", "Men");
             barDataset.addValue(countwomen, "Damege", "Women");
             
             JFreeChart barChart = ChartFactory.createBarChart("Dameg per gender"+countwomen, "gender", "Dameg", barDataset);
             ChartPanel barChartPanel = new ChartPanel(barChart);
             /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
             // Pie Chart for Vehicle Utilization
             DefaultPieDataset pieDataset = new DefaultPieDataset();
             rs=dbOperation.GetData("id_client, COUNT(*) AS frequency","reservation","GROUP BY  id_client HAVING  COUNT(*) = 3 ORDER BY frequency DESC;");
             int count3time=dbOperation.countrows("frequency","3", rs);
             rs=dbOperation.GetData("id_client, COUNT(*) AS frequency","reservation","GROUP BY  id_client HAVING  COUNT(*) = 2 ORDER BY frequency DESC;");
             int count2time=dbOperation.countrows("frequency","2", rs);
             rs=dbOperation.GetData("id_client, COUNT(*) AS frequency","reservation","GROUP BY  id_client HAVING  COUNT(*) = 1 ORDER BY frequency DESC;");
             int count1time=dbOperation.countrows("frequency","1", rs);
             rs=dbOperation.GetData("id_client, COUNT(*) AS frequency","reservation","GROUP BY  id_client HAVING  COUNT(*) > 3 ORDER BY frequency DESC;");
             int countmore3=dbOperation.countrows("frequency",">3", rs);
             pieDataset.setValue("1 time", count1time);
             pieDataset.setValue("2 time", count2time);
             pieDataset.setValue("3 time", count3time);
             pieDataset.setValue("mora than 3", countmore3);
             JFreeChart pieChart = ChartFactory.createPieChart("Analyse de la fréquence des locations", pieDataset, true, true, false);
             ChartPanel pieChartPanel = new ChartPanel(pieChart);
             centerPanel.add(imegePanel);
             centerPanel.add(UtilizationRatePanel);
             centerPanel.add(barChartPanel);
             centerPanel.add(barChartPanel2);
             centerPanel.add(pieChartPanel);
             centerPanel.add(MaintenanceFrequencyPanel);
             
             // Pie Chart for Vehicle Utilization
             
             
             add(centerPanel, BorderLayout.CENTER);
             return centerPanel;
    }
     }
