package amena.gui;

import amena.entities.Competition;
import com.codename1.ui.Button;
import amena.services.ServiceCompetition;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;

import amena.services.ServiceCompetition;
import com.codename1.ui.Dialog;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddCompetitionForm extends Form {
    
    public class AjouterCompetitionForm extends Form {
    private TextField titleTextField, dateDebTextField, dateFinTextField, typeTextField;
    private Button ajouterButton;

    public AjouterCompetitionForm() {
        super("Ajouter une compétition", BoxLayout.y());

        titleTextField = new TextField("", "Titre");
        dateDebTextField = new TextField("", "Date de début (YYYY-MM-DD)");
        dateFinTextField = new TextField("", "Date de fin (YYYY-MM-DD)");
        typeTextField = new TextField("", "Type");

        ajouterButton = new Button("Ajouter");

        ajouterButton.addActionListener(e -> {
            String title = titleTextField.getText().trim();
            String dateDeb = dateDebTextField.getText().trim();
            String dateFin = dateFinTextField.getText().trim();
            String type = typeTextField.getText().trim();

            if (title.isEmpty() || dateDeb.isEmpty() || dateFin.isEmpty() || type.isEmpty()) {
                Dialog.show("Erreur", "Veuillez remplir tous les champs", "OK", null);
                return;
            }

            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date dateDebFormatted = dateFormat.parse(dateDeb);
                Date dateFinFormatted = dateFormat.parse(dateFin);
                int typeFormatted = Integer.parseInt(type);

                Competition competition = new Competition(title, dateDebFormatted, dateFinFormatted, typeFormatted);
                ServiceCompetition.getInstance().ajouterCompetition(competition);

                Dialog.show("Succès", "La compétition a été ajoutée avec succès", "OK", null);
                titleTextField.setText("");
                dateDebTextField.setText("");
                dateFinTextField.setText("");
                typeTextField.setText("");
            } catch (ParseException ex) {
                Dialog.show("Erreur", "Format de date invalide", "OK", null);
            } catch (NumberFormatException ex) {
                Dialog.show("Erreur", "Le type doit être un entier", "OK", null);
            }
        });

        add(titleTextField);
        add(dateDebTextField);
        add(dateFinTextField);
        add(typeTextField);
        add(ajouterButton);
    }
}
}