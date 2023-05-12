/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package amena.gui;

import amena.util.Vars;
import com.codename1.components.ToastBar;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;

/**
 * Common code that can setup the side menu
 *
 * @author Shai Almog
 */
public abstract class SideMenuBaseForm extends Form {

    private EncodedImage palceHolder;

    public SideMenuBaseForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public SideMenuBaseForm(String title) {
        super(title);
    }

    public SideMenuBaseForm() {
    }

    public SideMenuBaseForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }

    public void setupSideMenu(Resources res) {
        String image = Vars.current_user.getImage();
        Image profilePic = null;
        try {
            palceHolder = EncodedImage.create("/giphy.gif");
        } catch (IOException ex) {

        }
        if (image != null) {
            profilePic = URLImage.createToStorage(palceHolder, image, image);

        }
//        Image profilePic = res.getImage("user-picture.jpg");

        Image mask = res.getImage("round-mask.png");
        mask = mask.scaledHeight(mask.getHeight() / 4 * 3);
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(Vars.current_user.getNom(), profilePic, "SideMenuTitle");
        profilePicLabel.setMask(mask.createMask());

        Container sidemenuTop = BorderLayout.center(profilePicLabel);
        sidemenuTop.setUIID("SidemenuTop");

        getToolbar().addComponentToSideMenu(sidemenuTop);
        getToolbar().addMaterialCommandToSideMenu("  Dashboard", FontImage.MATERIAL_DASHBOARD, e -> showOtherForm(res));

        getToolbar().addMaterialCommandToSideMenu("  Reservation vehicule", FontImage.MATERIAL_DASHBOARD, e -> new ReservationHome(this, UIManager.initFirstTheme("/theme2")).show());
        getToolbar().addMaterialCommandToSideMenu("  Competition", FontImage.MATERIAL_DASHBOARD, e -> new BaseForm(this, UIManager.initFirstTheme("/theme2")).show());
        getToolbar().addMaterialCommandToSideMenu("  Colis", FontImage.MATERIAL_ACCESS_TIME, e -> new BaseForm1(this).show());
        getToolbar().addMaterialCommandToSideMenu(" Confirmer Colis", FontImage.MATERIAL_ACCESS_TIME, e -> new ConfirmerColis(this).show());
        getToolbar().addMaterialCommandToSideMenu("  Account Settings", FontImage.MATERIAL_SETTINGS, e -> new UserListGUI(this, res, Vars.current_user).show());
        getToolbar().addMaterialCommandToSideMenu("  Annonces", FontImage.MATERIAL_SETTINGS, e -> new AjouterAnnonces(this).show());
        getToolbar().addMaterialCommandToSideMenu("  Annonces", FontImage.MATERIAL_SETTINGS, e -> new ListAnnonce(this).show());

        getToolbar().addMaterialCommandToSideMenu("  Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> {new LoginForm(res).show();
        Vars.current_user=null;});
    }

    protected abstract void showOtherForm(Resources res);

}
