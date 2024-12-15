package Gui;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import Entities_CRUD.Locations_CRUD;
import components.DataGridView;
import CustomControle.*;

public class Locations extends JFrame {

    private JLabel selectedClientLabel; // لعرض اسم العميل المختار
    public DataGridView DataGridView;

    public Locations() {
        setTitle("Add Location");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        this.setBackground(new Color(245, 245, 245));

        TextFieldStyle1 searchTextField = new TextFieldStyle1();
     
        ButtonStyle1 addButton = new ButtonStyle1("Ajouter ");
        addButton.addActionListener(e -> {
        
             new AddLocations(this);
            
        });
        ButtonStyle1 deleteButton= new ButtonStyle1("supprimer");
        deleteButton.addActionListener(e -> {
            var selectedRow = DataGridView.getSelectedRowData();

            if (selectedRow != null) {
               
                String idReservation = String.valueOf(selectedRow[0]); 
        
                int confirmation = JOptionPane.showConfirmDialog(
                    this,
                    "Voulez-vous vraiment supprimer cette réservation ?",  // Message de confirmation en français
                    "Confirmation de suppression",  // Titre de la boîte de dialogue en français
                    JOptionPane.YES_NO_OPTION  // Options Oui et Non
                );
        
                if (confirmation == JOptionPane.YES_OPTION) {
                    
                    String condition = "id_reservation = " + idReservation;
                    boolean result = Locations_CRUD.Delete("reservation", condition);
        
                    if (result) {
                    
                        JOptionPane.showMessageDialog(this, "Suppression réussie !");
                      
                      ResultSet data = Locations_CRUD.GetAll();
             
                      DataGridView.SetDataSource(data);
                    } else {
                        JOptionPane.showMessageDialog(this, "La réservation n'a pas été supprimée.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Aucune ligne sélectionnée pour la suppression !", "Erreur", JOptionPane.WARNING_MESSAGE);
          if(selectedRow != null){
                CarDamageEvaluation instance = new CarDamageEvaluation(selectedRow,this);
                instance.setVisible(true);
                JOptionPane.showMessageDialog(null, "s");
            }else{
                JOptionPane.showMessageDialog(null, "Not selected row");

            }
        }});


     
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(searchTextField);
        topPanel.add(addButton);
        topPanel.add(deleteButton);
       
        add(topPanel, BorderLayout.NORTH);

    
        ResultSet data = Locations_CRUD.GetAll(); 
        String[] columnNames = {"id_reservation", "client", "voiture", "statut", "montant_total", "date_debut", "date_fin", "clientId", "carId"};
        DataGridView = new DataGridView(columnNames, data);
        
       

        add(DataGridView, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Locations());
    }
}
