/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena.gui;


import amena.services.SserviceComp;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Ahlem
 */
public class DeleteCompForm extends Form {
    public DeleteCompForm(int id, Form previous) {
        setTitle("Delete competition");
        setLayout(BoxLayout.y());

        Label confirmationLabel = new Label("Are you sure you want to delete this comp?");
        Button btnSupprimer = new Button("Delete colis");

        btnSupprimer.addActionListener((ActionListener) (ActionEvent evt) -> {
            boolean deleted = SserviceComp.getInstance().deleteComp(id);
            if (deleted) {
                Dialog.show("Success", "Comp deleted", new Command("OK"));
                previous.showBack();
            } else {
                Dialog.show("ERROR", "Server error", new Command("OK"));
            }
        });

        addAll(confirmationLabel, btnSupprimer);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
    
}
