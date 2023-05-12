/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena.gui;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import amena.entities.Colis;
import amena.services.ColisCRUD;
import amena.util.Vars;

import com.codename1.ui.Dialog;

public class AjouterColis extends Form {

    public AjouterColis(Form previous) {
        setTitle("Add a new colis");
        setLayout(BoxLayout.y());

        TextField tfNomExpediteur = new TextField("", "Nom Expediteur");
        tfNomExpediteur.getStyle().setFgColor(0x000000);
        TextField tfAdresseExpediteur = new TextField("", "Adresse Expediteur");
        tfAdresseExpediteur.getStyle().setFgColor(0x000000);
        TextField tfNomDestinataire = new TextField("", "Nom Destinataire");
        tfNomDestinataire.getStyle().setFgColor(0x000000);
        TextField tfAdresseDestinataire = new TextField("", "Adresse Destinataire");
        tfAdresseDestinataire.getStyle().setFgColor(0x000000);
        TextField tfPoids = new TextField("", "Poids");
        tfPoids.getStyle().setFgColor(0x000000);
        Button btnValider = new Button("Add colis");
       
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfNomExpediteur.getText().length() == 0 || tfAdresseExpediteur.getText().length() == 0
                        || tfNomDestinataire.getText().length() == 0 || tfAdresseDestinataire.getText().length() == 0
                        || tfPoids.getText().length() == 0) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        float poids = Float.parseFloat(tfPoids.getText());
                        Colis colis = new Colis();
                        colis.setNomExpediteur(tfNomExpediteur.getText());
                        colis.setAdresseExpediteur(tfAdresseExpediteur.getText());
                        colis.setNomDestinataire(tfNomDestinataire.getText());
                        colis.setAdresseDestinataire(tfAdresseDestinataire.getText());
                        colis.setPoids(poids);
                        colis.setIdu(Vars.current_user.getId());

                        if (ColisCRUD.getInstance().addColis(colis)) {
                            Dialog.show("Success", "Colis added", new Command("OK"));
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Invalid poids value", new Command("OK"));
                    }
                }
            }
        });

        addAll(tfNomExpediteur, tfAdresseExpediteur, tfNomDestinataire, tfAdresseDestinataire, tfPoids, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
}
