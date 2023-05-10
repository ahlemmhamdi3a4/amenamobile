package amena.gui;

import amena.entities.User;
import amena.services.UserService;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;

public class UpdateUserForm extends Form {

    private final User user;
    private final UserService userServices;

    public UpdateUserForm(Resources res, User user) {
        super(new BorderLayout());
        setTitle("Update User");
        this.user = user;
        this.userServices = UserService.getInstance();

        // Create UI components
        Label lblNom = new Label("Nom :");
        TextField txtNom = new TextField(user.getNom());
        Label lblPrenom = new Label("Prenom :");
        TextField txtPrenom = new TextField(user.getPrenom());
        Label lblCin = new Label("CIN :");
        TextField txtCin = new TextField(user.getCin());
        Label lblEmail = new Label("Email :");
        TextField txtEmail = new TextField(user.getEmail());
        Label lblPassword = new Label("Password :");
        TextField txtPassword = new TextField(user.getPassword());
        Label lblNumTel = new Label("NumTel :");
        TextField txtNumTel = new TextField(user.getNumtel());
        Label lblAdresse = new Label("Adresse :");
        TextField txtAdresse = new TextField(user.getAdress());
        Button btnUpdate = new Button("Update");

        // Add components to a container
        Container inputs = BoxLayout.encloseY(
                new SpanLabel("Update User"),
                lblNom, txtNom,
                lblPrenom, txtPrenom,
                lblCin, txtCin,
                lblEmail, txtEmail,
                lblPassword, txtPassword,
                lblNumTel, txtNumTel,
                lblAdresse, txtAdresse
        );
        inputs.setScrollableY(true);

        ComponentGroup group = new ComponentGroup();
        group.addComponent(inputs);
        add(BorderLayout.CENTER, group);

        FlowLayout flowLayout = new FlowLayout();
        Container buttons = new Container(flowLayout);
        buttons.addAll(
                btnUpdate
        );

        // Add container to the form
        add(BorderLayout.SOUTH, buttons);

        // Bind actions to buttons
        btnUpdate.addActionListener(e -> {
            User updatedUser = new User(
                    user.getId(),
                    txtNom.getText(),
                    txtPrenom.getText(),
                    txtCin.getText(),
                    txtEmail.getText(),
                   
                    user.getRole(),
                    txtNumTel.getText(),
                    txtAdresse.getText(),
                    user.getToken(),
                    user.getScore(),
                    user.getImage(),
                    user.isStatus()
            );
            if (userServices.updateUser(updatedUser)) {
                Dialog.show("Success", "User updated successfully", "OK", null);
               
            } else {
                Dialog.show("Error", "An error occurred while updating user", "OK", null);
            }
        });
    }

    public UpdateUserForm(Resources theme) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
