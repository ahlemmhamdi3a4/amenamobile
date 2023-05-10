/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author hp
 */
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.rest.Response;
import com.codename1.io.rest.Rest;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import modeles.Colis;
import utils.Statics;

public class DetailsColis extends Form {
    public DetailsColis(Colis colis) {
        setTitle("Détails Colis");
        setLayout(BoxLayout.y());

        ConnectionRequest request = new ConnectionRequest() {
            @Override
            protected void readResponse(InputStream input) throws IOException {
                JSONParser parser = new JSONParser();
                Map<String, Object> responseJson = parser.parseJSON(new InputStreamReader(input));
                displayColisDetails(responseJson);
            }

            @Override
            protected void handleErrorResponseCode(int code, String message) {
                Dialog.show("Error", "Failed to fetch colis details", new Command("OK"));
            }

            @Override
            protected void handleException(Exception err) {
                Dialog.show("Error", "Failed to parse server response", new Command("OK"));
            }
        };

        request.setUrl(Statics.BASE_URL + "allColis/" + colis.getId());
        request.setHttpMethod("GET");
        request.addRequestHeader("Accept", "application/json");

        NetworkManager.getInstance().addToQueue(request);
    }

    private void displayColisDetails(Map<String, Object> colisDetails) {
       
    int id = ((Double) colisDetails.get("id")).intValue();
    String nomExpediteur = (String) colisDetails.get("nomExpediteur");
    String adresseExpediteur = (String) colisDetails.get("adresseExpediteur");
    String nomDestinataire = (String) colisDetails.get("nomDestinataire");
    String adresseDestinataire = (String) colisDetails.get("adresseDestinataire");
    float poids = ((Double) colisDetails.get("poids")).floatValue();
    String statut = (String) colisDetails.get("statut");
    String dateExpedition = (String) colisDetails.get("dateExpedition");

        Label lblId = new Label("ID: " + id);
        Label lblNomExpediteur = new Label("Nom Expediteur: " + nomExpediteur);
        Label lblAdresseExpediteur = new Label("Adresse Expediteur: " + adresseExpediteur);
        Label lblNomDestinataire = new Label("Nom Destinataire: " + nomDestinataire);
        Label lblAdresseDestinataire = new Label("Adresse Destinataire: " + adresseDestinataire);
        Label lblPoids = new Label("Poids: " + poids);
        Label lblStatut = new Label("Statut: " + statut);
        Label lblDateExpedition = new Label("Date d'expédition: " + dateExpedition);

        addAll(lblId, lblNomExpediteur, lblAdresseExpediteur, lblNomDestinataire, lblAdresseDestinataire,
                lblPoids, lblStatut, lblDateExpedition);
        
    }

}