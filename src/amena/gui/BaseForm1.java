/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena.gui;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.UIManager;

import amena.entities.Colis;

/**
 *
 * @author hp
 */
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;

public class BaseForm1 extends Form {
    public BaseForm1(Form previous) {
        setTitle("Home");

        Label titleLabel = new Label("Choissir une option");
        titleLabel.getUnselectedStyle().setAlignment(CENTER);

        Button btnAddColis = new Button("Ajouter Colis");
        Button btnListColis = new Button("La Liste des colis");

        btnAddColis.addActionListener(e -> new AjouterColis(this).show());
        btnListColis.addActionListener(e -> new ListColis(this).show());

        setLayout(new BorderLayout());
        add(BorderLayout.NORTH, titleLabel);

        GridLayout grid = new GridLayout(2, 1);

        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);

        Form inner = new Form(grid);
        inner.add(btnAddColis);
        inner.add(btnListColis);

        add(BorderLayout.CENTER, inner);
        add(BorderLayout.SOUTH, new Label(""));
          getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
}