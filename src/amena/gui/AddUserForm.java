package amena.gui;

import amena.entities.User;
import amena.services.UserService;
import com.codename1.ui.Button;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;

public class AddUserForm extends Form {

    private TextField nomField, prenomField, cinField, emailField, passwordField, numtelField, adressField, date_n;
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
        Picker datePickerDeb = new Picker();
        datePickerDeb.setType(Display.PICKER_TYPE_DATE);
        datePickerDeb.setDate(new java.util.Date());
        datePickerDeb.getStyle().setBgColor(0xFFFFFF); // set background color to white
        datePickerDeb.getStyle().setFgColor(0x000000); // set foreground color to black
        datePickerDeb.getStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM)); // set font style
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
                datePickerDeb,
                ajouterBtn
        );
        content.setScrollableY(true);

        // Add container to the form
        add(BorderLayout.CENTER, content);

        // Bind actions to buttons
        ajouterBtn.addActionListener(e -> {
            if (validateInput()) {
                User u = new User(
                        nomField.getText(),
                        prenomField.getText(),
                        cinField.getText(),
                        emailField.getText(),
                        passwordField.getText(),
                        numtelField.getText(),
                        adressField.getText(),
                        datePickerDeb.getDate()
                );
                if (UserService.getInstance().addUser(u)) {
                    Dialog.show("Succès", "Utilisateur ajouté avec succès!", "OK", null);
                    new LoginForm(res).show();
                } else {
                    Dialog.show("Erreur", "Erreur lors de l'ajout de l'utilisateur.", "OK", null);
                }
            }
        });

    }

    public Form getAddUserForm() {
        return this;
    }

    private boolean validateInput() {
        if (nomField.getText().isEmpty()) {
            Dialog.show("Erreur", "Nom invalide", "OK", null);
            return false;
        }
        if (prenomField.getText().isEmpty()) {
            Dialog.show("Erreur", "Prénom invalide", "OK", null);
            return false;
        }
        if (cinField.getText().isEmpty()) {
            Dialog.show("Erreur", "CIN invalide (8 chiffres)", "OK", null);
            return false;
        }
        if (emailField.getText().isEmpty() || (!emailField.getText().endsWith(".com") && !emailField.getText().endsWith(".tn"))) {
            Dialog.show("Erreur", "Email invalide (l'adresse e-mail doit se terminer par .com ou .tn)", "OK", null);
            return false;
        }
        if (passwordField.getText().isEmpty() || passwordField.getText().length() < 8) {
            Dialog.show("Erreur", "Mot de passe invalide (min 8 caractères)", "OK", null);
            return false;
        }
        if (numtelField.getText().isEmpty()) {
            Dialog.show("Erreur", "Numéro de téléphone invalide (6 à 14 chiffres et doit commencer par +216)", "OK", null);
            return false;
        } else {
            String numtel = numtelField.getText().trim();
            if (numtel.length() < 6 || numtel.length() > 14 || !numtel.startsWith("+216")) {
                Dialog.show("Erreur", "Numéro de téléphone invalide (6 à 14 chiffres et doit commencer par +216)", "OK", null);
                return false;
            }
            for (int i = 1; i < numtel.length(); i++) {
                char c = numtel.charAt(i);
                if (!Character.isDigit(c)) {
                    Dialog.show("Erreur", "Numéro de téléphone invalide (caractères non numériques)", "OK", null);
                    return false;
                }
            }
        }
        if (adressField.getText().isEmpty()) {
            Dialog.show("Erreur", "Adresse invalide", "OK", null);
            return false;
        }
        return true;
    }
}
