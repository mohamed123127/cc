package components;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class  DataGridView extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;

  
    public DataGridView(String[] columnNames, Object[][] data) {
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(data, columnNames);

        table = new JTable(tableModel);    
        
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JScrollPane scrollPane = new JScrollPane(table); 

        add(scrollPane, BorderLayout.CENTER);
    }

    // Method to add a row
    public void addRow(Object[] rowData) {
        tableModel.addRow(rowData);
    }

   

 
   
}
