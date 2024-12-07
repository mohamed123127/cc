package Gui;

import javax.swing.*;

import org.jdatepicker.impl.*;
import Gui.CarDamageEvaluation;
import java.awt.*;
import java.util.Properties;
import Entities_CRUD.Locations_CRUD;
import Helpers.DateLabelFormatter;
import components.DataGridView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Locations extends JFrame {
    private JLabel selectedClientLabel; // لعرض اسم العميل المختار
    public DataGridView DataGridView;

    public Locations() {
        setTitle("Add Location");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 500);
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
        JButton returnCarButton = new JButton("Restituer la voiture");
        returnCarButton.addActionListener(e->{
            var selectedRow = DataGridView.getSelectedRowData();
            if(selectedRow != null){
                CarDamageEvaluation instance = new CarDamageEvaluation(selectedRow,this);
                instance.setVisible(true);
                JOptionPane.showMessageDialog(null, "s");
            }else{
                JOptionPane.showMessageDialog(null, "Not selected row");
            }
            //JOptionPane.showMessageDialog(null,"ll");
        });
        // إضافة المكونات بالترتيب المطلوب مع مسافات بين المكونات
        panel.add(selectedClientLabel);     // عرض العميل المختار
        panel.add(selectClientButton);       // زر اختيار العميل
        panel.add(selectedCarLabel); 
        panel.add(selectCar);               // قائمة اختيار السيارة
        panel.add(dateLabel);               // نص اختيار التاريخ
        panel.add(datePicker);              // مكون اختيار التاريخ
        panel.add(addButton); 
        panel.add(returnCarButton);
        // إضافة اللوحة العلوية إلى الجزء الشمالي
        add(panel, BorderLayout.NORTH);

        // الجدول
        String[] columnNames = {"id_reservation","client","voiture","statut" , "montant_total", "date_debut", "date_fin","clientId","carId" };
        ResultSet data = Locations_CRUD.GetAll();
        DataGridView = new DataGridView(columnNames, data,new int[]{7,8});
        add(DataGridView, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Locations();
    }
}