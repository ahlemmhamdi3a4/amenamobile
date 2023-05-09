/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.ReservationGui;

import com.codename1.components.MultiButton;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Vehicule;
import com.mycompany.services.ServiceVehicule;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class ReservationHome extends Form {
   public static int idv ;  
    Form current;
    public ReservationHome(Resources res) {
        current=this;
           setTitle("Liste des vehicules");
        setLayout(BoxLayout.y());
        ServiceVehicule sv = new ServiceVehicule() ;  
        ArrayList<Vehicule> lv = sv.affichageVehicule() ; 
        Container list = new Container(BoxLayout.y());
         list.setScrollableY(true);
        for (Vehicule lvs : lv) {
            
            EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(50, 50, 0xffff0000), true);
              
            Image i = URLImage.createToStorage(placeholder,lvs.getImg(),lvs.getImg());
             MultiButton sp = new MultiButton(lvs.getImmat());
            sp.setIcon(i.fill(200, 200));
            sp.setTextLine1(lvs.getMarque() + " " + lvs.getModele());
              sp.setTextLine2(lvs.getPrix() + "dt");
            //  sp.setTextLine3("Age : "+lvs.getPrix());    
                     list.add(sp);
                    
                     sp.addActionListener((evt) -> {
                         new ReserverForm(current,lvs.getId()).show();
                         
                    //   new ReserverForm(this);
                     });
        }
        
         //SpanLabel sp = new SpanLabel();
        //sp.setText(ServiceVoyage.getInstance().affichageVoyage().toString());
        this.add(list); 
       /* getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
        new ProfileForm(res,this).show();
        });*/

        
    }
    
}
