package com.example.csc325_firebase_webview_auth.view;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Controller for login fxml file
 */
public class LoginController {
    @FXML
    TextField email;
    @FXML
    TextField uid;

    /**
     * HandleLoginButton method checks user input against firebase authentication storage
     */
    @FXML
    public void HandleLoginButton(ActionEvent event) {
        try {
            //checks user input against firebase
            UserRecord userEmail = FirebaseAuth.getInstance().getUserByEmail(email.getText());
            UserRecord userID = FirebaseAuth.getInstance().getUser(uid.getText());

            //if user input is good loads main menu
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/files/AccessFBView.fxml"));
            Parent root = loader.load();
            AccessFBView controller = loader.getController();
            controller.autheticate(true);

            App.setRoot("/files/AccessFBView.fxml");
        } catch (FirebaseAuthException e) {
            System.out.println("ERROR: User Does Not Exist");
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
