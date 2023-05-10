/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author hp
 */

import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.List;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;
import service.ColisCRUD;
import modeles.Colis;

/**
 *
 * @author bhk
 */
public class ListColis extends Form {
    public ListColis(Form previous) {
        setTitle("List Colis");
        setLayout(BoxLayout.y());

        ArrayList<Colis> colisList = ColisCRUD.getInstance().getColisList();

        for (Colis colis : colisList) {
            MultiButton mb = new MultiButton();
            mb.setTextLine1(colis.getNomExpediteur());
            mb.setTextLine2(colis.getNomDestinataire());
            mb.setTextLine3(String.valueOf(colis.getPoids()));
            
            // Bouton de modification
            Button btnModifier = new Button("Modifier");
            btnModifier.addActionListener((ActionListener) (ActionEvent evt) -> {
                new ModifierColis(colis, previous).show();
            });
            
            // Bouton de suppression
            Button btnSupprimer = new Button("Supprimer");
            btnSupprimer.addActionListener((ActionListener) (ActionEvent evt) -> {
                new deleteColis(colis.getId(), previous).show();
            });
            
            // Bouton d'affichage des détails
            Button btnDetails = new Button("Détails");
            btnDetails.addActionListener((ActionListener) (ActionEvent evt) -> {
                new DetailsColis(colis).show();
            });
            
            Container buttonsContainer = new Container(BoxLayout.x());
            buttonsContainer.addAll(btnModifier, btnSupprimer, btnDetails);
            
            addAll(mb, buttonsContainer);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
}
