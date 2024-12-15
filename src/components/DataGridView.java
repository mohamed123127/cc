package components;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;

public class DataGridView extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;

    

    // Constructor without ActionListener
    public DataGridView(String[] columnNames, ResultSet data) {
        setLayout(new BorderLayout());
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // All cells are non-editable
            }
        };

        table = new JTable(tableModel);

        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Apply custom row renderer for alternating colors
        table.setDefaultRenderer(Object.class, new AlternatingRowColorRenderer());

        SetDataSource(data);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
         // Add MouseListener for double-click
        

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    public DataGridView(String[] columnNames, ResultSet data, int[] hiddenColumnIndex) {
        this(columnNames, data);
    public DataGridView(String[] columnNames, ResultSet data, int[] hiddenColumnIndex) {
        this(columnNames, data);

        for (int index : hiddenColumnIndex) {
            if (index >= 0 && index < table.getColumnCount()) {
                table.getColumnModel().getColumn(index).setMinWidth(0);
                table.getColumnModel().getColumn(index).setMaxWidth(0);
                table.getColumnModel().getColumn(index).setWidth(0);
                table.getColumnModel().getColumn(index).setResizable(false);
            }
        }
    }

    public DataGridView(String[] columnNames, Object[][] data) {
        // تحقق من أن أسماء الأعمدة والبيانات ليست null
        if (columnNames == null || data == null) {
            columnNames = new String[0];
            data = new Object[0][0];
        }

        // تهيئة DefaultTableModel
        tableModel = new DefaultTableModel(data, columnNames);

        // إنشاء JTable باستخدام tableModel
        table = new JTable(tableModel);

        // إعداد JScrollPane
        // this.setViewportView(table);
    }

    public void addRow(Object[] rowData) {
        tableModel.addRow(rowData);
    }

    public void removeRow(int rowIndex) {
        tableModel.removeRow(rowIndex);
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
        int selectedRow = table.getSelectedRow(); // Get the index of the selected row
        if (selectedRow != -1) {
            int columnCount = table.getColumnCount();
            Object[] rowData = new Object[columnCount];
    
            // Loop through each column to retrieve the value for the selected row
            for (int column = 0; column < columnCount; column++) {
                rowData[column] = table.getValueAt(selectedRow, column);
            }
    
            return rowData;  // Return the data as an array of objects
        } else {
            return null; // Return null if no row is selected
        }
    }

    // Custom cell renderer for alternating row colors
    private static class AlternatingRowColorRenderer extends DefaultTableCellRenderer {
        private final Color evenRowColor = new Color(173, 216, 230); // Sky blue
        private final Color oddRowColor = Color.WHITE; // White

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (!isSelected) {
                // Set alternating row colors
                if (row % 2 == 0) {
                    component.setBackground(evenRowColor);
                } else {
                    component.setBackground(oddRowColor);
                }
                component.setForeground(Color.BLACK); // Black text for readability
            } else {
                component.setBackground(table.getSelectionBackground());
                component.setForeground(table.getSelectionForeground());
            }

            return component;
        }
    }
}
