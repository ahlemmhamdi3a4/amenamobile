/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena.gui;

/**
 *
 * @author aymen
 */
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import amena.services.AnnonceCRUD;

/**
 *
 * @author Nour Saidi
 */

public class deleteAnnonce extends Form {
    public deleteAnnonce(int AnnonceId, Form previous) {
        setTitle("suppression d'annonce");
        setLayout(BoxLayout.y());

        Label confirmationLabel = new Label("etes vous sure de vouloir supprimer cette annnonce?");
        Button btnSupprimer = new Button("annonce supprimée");

        btnSupprimer.addActionListener((ActionListener) (ActionEvent evt) -> {
            boolean deleted = AnnonceCRUD.getInstance().deleteAnnonce(AnnonceId);
            if (deleted) {
                Dialog.show("Success", "Colis deleted", new Command("OK"));
                previous.showBack();
            } else {
                Dialog.show("ERROR", "Server error", new Command("OK"));
            }
        });

        addAll(confirmationLabel, btnSupprimer);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

}