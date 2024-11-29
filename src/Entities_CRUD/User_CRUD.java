package Entities_CRUD;

import Helpers.DbOperation;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class User_CRUD {

    public static void execute() {
        
        DbOperation db = new DbOperation();
        boolean isSuccessful = db.Insert("utilisateur", "nom, prenom, email, mot_de_passe, role", "'mohamed','louahchi','mohamedlouahchi9@gmail.com','mohamed230','admin'");
        //boolean isSuccessful = db.Update("utilisateur", "email='mohame@sda.asd',mot_de_passe='adsa'","id_utilisateur=10");
        //boolean isSuccessful = db.Delete("utilisateur", "id_utilisateur=5");
        if(isSuccessful){
                        JOptionPane.showMessageDialog(null, "Good");
        }else{
            JOptionPane.showMessageDialog(null, "Bad");
        }
    }

    public static JTable GetData() {
        DbOperation db = new DbOperation();
        boolean isSuccessful ;
        if(isSuccessful){
            JOptionPane.showMessageDialog(null, "Good");
        }else{
            JOptionPane.showMessageDialog(null, "Bad");
        }
    }
}
