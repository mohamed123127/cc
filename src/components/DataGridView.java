package components;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.function.Consumer;
import java.util.function.Function;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class  DataGridView extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;

  
    public DataGridView(String[] columnNames, ResultSet data,ValueWrapper<String> wrapper) {
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(columnNames,0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // All cells are non-editable
            }
        };

        table = new JTable(tableModel);    
        SetDataSource(data);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
         // Add MouseListener for double-click
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Check for double-click
                if (e.getClickCount() == 2) {
                    rowDoubleClickEvent();
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table); 

        add(scrollPane, BorderLayout.CENTER);
    }

    // Method to add a row
    public void addRow(Object[] rowData) {
        tableModel.addRow(rowData);
    }

    public void SetDataSource(ResultSet data){
        try {
            if(data != null){
        tableModel.setRowCount(0); 
        ResultSetMetaData metaData = data.getMetaData();
        int columnCount = metaData.getColumnCount();

        // Add rows to the table model
        int rowNbr = 1;
        while (data.next()) {
            Object[] row = new Object[columnCount];
            row[0] = rowNbr;
            for (int i = 1; i <= columnCount; i++) {
                row[i - 1] = data.getObject(i); // ResultSet columns are 1-based
            }
            tableModel.addRow(row);
            rowNbr++;
        }
    }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error populating table: " + e.getMessage());
    }
}  

 
   
}
