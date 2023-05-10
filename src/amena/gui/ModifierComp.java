/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena.gui;

import amena.entities.Competition;
import amena.services.SserviceComp;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*import java.util.logging.Level;
import java.util.logging.Logger;*/

/**
 *
 * @author Ahlem
 */
public class ModifierComp extends Form {

    /**
     *
     * @param competition
     * @param previous
     */
    public ModifierComp(Competition competition, Form previous) {
        setTitle("Update competition");
        setLayout(BoxLayout.y());

        TextField tfTitre = new TextField(competition.getTitle(), "Titre");
        TextField tfType = new TextField(Integer.toString(competition.getType()), "Type");
        TextField tfDateDebut = new TextField(competition.getDate_deb().toString(), "Date de début");
        TextField tfDateFin = new TextField(competition.getDate_fin().toString(), "Date de fin");
 
        Button btnModifier = new Button("Update competition");

        btnModifier.addActionListener((ActionListener) (ActionEvent evt) -> {
            
            
            if (tfTitre.getText().length() == 0 || tfType.getText().length() == 0
                    || tfDateDebut.getText().length() == 0 || tfDateFin.getText().length() == 0
                    ) {
                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
            } else {
                try {
                    
                    competition.setTitle(tfTitre.getText());
                    competition.setType(Integer.parseInt(tfType.getText()));
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    competition.setDate_deb( sdf.parse(tfDateDebut.getText()));
                    competition.setDate_fin(sdf.parse(tfDateFin.getText()));
                    

                  
                } catch (NumberFormatException e) {
                    Dialog.show("ERROR", "Invalid poids value", new Command("OK"));
                } catch (ParseException ex) {
                }
                  if (SserviceComp.getInstance().updateComp(competition)) {
                        Dialog.show("Success", "comp updated", new Command("OK"));
                        previous.showBack();
                    } else {
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    }
                
            }
        });

        addAll(tfTitre, tfType, tfDateDebut, tfDateFin, btnModifier);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

}


