/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import amena.services.AnnonceCRUD;
import amena.entities.Annonces;
/**
 *
 * @author hp
 */
public class ModifierAnnonces extends Form {
    
    /**
     *
     * @param colis
     * @param previous
     */
   
    public ModifierAnnonces(Annonces annonce, Form previous) {
        setTitle("Update annonce");
        setLayout(BoxLayout.y());

         TextField tfType = new TextField("", "Type");
        TextField tfVilledep = new TextField("", "Ville de dep");
        TextField tfVillearr = new TextField("", "Ville d'arrivée");
        TextField tfDatedep = new TextField("", "Date de depart");
        TextField tfDatearr = new TextField("", "Date d'arrivée");
        TextField tfDesc = new TextField("", "Description");
        TextField tfPrix = new TextField("", "Prix");
        Button btnModifier = new Button("Update Annonce");

        btnModifier.addActionListener((ActionListener) (ActionEvent evt) -> {
                           if (tfType.getText().length() == 0 || tfVilledep.getText().length() == 0
                        || tfVillearr.getText().length() == 0 || tfDatedep.getText().length() == 0
                        || tfDatearr.getText().length() == 0) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
            } else {
                try {
                    int prix = Integer.parseInt(tfPrix.getText());
                        annonce.setType(tfType.getText());
                        annonce.setVilleDep(tfVilledep.getText());
                        annonce.setVilleArr(tfVillearr.getText());
                        annonce.setDateDep(tfDatedep.getText());
                        annonce.setDateArr(tfDatearr.getText());
                        annonce.setDescription(tfDesc.getText());
                    if (AnnonceCRUD.getInstance().updateAnnonce(annonce)) {
                        Dialog.show("Success", "annonce updated", new Command("OK"));
                        previous.showBack();
                    } else {
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    }
                } catch (NumberFormatException e) {
                    Dialog.show("ERROR", "Invalid poids value", new Command("OK"));
                }
            }
        });

        addAll(tfType, tfVilledep, tfVillearr, tfDatedep, tfDatearr,tfDesc,tfPrix, btnModifier);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
}