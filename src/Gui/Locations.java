package Gui;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import Entities_CRUD.Locations_CRUD;
import components.DataGridView;

public class Locations extends JFrame {
    private DataGridView DataGridView;

    public Locations() {
        setTitle("Add Location");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

     
        JButton addButton = new JButton("Add Location");
        addButton.addActionListener(e -> {
        
             new AddLocations();
             ResultSet data = Locations_CRUD.GetAll();           
             DataGridView.SetDataSource(data);
        });
        JButton deleteButton= new JButton("delet");
        deleteButton.addActionListener(e -> {
            var selectedRow = DataGridView.getSelectedRowData();
            if (selectedRow != null) {
               
                String idReservation = String.valueOf(selectedRow[0]); 
        
               
                int confirmation = JOptionPane.showConfirmDialog(
                    this,
                    "هل تريد بالتأكيد حذف هذا الحجز؟",
                    "تأكيد الحذف",
                    JOptionPane.YES_NO_OPTION
                );
        
                if (confirmation == JOptionPane.YES_OPTION) {
                    
                    String condition = "id_reservation = " + idReservation;
                    boolean result = Locations_CRUD.Delete("reservation", condition);
        
                    if (result) {
                    
                        JOptionPane.showMessageDialog(this, "تم الحذف بنجاح!");
                      
                      ResultSet data = Locations_CRUD.GetAll();
             
                      DataGridView.SetDataSource(data);
                    } else {
                        JOptionPane.showMessageDialog(this, "فشل الحذف من قاعدة البيانات!", "خطأ", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "لم يتم تحديد أي صف للحذف!", "خطأ", JOptionPane.WARNING_MESSAGE);
            }
        });

     
        JPanel topPanel = new JPanel();
        topPanel.add(addButton);
        topPanel.add(deleteButton);

        add(topPanel, BorderLayout.NORTH);

    
        ResultSet data = Locations_CRUD.GetAll(); 
        String[] columnNames = {"id_reservation", "client", "voiture", "statut", "montant_total", "date_debut", "date_fin", "clientId", "carId"};
        DataGridView = new DataGridView(columnNames, data);
        

        add(DataGridView, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Locations());
    }
}
