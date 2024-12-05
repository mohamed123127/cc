package Entities_CRUD;

import Helpers.DbOperation;
import java.sql.ResultSet;

public class Locations_CRUD {
    private static  DbOperation db = new DbOperation();
    public static ResultSet GetAll(){
        ResultSet data = db.GetData("id_reservation,nom,modele,statut,montant_total,date_debut,date_fin,reservation.id_client,reservation.id_vehicule", "reservation","INNER JOIN client ON reservation.id_client = client.id_client INNER JOIN vehicule ON reservation.id_vehicule = vehicule.id_vehicule");
        return data;
    }
}
