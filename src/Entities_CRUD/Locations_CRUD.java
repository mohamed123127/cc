package Entities_CRUD;

import Helpers.DbOperation;
import java.sql.ResultSet;

public class Locations_CRUD {
    private static  DbOperation db = new DbOperation();

    public static ResultSet GetAll(){
        ResultSet data = db.GetData("reservation.id_reservation, client.nom, vehicule.modele, reservation.statut, CAST(COALESCE(SUM(paiement.montant), 0) AS INT) AS montant_total, reservation.date_debut, reservation.date_fin, reservation.id_client, reservation.id_vehicule", "reservation","INNER JOIN client ON reservation.id_client = client.id_client \r\n" + //
                        "INNER JOIN vehicule ON reservation.id_vehicule = vehicule.id_vehicule \r\n" + //
                        "LEFT JOIN paiement ON reservation.id_reservation = paiement.id_reservation \r\n" + //
                        "GROUP BY reservation.id_reservation, client.nom, vehicule.modele, reservation.statut, reservation.date_debut, reservation.date_fin, reservation.id_client, reservation.id_vehicule;");
        return data;
    }

    public static void UpdateStatus(int id_reservation,String statut){
        db.Update("reservation","statut='" + statut + "'","id_reservation=" + id_reservation);
    }

}
