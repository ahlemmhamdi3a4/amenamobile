/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena.gui;

import amena.entities.User;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author aymen
 */
public class UserProfileForm {

    public Form form;
    public User user;

    public UserProfileForm(User user) {
        this.user = user;
        form = new Form(user.getNom());
        form.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        // Ajouter des composants pour afficher les détails de l'utilisateur
        form.add(new SpanLabel("Nom d'utilisateur : " + user.getNom()));
        form.add(new SpanLabel("Email : " + user.getEmail()));
        // ...
    }

    public Form getForm() {
        return form;
    }

    public void show() {
        form.show();
    }
}
