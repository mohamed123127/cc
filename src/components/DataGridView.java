package components;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Entities_CRUD.Locations_CRUD;

public class DataGridView extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;

    // Constructor without ActionListener
    public DataGridView(String[] columnNames, ResultSet data) {
        setLayout(new BorderLayout());
        //table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableModel = new DefaultTableModel(columnNames,0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // All cells are non-editable
            }
        };
        
        table = new JTable(tableModel);    
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        SetDataSource(data);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
         // Add MouseListener for double-click
        

        JScrollPane scrollPane = new JScrollPane(table); 

        add(scrollPane, BorderLayout.CENTER);
    }

    public void addRow(Object[] rowData) {
        tableModel.addRow(rowData);
    }

    public void SetDataSource(ResultSet data) {
        try {
            if (data != null) {
                tableModel.setRowCount(0); // Clear existing data
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

    public Object[] getSelectedRowData() {
        int selectedRow = table.getSelectedRow();  // Get the index of the selected row
        // Ensure that a row is selected
        if (selectedRow != -1) {
            int columnCount = table.getColumnCount();
            Object[] rowData = new Object[columnCount];
    
            // Loop through each column to retrieve the value for the selected row
            for (int column = 0; column < columnCount; column++) {
                rowData[column] = table.getValueAt(selectedRow, column);
            }
    
            return rowData;  // Return the data as an array of objects
        } else {
            //System.out.println("No row selected");
            return null;  // Return null if no row is selected
        }
    }
    
   

}
