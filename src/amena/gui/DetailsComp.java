/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package amena.gui;

import amena.entities.Competition;
import amena.services.SserviceComp;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;


/**
 *
 * @author Ahlem
 */
public class DetailsComp extends Form {
    public DetailsComp(Form previous) {
        setTitle("List Comp");
        setLayout(BoxLayout.y());

        ArrayList<Competition> compList = SserviceComp.getInstance().affichageCompetition();

        for (Competition competition : compList) {
            MultiButton mb = new MultiButton();
            mb.setTextLine1(competition.getTitle());
            mb.setTextLine2(competition.getDate_deb().toString());
            mb.setTextLine3(competition.getDate_fin().toString());
            mb.setTextLine4(Integer.toString(competition.getType()));
            
            // Bouton de modification
            Button btnModifier = new Button("Modifier");
            btnModifier.addActionListener((ActionListener) (ActionEvent evt) -> {
                new ModifierComp(competition, previous).show();
            });
            
            // Bouton de suppression
            Button btnSupprimer = new Button("Supprimer");
            btnSupprimer.addActionListener((ActionListener) (ActionEvent evt) -> {
                new DeleteCompForm(competition.getId(), previous).show();
            });
            
            // Bouton d'affichage des détails
           
            
            Container buttonsContainer = new Container(BoxLayout.x());
            buttonsContainer.addAll(btnModifier, btnSupprimer);
            
            addAll(mb, buttonsContainer);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
    
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author hp
 */


