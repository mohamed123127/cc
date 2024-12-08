package Gui;

import javax.swing.*;
import org.jdatepicker.impl.*;
import java.awt.*;
import java.util.Properties;
import Entities_CRUD.Locations_CRUD;
import Helpers.DateLabelFormatter;
import components.DataGridView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class AddLocations extends JFrame {

    private JLabel selectedClientLabel; // لعرض اسم العميل المختار
    private DataGridView DataGridView;

    private String selectedClientId = null; // لتخزين معرّف العميل المختار
    private String selectedCarId = null; // لتخزين معرّف السيارة
    private double montantParJour;

    public AddLocations() {
        setTitle("Add Location");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        
        // استخدام GridBagLayout بدلاً من FlowLayout
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // إضافة بعض المسافات بين العناصر

        // Select Client Button
        JButton selectClientButton = new JButton("Select Client");
        selectedClientLabel = new JLabel("Selected Client: None");
        selectClientButton.addActionListener(e -> {
            new ClientSelection(selectedClient -> {
                selectedClientId = String.valueOf(selectedClient[0]);
                selectedClientLabel.setText("Selected Client: " + selectedClient[1]);
            });
        });

        // Select Car Button
        JButton selectCarButton = new JButton("Select Car");
        JLabel selectedCarLabel = new JLabel("Selected Car: None");
        selectCarButton.addActionListener(e -> {
            new CarSelection(selectedCarData -> {
                String selectedCarName = (String) selectedCarData[1]; 
                selectedCarLabel.setText("Selected Car: " + selectedCarName);
                selectedCarId = String.valueOf(selectedCarData[0]); 
                montantParJour = (double) selectedCarData[3];
            });
        });

        // Properties for Date Picker
        Properties properties = new Properties();
        properties.put("text.today", "Today");
        properties.put("text.month", "Month");
        properties.put("text.year", "Year");

        JLabel dateDebutLabel = new JLabel("Start Date:");
        JDatePanelImpl dateDebutPanel = new JDatePanelImpl(new UtilDateModel(), properties);
        JDatePickerImpl dateDebutPicker = new JDatePickerImpl(dateDebutPanel, new DateLabelFormatter());
        dateDebutPicker.setPreferredSize(new Dimension(150, 30));

        JLabel dateFinLabel = new JLabel("End Date:");
        JDatePanelImpl dateFinPanel = new JDatePanelImpl(new UtilDateModel(), properties);
        JDatePickerImpl dateFinPicker = new JDatePickerImpl(dateFinPanel, new DateLabelFormatter());
        dateFinPicker.setPreferredSize(new Dimension(150, 30));

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            if (selectedClientId == null || selectedCarId == null) {
                JOptionPane.showMessageDialog(this, "Please select both client and car!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            java.util.Date startDate = (java.util.Date) dateDebutPicker.getModel().getValue();
            java.util.Date endDate = (java.util.Date) dateFinPicker.getModel().getValue();

            if (startDate == null || endDate == null){
                JOptionPane.showMessageDialog(this, "Please select a date!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            LocalDate startLocalDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate endLocalDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            long daysDifference = ChronoUnit.DAYS.between(startLocalDate, endLocalDate);
            if (daysDifference <= 0) {
                JOptionPane.showMessageDialog(this, "End date must be after start date!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String startDateString = sdf.format(startDate);
            String endDateString = sdf.format(endDate);

            String columns = "id_client, id_vehicule, date_debut, date_fin, statut";
            double montantTotal = daysDifference * montantParJour;

            String values = selectedClientId + ", " + selectedCarId + ", '" + startDateString + "', '" + endDateString + "', 'en cours'";

            boolean result = Locations_CRUD.Insert(columns, values);

            int ReservationId = Locations_CRUD.GetLastReservationId();
            System.out.println(ReservationId);
            dispose();

            if (result) {
                JOptionPane.showMessageDialog(this, "Location added successfully! Days: " + daysDifference + ", Total: " + montantTotal + " DA");
                selectedClientId = null;
                selectedCarId = null;
                selectedClientLabel.setText("Selected Client: None");
                selectedCarLabel.setText("Selected Car: None");
                dateFinPicker.getModel().setValue(null);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add location!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Set the components with GridBagLayout constraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(selectedClientLabel, gbc);

        gbc.gridx = 1;
        panel.add(selectClientButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(selectedCarLabel, gbc);

        gbc.gridx = 1;
        panel.add(selectCarButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(dateDebutLabel, gbc);

        gbc.gridx = 1;
        panel.add(dateDebutPicker, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(dateFinLabel, gbc);

        gbc.gridx = 1;
        panel.add(dateFinPicker, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(addButton, gbc);

        // إضافة اللوحة العلوية إلى الجزء الشمالي
        add(panel, BorderLayout.NORTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        new AddLocations();
    }
}
