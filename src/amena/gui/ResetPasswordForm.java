/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena.gui;

import amena.services.UserService;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;

/**
 *
 * @author aymen
 */
public class ResetPasswordForm extends Form {

    private TextField emailField;
    private Button resetButton;
    private Button backButton;

    public ResetPasswordForm() {
        setTitle("Reset Password");

        emailField = new TextField("", "Email", 20, TextField.EMAILADDR);
        resetButton = new Button("Reset Password");
        backButton = new Button("Go Back");
        resetButton.addActionListener(e -> {
            String email = emailField.getText();
            if (email == null || email.isEmpty()) {
                Dialog.show("Error", "Email is required", "OK", null);
                return;
            }

            boolean success = UserService.getInstance().resetPassword(email);
            if (success) {
                Dialog.show("Success", "A password reset link has been sent to your email address", "OK", null);
            } else {
                Dialog.show("Error", "An error occurred while resetting your password. Please try again later.", "OK", null);
            }
        });

     

        addAll(emailField, resetButton, backButton);
    }
}
