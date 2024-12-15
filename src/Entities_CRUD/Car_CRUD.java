package Entities_CRUD;

import Helpers.DbOperation;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Car_CRUD {
    private static  DbOperation db = new DbOperation();

    public static void UpdateStatus(int id_car,String statut){
        db.Update("vehicule","etat='" + statut + "'","id_vehicule=" + id_car);
    }

   public static int getLocationPrice(int id_car) {
        try {
            ResultSet resultSet = db.GetFilltredData("prix_location_jour", "vehicule", "id_vehicule = " + id_car);
            if (resultSet.next()) {
                return resultSet.getInt("prix_location_jour");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; 
    }
}
