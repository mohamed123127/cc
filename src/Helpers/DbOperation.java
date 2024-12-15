package Helpers;

import Config.Database;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class DbOperation {

    private Connection conn;
    private String query = "";

    public DbOperation() {
        Database db = new Database();
        conn = db.GetConnection();
    }

    public ResultSet GetData(String columns, String tableName) {
        try {
            query = "SELECT " + columns + " FROM " + tableName;
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(query);
        } catch (Exception e) {
            handleError("retrieving data", e);
            return null;
        }
    }

    public ResultSet GetData(String columns, String tableName, String joinQuery) {
        try {
            query = "SELECT " + columns + " FROM " + tableName + " " + joinQuery;
            copyToClipboard(query); // Copy the query for debugging purposes
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(query);
        } catch (Exception e) {
            handleError("retrieving data with join", e);
            return null;
        }
    }

    public ResultSet GetFilteredData(String columns, String tableName, String condition) {
        try {
            query = "SELECT " + columns + " FROM " + tableName + " WHERE " + condition;
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(query);
        } catch (Exception e) {
            handleError("retrieving filtered data", e);
            return null;
        }
    }

    public boolean Insert(String tableName, String columns, String values) {
        try {
            query = "INSERT INTO " + tableName + " (" + columns + ") VALUES (" + values + ")";
            copyToClipboard(query); // Copy the query for debugging purposes
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            handleError("inserting data", e);
            return false;
        }
    }

    public boolean Update(String tableName, String columnsAndValues, String condition) {
        try {
            query = "UPDATE " + tableName + " SET " + columnsAndValues + " WHERE " + condition;
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            handleError("updating data", e);
            return false;
        }
    }

    public boolean Delete(String tableName, String condition) {
        try {
            query = "DELETE FROM " + tableName + " WHERE " + condition;
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            handleError("deleting data", e);
            return false;
        }
    }

    // Helper method for handling errors and copying details to clipboard
    private void handleError(String operation, Exception e) {
        String errorMessage = "An error occurred while " + operation + ": \nQuery: " + query + "\nError: " + e.getMessage();
        JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
        copyToClipboard(errorMessage);
    }

    // Helper method to copy text to the clipboard
    private void copyToClipboard(String text) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection selection = new StringSelection(text);
        clipboard.setContents(selection, null);
    }
    public String isExists(String email, String password) {
        String query = "SELECT role FROM utilisateur WHERE email = ? AND mot_de_passe = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, email);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("role");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
