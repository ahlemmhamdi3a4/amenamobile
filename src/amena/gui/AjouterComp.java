/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena.gui;

import amena.entities.Competition;
import amena.services.SserviceComp;
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
import com.codename1.ui.Dialog;
import java.text.ParseException;
import java.text.SimpleDateFormat;



/**
 *
 * @author Ahlem
 */
public class AjouterComp extends Form {

    public AjouterComp(Form previous){
        setTitle("Add a new comp");
        setLayout(BoxLayout.y());

        TextField tfTitre = new TextField("", "Titre");
        TextField tfType = new TextField("", " Type");
        TextField tfDateDeb = new TextField("", "Date de début");
        TextField tfDateFin = new TextField("", "Date de fin");
        
        Button btnValider = new Button("Add comp");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfTitre.getText().length() == 0 || tfType.getText().length() == 0
                        || tfDateDeb.getText().length() == 0 || tfDateFin.getText().length() == 0
                        ) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        
                        Competition competition = new Competition();
                        competition.setTitle(tfTitre.getText());
                        competition.setType(Integer.parseInt(tfType.getText()));
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        competition.setDate_deb( sdf.parse(tfDateDeb.getText()));
                    competition.setDate_fin(sdf.parse(tfDateFin.getText()));
                        
                        if (SserviceComp.getInstance().ajouterComp(competition)) {
                            Dialog.show("Success", "comp added", new Command("OK"));
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Invalid poids value", new Command("OK"));
                    } catch (ParseException ex) {
                        System.out.println("aa");                    } 
                }
            }
        });

        addAll(tfTitre, tfType, tfDateDeb, tfDateFin, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    } 
    
}





