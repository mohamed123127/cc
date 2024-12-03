package Gui;

import javax.swing.*;
import java.awt.*;
import java.util.Properties;
import org.jdatepicker.impl.*;

import Helpers.DateLabelFormatter;
import components.DataGridView;

public class Locations extends JFrame {
    private JLabel selectedClientLabel; // لعرض اسم العميل المختار

    public Locations() {
        setTitle("Add Location");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 500);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // اللوحة العلوية مع مسافات أفقية وعمودية
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10)); // 20px مسافة أفقية، 10px مسافة عمودية

        // زر اختيار العميل
        JButton selectClientButton = new JButton("Select Client");
        selectedClientLabel = new JLabel("Selected Client: None"); // النص الافتراضي       
        selectClientButton.addActionListener(e -> {
            new ClientSelection(selectedClient -> {
                // تحديث النص باسم العميل المختار
                selectedClientLabel.setText("Selected Client: " + selectedClient);
            });
        });

        JLabel selectedCarLabel = new JLabel("Selected Car:");
        // باقي المكونات
        JComboBox<String> selectCar = new JComboBox<>(new String[]{"Car 1", "Car 2", "Car 3"});
        JLabel dateLabel = new JLabel("Select Date:");

        Properties properties = new Properties();
        properties.put("text.today", "Today");
        properties.put("text.month", "Month");
        properties.put("text.year", "Year");

        JDatePanelImpl datePanel = new JDatePanelImpl(new UtilDateModel(), properties);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        datePicker.setPreferredSize(new Dimension(150, 30)); // تكبير عرض مكون التاريخ
        
        JButton addButton = new JButton("add");
        // إضافة المكونات بالترتيب المطلوب مع مسافات بين المكونات
        panel.add(selectedClientLabel);     // عرض العميل المختار
        panel.add(selectClientButton);       // زر اختيار العميل
        panel.add(selectedCarLabel); 
        panel.add(selectCar);               // قائمة اختيار السيارة
        panel.add(dateLabel);               // نص اختيار التاريخ
        panel.add(datePicker);              // مكون اختيار التاريخ
        panel.add(addButton); 
        // إضافة اللوحة العلوية إلى الجزء الشمالي
        add(panel, BorderLayout.NORTH);

        // الجدول
        String[] columnNames = {"ID", "Name", "date debut", "date fin", "statut", "montant total"};
        Object[][] data = {
            {1, "moh", "30/11/2024", "05/12/2024", "en cour", "15 000 DA"},
            {99, "nazim", "10/11/2024", "25/11/2024", "en cour", "25 000 DA"}
        };

        DataGridView grid = new DataGridView(columnNames, data);
        add(grid, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Locations();
    }
}
