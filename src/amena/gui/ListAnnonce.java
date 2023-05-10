/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena.gui;

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
import amena.services.AnnonceCRUD;
import amena.entities.Annonces;

/**
 *
 * @author bhk
 */
public class ListAnnonce extends Form {

    public ListAnnonce(Form previous) {
        setTitle("List des Annonces");
        setLayout(BoxLayout.y());

        ArrayList<Annonces> annonceList = AnnonceCRUD.getInstance().getAnnonceList();

        for (Annonces annonce : annonceList) {
            MultiButton mb = new MultiButton();
            mb.setTextLine1(annonce.getType());
            mb.setTextLine2(annonce.getVilleDep());
            mb.setTextLine3(annonce.getVilleArr());
            mb.setTextLine4(annonce.getDateDep());
            // mb.setTextLine5(annonce.getDateArr());
            // mb.setTextLine6(annonce.getDescription());

            // Bouton de modification
            Button btnModifier = new Button("Modifier");
            btnModifier.addActionListener((ActionListener) (ActionEvent evt) -> {
                new ModifierAnnonces(annonce, previous).show();
            });

            // Bouton de suppression
            Button btnSupprimer = new Button("Supprimer");
            btnSupprimer.addActionListener((ActionListener) (ActionEvent evt) -> {
                new deleteAnnonce(annonce.getId(), previous).show();
            });

            Container buttonsContainer = new Container(BoxLayout.x());
            buttonsContainer.addAll(btnModifier, btnSupprimer);

            addAll(mb, buttonsContainer);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    
}
