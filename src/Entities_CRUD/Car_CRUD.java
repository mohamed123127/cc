package Entities_CRUD;

import java.sql.ResultSet;

import Helpers.DbOperation;

public class Car_CRUD {
    private static  DbOperation db = new DbOperation();

    public static void UpdateStatus(int id_car,String statut){
        db.Update("vehicule","etat='" + statut + "'","id_vehicule=" + id_car);
    }
       public static ResultSet GetAll(){
        ResultSet data = db.GetData("*", "vehicule");
        return data;
    }
    public static Object[][] GetDataa(String[] columnNames, String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'GetDataa'");
    }

}
