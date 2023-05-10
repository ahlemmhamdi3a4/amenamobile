/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import service.ColisCRUD;
import modeles.Colis;
/**
 *
 * @author hp
 */
public class ModifierColis extends Form {
    
    /**
     *
     * @param colis
     * @param previous
     */
   
    public ModifierColis(Colis colis, Form previous) {
        setTitle("Modifier Colis");
        setLayout(BoxLayout.y());

        TextField tfNomExpediteur = new TextField(colis.getNomExpediteur(), "Nom Expediteur");
        TextField tfAdresseExpediteur = new TextField(colis.getAdresseExpediteur(), "Adresse Expediteur");
        TextField tfNomDestinataire = new TextField(colis.getNomDestinataire(), "Nom Destinataire");
        TextField tfAdresseDestinataire = new TextField(colis.getAdresseDestinataire(), "Adresse Destinataire");
        TextField tfPoids = new TextField(Float.toString(colis.getPoids()), "Poids");
        Button btnModifier = new Button("Update colis");

        btnModifier.addActionListener((ActionListener) (ActionEvent evt) -> {
            if (tfNomExpediteur.getText().length() == 0 || tfAdresseExpediteur.getText().length() == 0
                    || tfNomDestinataire.getText().length() == 0 || tfAdresseDestinataire.getText().length() == 0
                    || tfPoids.getText().length() == 0) {
                Dialog.show("Alert", "Veuillez remplir tous les champs", new Command("OK"));
            } else {
                try {
                    float poids = Float.parseFloat(tfPoids.getText());
                    colis.setNomExpediteur(tfNomExpediteur.getText());
                    colis.setAdresseExpediteur(tfAdresseExpediteur.getText());
                    colis.setNomDestinataire(tfNomDestinataire.getText());
                    colis.setAdresseDestinataire(tfAdresseDestinataire.getText());
                    colis.setPoids(poids);
                    
                    if (ColisCRUD.getInstance().updateColis(colis)) {
                        Dialog.show("Success", "Colis modifier", new Command("OK"));
                        previous.showBack();
                    } else {
                        Dialog.show("ERROR", "Erreur", new Command("OK"));
                    }
                } catch (NumberFormatException e) {
                    Dialog.show("ERROR", "Poids invalide", new Command("OK"));
                }
            }
        });

        addAll(tfNomExpediteur, tfAdresseExpediteur, tfNomDestinataire, tfAdresseDestinataire, tfPoids, btnModifier);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
}
