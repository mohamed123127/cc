package Helpers;

import Config.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DbOperation {

    private Connection conn;
    private String query = "";
    private String lastError = ""; // لتخزين آخر رسالة خطأ

    public DbOperation() {
        Database db = new Database();
        try {
            conn = db.GetConnection();
            if (conn == null || conn.isClosed()) {
                lastError = "Échec de la connexion à la base de données.";
            }
        } catch (Exception e) {
            lastError = e.getMessage();
            e.printStackTrace();
        }
    }

    // دالة لاختبار الاتصال بقاعدة البيانات
    public boolean testConnection() {
        try {
            return conn != null && !conn.isClosed();
        } catch (SQLException e) {
            lastError = e.getMessage();
            e.printStackTrace();
            return false;
        }
    }

    // دالة للحصول على آخر خطأ
    public String getLastError() {
        return lastError;
    }

    public ResultSet GetData(String columns, String tableName) {
        try {
            query = "SELECT " + columns + " FROM " + tableName;
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);
            return result;
        } catch (Exception e) {
            lastError = e.getMessage();
            JOptionPane.showMessageDialog(null,
                    "Une erreur s'est produite lors de la récupération des données : " + e.getMessage(), "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public ResultSet GetData(String columns, String tableName, String JoinQuery) {
        try {
            query = "SELECT " + columns + " FROM " + tableName + " " + JoinQuery;
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);
            return result;
        } catch (Exception e) {
            lastError = e.getMessage();
            JOptionPane.showMessageDialog(null,
                    "Une erreur s'est produite lors de la récupération des données : " + e.getMessage(), "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public ResultSet GetFilltredData(String columns, String tableName, String condition) {
        try {
            query = "SELECT " + columns + " FROM " + tableName + " WHERE " + condition;
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);
            return result;
        } catch (Exception e) {
            lastError = e.getMessage();
            JOptionPane
                    .showMessageDialog(
                            null, "Une erreur s'est produite lors de la récupération des données \nSelect phrase: "
                                    + query + "\nerror message: " + e.getMessage(),
                            "Erreur", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public boolean Insert(String tableName, String columns, String values) {
        try {
            query = "INSERT INTO " + tableName + " (" + columns + ") VALUES (" + values + ")";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (Exception e) {
            lastError = e.getMessage();
            JOptionPane.showMessageDialog(null, "Une erreur s'est produite lors de la insertion des données : \n"
                    + "query: " + query + "\n error message: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean Update(String tableName, String columnsAndValues, String condition) {
        try {
            query = "UPDATE " + tableName + " SET " + columnsAndValues + " WHERE " + condition;
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (Exception e) {
            lastError = e.getMessage();
            JOptionPane.showMessageDialog(null, "Une erreur s'est produite lors de la mise à jour des données : \n"
                    + "query: " + query + "\n error message: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
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
            lastError = e.getMessage();
            JOptionPane.showMessageDialog(null, "Une erreur s'est produite lors de la suppression des données : \n"
                    + "query: " + query + "\n error message: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
