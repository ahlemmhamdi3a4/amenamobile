/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena.gui;

/**
 *
 * @author aymen
 */
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

import java.util.List;

import amena.entities.Annonces;

public class AfficherAnnonce extends Form {

    private final Container content = new Container(new BoxLayout(BoxLayout.Y_AXIS));

    public AfficherAnnonce(List<Annonces> annoncesList) {
        super("Liste d'annonces");

        for (Annonces annonce : annoncesList) {
            Container annonceContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            annonceContainer.setUIID("AnnonceContainer");

            Label typeLabel = new Label("Type: " + annonce.getType());
            Label villeDepLabel = new Label("Ville de départ: " + annonce.getVilleDep());
            Label villeArrLabel = new Label("Ville d'arrivée: " + annonce.getVilleArr());
            Label dateDepLabel = new Label("Date de départ: " + annonce.getDateDep().toString());
            Label dateArrLabel = new Label("Date d'arrivée: " + annonce.getDateArr().toString());
            Label descriptionLabel = new Label("Description: " + annonce.getDescription());
            Label prixLabel = new Label("Prix: " + annonce.getPrix() + " €");

            annonceContainer.addComponent(typeLabel);
            annonceContainer.addComponent(villeDepLabel);
            annonceContainer.addComponent(villeArrLabel);
            annonceContainer.addComponent(dateDepLabel);
            annonceContainer.addComponent(dateArrLabel);
            annonceContainer.addComponent(descriptionLabel);
            annonceContainer.addComponent(prixLabel);

            content.addComponent(annonceContainer);
            content.add(new Label(""), "newline");

        }

        add(content);
    }
}