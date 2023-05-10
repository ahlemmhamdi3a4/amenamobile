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
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import amena.services.ColisCRUD;

public class deleteColis extends Form {
    public deleteColis(int colisId, Form previous) {
        setTitle("Delete Colis");
        setLayout(BoxLayout.y());

        Label confirmationLabel = new Label("Êtes-vous sûr de vouloir supprimer ce colis ?");
        Button btnSupprimer = new Button("Supprimer Colis");

        btnSupprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                boolean confirmed = Dialog.show("Confirmation", "Êtes-vous sûr de vouloir supprimer ce colis ?", "Oui", "Non");
                if (confirmed) {
                    boolean deleted = ColisCRUD.getInstance().deleteColis(colisId);
                    if (deleted) {
                        Dialog.show("Succès", "Le colis a été supprimé", new Command("OK"));
                        previous.showBack();
                    } else {
                        Dialog.show("Erreur", "Erreur du serveur", new Command("OK"));
                    }
                }
            }
        });

        addAll(confirmationLabel, btnSupprimer);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
}