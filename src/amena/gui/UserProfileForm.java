package amena.gui;

import amena.entities.User;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Form;
import com.codename1.ui.Image;

import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

public class UserProfileForm {

    public Form form;
    public User user;

    public UserProfileForm(User user) {
        this.user = user;
        form = new Form(user.getNom());
        form.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        // Ajouter d'autres composants pour afficher les détails de l'utilisateur
        form.add(new SpanLabel("Nom d'utilisateur : " + user.getNom()));
        form.add(new SpanLabel("Email : " + user.getEmail()));

        // Afficher l'image de profil
        try {
            Image profileImage = Image.createImage(user.getImage());
            ImageViewer profileImageViewer = new ImageViewer(profileImage);
            form.add(profileImageViewer);
        } catch (Exception ex) {
            System.out.println("Erreur lors de l'affichage de l'image de profil : " + ex.getMessage());
        }
    }

    
    public Form getForm() {
        return form;
    }

    public void show() {
        form.show();
    }
}
