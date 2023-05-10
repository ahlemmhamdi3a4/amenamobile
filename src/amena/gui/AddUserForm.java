package amena.gui;

import amena.entities.User;
import amena.services.UserService;
import com.codename1.ui.Button;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

public class AddUserForm extends Form {

    private TextField nomField, prenomField, cinField, emailField, passwordField, numtelField, adressField;
    private Button ajouterBtn;

    public AddUserForm(Resources res) {
        super(new BorderLayout());
        setTitle("Ajouter un utilisateur");

        // Create UI components
        nomField = new TextField("", "Nom", 20, TextField.ANY);
        prenomField = new TextField("", "Prenom", 20, TextField.ANY);
        cinField = new TextField("", "CIN", 20, TextField.NUMERIC);
        emailField = new TextField("", "Email", 20, TextField.EMAILADDR);
        passwordField = new TextField("", "Mot de passe", 20, TextField.PASSWORD);
        numtelField = new TextField("", "Numéro de téléphone", 20, TextField.PHONENUMBER);
        adressField = new TextField("", "Adresse", 20, TextField.ANY);

        ajouterBtn = new Button("Ajouter");

        // Add components to a container
        ComponentGroup fields = ComponentGroup.enclose(
                nomField,
                prenomField,
                cinField,
                emailField,
                passwordField,
                numtelField,
                adressField
        );
        fields.setScrollableY(true);

        Container content = BoxLayout.encloseY(
                new Label("Informations utilisateur"),
                fields,
                ajouterBtn
        );
        content.setScrollableY(true);

        // Add container to the form
        add(BorderLayout.CENTER, content);

        // Bind actions to buttons
        ajouterBtn.addActionListener(e -> {
            User u = new User(
                    nomField.getText(),
                    numtelField.getText(),
                    prenomField.getText(),
                    cinField.getText(),
                    emailField.getText(),
                    passwordField.getText(),
                    
                    adressField.getText()
            );
            if (UserService.getInstance().addUser(u)) {
                System.out.println("Utilisateur ajouté avec succès!");
            } else {
                System.out.println("Erreur lors de l'ajout de l'utilisateur.");
            }
        });
    }
}
