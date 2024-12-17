package Gui;
import javax.swing.*;
import java.awt.*;
import Entities_CRUD.User_CRUD;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import Helpers.DbOperation; 
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatistiquesVehicule extends JPanel{
    
    public JPanel PrintpanelsTop5(int rank, String carName,double statice) {
        // Create a panel for a specific rank
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 3, 10, 10)); // Grid layout with 1 row and 3 columns
        JLabel rankLabel = new JLabel("Place number " + rank + " : ");
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
        JLabel carstaticLabel = new JLabel(String.valueOf(statice));
        

        panel1_1.add(carNameLabel);
        panel1_2.add(carstaticLabel);
        panelMain.add(panel1_1);
        panelMain.add(panel1_2);
        panel1_2.setBackground(Color.GRAY); 
        return panelMain;
    }

  //hnaya kayen fonction win treturni number ta3 el cars li yesta3mlou essence ou li yesta3mlou dizel
    


    public JPanel  StatistiquesVehicule(){
        DbOperation dbOperation = new DbOperation();
        JPanel centerPanel = new JPanel();
        JPanel centerPaneltop = new JPanel();
        JPanel centerPanelbotum = new JPanel();
             // Center Panel for Charts and Tables and other component
             JPanel imegePanel = new JPanel();
             imegePanel.setLayout(new BorderLayout());
             JLabel titelimage = new JLabel("best Car Rental ", JLabel.CENTER);
             //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
             try {
                // Load the data from the database
                ResultSet rs = dbOperation.GetSpecialData("SELECT r.id_vehicule, v.marque, COUNT(*) AS rental_count " +
                                                          "FROM reservation r " +
                                                          "JOIN vehicule v ON r.id_vehicule = v.id_vehicule " +
                                                          "GROUP BY r.id_vehicule, v.marque " +
                                                          "ORDER BY rental_count DESC;");
            
                int idVehicule = 0; // Variable to hold the id_vehicule
                String marque = ""; // Variable to hold the marque
            
                // Process only the first row of the ResultSet
                if (rs.next()) { 
                    idVehicule = rs.getInt("id_vehicule"); // Get id_vehicule from the first row
                }
            
                // Determine the marque based on id_vehicule
                switch (idVehicule) {
                    case 1:
                        marque = "Toyota";
                        break;
                    case 2:
                        marque = "Hyundai";
                        break;
                    case 3:
                        marque = "Ford";
                        break;
                    case 4:
                        marque = "Chevrolet";
                        break;
                    case 5:
                        marque = "Nissan";
                        break;
                    case 6:
                        marque = "BMW";
                        break;
                    case 7:
                        marque = "Audi";
                        break;
                    case 8:
                        marque = "Kia";
                        break;
                    case 9:
                        marque = "Mercedes";
                        break;
                    case 10:
                        marque = "Renault";
                        break;
                    default:
                        marque = "Unknown"; // Fallback if id_vehicule is not matched
                        break;
                }
            
                // Example: Load an image based on the marque
                ImageIcon imageIcon = new ImageIcon(getClass().getResource("/img/" + marque + ".jpg"));
            
                Image scaledImage = imageIcon.getImage().getScaledInstance(400, 300, Image.SCALE_SMOOTH);
                JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
            
                // Add the image to the panel
                imegePanel.add(titelimage, BorderLayout.NORTH);
                imegePanel.add(imageLabel, BorderLayout.CENTER);
            
            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("Image not found: Ensure the file exists in the img folder.");
            }
            
            
 
             /*---------------------------------------------------------------------------------------------------- */
             JPanel topValuePanel = new JPanel();
             
             JLabel titeltopValue = new JLabel("top 5 Car Rental ", JLabel.CENTER);
             topValuePanel.setLayout(new GridLayout(6,1,20,10));
             ResultSet rs=dbOperation.GetSpecialData("SELECT r.id_vehicule, v.marque, COUNT(*) AS rental_count FROM reservation r JOIN vehicule v ON r.id_vehicule = v.id_vehicule GROUP BY r.id_vehicule, v.marque ORDER BY rental_count DESC;");
             String values []=dbOperation.getValueFromRow(rs,"marque");
             rs=dbOperation.GetSpecialData("SELECT r.id_vehicule, v.marque, COUNT(*) AS rental_count FROM reservation r JOIN vehicule v ON r.id_vehicule = v.id_vehicule GROUP BY r.id_vehicule, v.marque ORDER BY rental_count DESC;");
             double frequency[]=dbOperation.getSpecialValueFromRow(rs,"rental_count");
             JPanel top1of5 = PrintpanelsTop5(1, values[0],frequency[0]);
             JPanel top2of5 = PrintpanelsTop5(2, values[1],frequency[1]);
             JPanel top3of5 = PrintpanelsTop5(3, values[2],frequency[2]);
             JPanel top4of5 = PrintpanelsTop5(4, values[3],frequency[3]);
             JPanel top5of5 = PrintpanelsTop5(5, values[4],frequency[4]);
        
             topValuePanel.add(titeltopValue);
             topValuePanel.add(top1of5);
             topValuePanel.add(top2of5);
             topValuePanel.add(top3of5);
             topValuePanel.add(top4of5);
             topValuePanel.add(top5of5);
             topValuePanel.setBackground(Color.GRAY); 
             /*---------------------------------------------------------------------------------------------------- */
             JPanel UtilizationRatePanel = new JPanel();
             JLabel titeltopUse = new JLabel("number of days used for top 5 Car rented ", JLabel.CENTER);
             rs=dbOperation.GetSpecialData("SELECT r.id_vehicule, v.marque, COUNT(*) AS rental_count FROM reservation r JOIN vehicule v ON r.id_vehicule = v.id_vehicule GROUP BY r.id_vehicule, v.marque ORDER BY rental_count DESC;");
             long days[]=dbOperation.countdays(rs);
             UtilizationRatePanel.setLayout(new GridLayout(6,1,20,10));
             JPanel topUse1 = PrintpanelsUseRate(values[0],days[0]);
             JPanel topUse2 = PrintpanelsUseRate(values[1],days[1]);
             JPanel topUse3 = PrintpanelsUseRate(values[2],days[2]);
             JPanel topUse4 = PrintpanelsUseRate(values[3],days[3]);
             JPanel topUse5 = PrintpanelsUseRate(values[4],days[4]);
 
             UtilizationRatePanel.add(titeltopUse);
             UtilizationRatePanel.add(topUse1);
             UtilizationRatePanel.add(topUse2);
             UtilizationRatePanel.add(topUse3);
             UtilizationRatePanel.add(topUse4);
             UtilizationRatePanel.add(topUse5);
             UtilizationRatePanel.setBackground(Color.GREEN); 
             /*---------------------------------------------------------------------------------------------------- */
             JPanel MaintenanceFrequencyPanel = new JPanel();
             JLabel titelstat = new JLabel("Vehicle Utilization ", JLabel.CENTER);
             MaintenanceFrequencyPanel.setLayout(new GridLayout(6,1,20,10));
             JPanel stat1 = PrintpanelsUseRate( "Mercides ",50);
             JPanel stat2 = PrintpanelsUseRate("Mercides ",50);
             JPanel stat3 = PrintpanelsUseRate("Mercides ",50);
             JPanel stat4 = PrintpanelsUseRate("Mercides ",50);
             JPanel stat5 = PrintpanelsUseRate("Mercides ",50);
 
             MaintenanceFrequencyPanel.add(titelstat);
             MaintenanceFrequencyPanel.add(stat1);
             MaintenanceFrequencyPanel.add(stat2);
             MaintenanceFrequencyPanel.add(stat3);
             MaintenanceFrequencyPanel.add(stat4);
             MaintenanceFrequencyPanel.add(stat5);
             MaintenanceFrequencyPanel.setBackground(Color.RED); 
             /*---------------------------------------------------------------------------------------------------- */
             
             
             centerPanel.setLayout(new GridLayout(2, 1, 100, 10));
             centerPaneltop.setLayout(new GridLayout(1, 3, 10, 10));
             centerPanelbotum.setLayout(new GridLayout(1, 2, 50, 10));
             // Bar Chart for Most Rented Vehicles
              rs=dbOperation.GetData("type","vehicule");
             int countSUV=dbOperation.countrows("type","SUV", rs);
             rs=dbOperation.GetData("type","vehicule");
             int countBerline=dbOperation.countrows("type","Berline", rs);
             DefaultCategoryDataset barDataset = new DefaultCategoryDataset();
             barDataset.addValue(countSUV, "Rentals", "SUV");
             barDataset.addValue(countBerline, "Rentals", "Berline");
            
             JFreeChart barChart = ChartFactory.createBarChart("Most Rented Vehicles"+countSUV, "Vehicle Type", "Rentals", barDataset);
             ChartPanel barChartPanel = new ChartPanel(barChart);


             
             // Pie Chart for Vehicle Utilization
             //hnaya kayen fonction win treturni number ta3 el cars li yesta3mlou essence ou li yesta3mlou dizel
             
             
             rs=dbOperation.GetData("carburant","vehicule");
             int countEssence=dbOperation.countrows("carburant","Essence",rs);
             rs=dbOperation.GetData("carburant","vehicule");
             int countDiesel=dbOperation.countrows("carburant","Diesel", rs);

             DefaultPieDataset pieDataset = new DefaultPieDataset();
             pieDataset.setValue("Essence", countEssence);
             pieDataset.setValue("Diesel", countDiesel);
             System.out.println(pieDataset);
            
             JFreeChart pieChart = ChartFactory.createPieChart("Type de carburant utilisé", pieDataset, true, true, false);
             ChartPanel pieChartPanel = new ChartPanel(pieChart);
             centerPaneltop.add(imegePanel);
             centerPaneltop.add(topValuePanel);
             centerPaneltop.add(UtilizationRatePanel);
             centerPanelbotum.add(barChartPanel);
             centerPanelbotum.add(pieChartPanel);
            
             centerPanel.add(centerPaneltop);
             centerPanel.add(centerPanelbotum);
             add(centerPanel, BorderLayout.CENTER);
             return centerPanel;
    } 

}
