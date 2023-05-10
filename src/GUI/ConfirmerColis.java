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
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.GenericListCellRenderer;
import com.codename1.ui.list.ListModel;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.util.ArrayList;
import modeles.Colis;
import service.ColisCRUD;

public class ConfirmerColis extends Form {

    private static final String ACCOUNT_SID = "AC0643a8413f76b0e5bdd8ea93378281d0";
    private static final String AUTH_TOKEN = "3272a8f83b9c1ac2e03ea6c6cbe24fb3";

    private ArrayList<Colis> colisList;
    private Container colisContainer;
    private Label noColisLabel;

    public ConfirmerColis() {
        super("Liste des colis");

        // Initialisation de l'interface
        colisList = ColisCRUD.getInstance().getColisList();
        colisContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        noColisLabel = new Label("Aucun colis");

        // Affichage des colis
        if (colisList.isEmpty()) {
            colisContainer.add(noColisLabel);
        } else {
            for (Colis colis : colisList) {
                MultiButton colisButton = new MultiButton(colis.getNomDestinataire());
                colisButton.setTextLine2(colis.getAdresseDestinataire());
                colisButton.setTextLine3("Poids : " + colis.getPoids() + " kg");
                colisButton.addActionListener(evt -> {
                    confirmDelivery(colis);
                });
                colisContainer.add(colisButton);
            }
        }

        // Ajout du container à la form
        add(colisContainer);

        // Commande pour rafraîchir la liste des colis
        Command refreshCommand = new Command("Actualiser") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                colisList = ColisCRUD.getInstance().getColisList();
                colisContainer.removeAll();
                if (colisList.isEmpty()) {
                    colisContainer.add(noColisLabel);
                } else {
                    for (Colis colis : colisList) {
                        MultiButton colisButton = new MultiButton(colis.getNomDestinataire());
                        colisButton.setTextLine2(colis.getAdresseDestinataire());
                        colisButton.setTextLine3("Poids : " + colis.getPoids() + " kg");
                        colisButton.addActionListener(evt2 -> {
                            confirmDelivery(colis);
                        });
                        colisContainer.add(colisButton);
                    }
                }
                colisContainer.revalidate();
            }
        };
        setToolbar(new Toolbar());
        getToolbar().addCommandToRightBar(refreshCommand);

        // Paramètres de la form
        setLayout(new BorderLayout());
        getContentPane().setScrollVisible(true);
        setScrollableY(true);
        setScrollableX(false);
    }
private void confirmDelivery(Colis colis) {
    // Affichage d'une popup de confirmation
    boolean confirmed = Dialog.show("Confirmation de livraison",
            "Voulez-vous confirmer la livraison du colis " + colis.getNomDestinataire() + " ?",
            "Confirmer", "Annuler");
    if (confirmed) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new PhoneNumber("+21625363115"),
                new PhoneNumber("+12766630621"),
        "Votre colis " + colis.getNomDestinataire() + " a été livré avec succès!")
        .create();
        System.out.println("Message SID: " + message.getSid());

        // Mettre à jour le statut du colis à "livré"
        colis.setStatut("livré");
        ColisCRUD.getInstance().updateColis(colis);


        // Affichage d'une popup pour confirmer la livraison
        Dialog.show("Livraison confirmée",
                "La livraison du colis " + colis.getNomDestinataire() + " a été confirmée avec succès!",
                "OK", null);
    }
}

}
