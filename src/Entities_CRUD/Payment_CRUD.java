package Entities_CRUD;

import Helpers.DbOperation;
import java.time.LocalDate;
public class Payment_CRUD {

    private static DbOperation db = new DbOperation();

    public static boolean Add(int id_reservation, String type_payment, int montant, String mode_paiement, String date_paiement) {
        boolean isSuccessful = db.Insert("paiement", "id_reservation,type_payment,montant,date_paiement,mode_paiement", String.format("%d,'%s',%d,'%s','%s'", id_reservation,type_payment,montant,date_paiement,mode_paiement));
        return isSuccessful;
    }

    public static boolean AddFraisDammage(int id_reservation, int damageFees, String mode_paiement) {
        boolean isSuccessful = Add(id_reservation, "Frais de dommages", damageFees, mode_paiement, LocalDate.now().toString());
        return isSuccessful;
    }

    public static boolean AddlateReturnFees(int id_reservation, int lateReturnFees, String mode_paiement) {
        boolean isSuccessful = Add(id_reservation, "Frais de retard", lateReturnFees, mode_paiement, LocalDate.now().toString());
        return isSuccessful;
    }
    public static boolean addLocationPayment (int id_reservation, int montant, String mode_paiement) {
        boolean isSuccessful = Add(id_reservation, "Paiement de location", montant, mode_paiement, LocalDate.now().toString());
        return isSuccessful;
    }

}