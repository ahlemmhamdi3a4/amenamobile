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
import amena.services.AnnonceCRUD;
import amena.entities.Annonces;
import amena.util.Vars;


import com.codename1.ui.Dialog;

public class AjouterAnnonces extends Form {

    public AjouterAnnonces(Form previous) {
        setTitle("ajouter une nouvelle annonce");
        setLayout(BoxLayout.y());
        TextField tfType = new TextField("", "Type");
        tfType.getStyle().setFgColor(0x000000);
        
        TextField tfVilledep = new TextField("", "Ville de dep");
        tfVilledep.getStyle().setFgColor(0x000000);
        TextField tfVillearr = new TextField("", "Ville d'arrivée");
        tfVillearr.getStyle().setFgColor(0x000000);
        TextField tfDatedep = new TextField("", "Date de depart");
        tfDatedep.getStyle().setFgColor(0x000000);
        
        TextField tfDatearr = new TextField("", "Date d'arrivée");
        tfDatearr.getStyle().setFgColor(0x000000);
        TextField tfDesc = new TextField("", "Description");
        tfDesc.getStyle().setFgColor(0x000000);
        TextField tfPrix = new TextField("", "Prix");
        tfPrix.getStyle().setFgColor(0x000000);
        Button btnValider = new Button("Ajouter Annonce");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfType.getText().length() == 0 || tfVilledep.getText().length() == 0
                        || tfVillearr.getText().length() == 0 || tfDatedep.getText().length() == 0
                        || tfDatearr.getText().length() == 0) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                       int prix = Integer.parseInt(tfPrix.getText());
                        Annonces annonce = new Annonces();
                        annonce.setType(tfType.getText());
                        annonce.setVilleDep(tfVilledep.getText());
                        annonce.setVilleArr(tfVillearr.getText());
                        annonce.setDateDep(tfDatedep.getText());
                        annonce.setDateArr(tfDatearr.getText());
                        annonce.setDescription(tfDesc.getText());
                        annonce.setIdu(Vars.current_user.getId());
                       
                        if (AnnonceCRUD.getInstance().addAnnonce(annonce)) {
                            Dialog.show("Success", "annonce added", new Command("OK"));
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Invalid poids value", new Command("OK"));
                    }
                }
            }
        });

        addAll(tfType, tfVilledep, tfVillearr, tfDatedep, tfDatearr,tfDesc,tfPrix,  btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
}