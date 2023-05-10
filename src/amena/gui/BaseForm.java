/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena.gui;

import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;
/**
 *
 * @author Ahlem
 */

public class BaseForm extends Form {
    public BaseForm(Form previous,Resources res) {
        setTitle("Home");

        Label titleLabel = new Label("Choose an Option");
        titleLabel.getUnselectedStyle().setAlignment(CENTER);
        Button btnWeather = new Button("Weather");
        Button btnAddComp = new Button("Add comp");
        Button btnListComp = new Button("List comp");
        
        btnWeather.addActionListener(e -> new WeatherApi(this).show());
        btnAddComp.addActionListener(e -> new AjouterComp(this).show());
        btnListComp.addActionListener(e -> new DetailsComp(this).show());
        

        setLayout(new BorderLayout());
        add(BorderLayout.NORTH, titleLabel);

        GridLayout grid = new GridLayout(2, 1);

        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);

        Form inner = new Form(grid);
        inner.add(btnAddComp);
        inner.add(btnListComp);
        inner.add(btnWeather);

        add(BorderLayout.CENTER, inner);
        add(BorderLayout.SOUTH, new Label(""));
         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
}