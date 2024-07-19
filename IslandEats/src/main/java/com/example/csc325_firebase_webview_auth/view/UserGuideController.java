package com.example.csc325_firebase_webview_auth.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for user guide fxml file
 */
public class UserGuideController {
    private static boolean authentication = false;

    /**
     *  LaunchLogin method is used to switch scenes to the login screen
     * */
    @FXML
    private void LaunchLogin() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/files/Login.fxml"));
            Parent parent = fxmlLoader.load();

            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *  LaunchAddRecord method is used to switch scenes to the add record screen. If the user has not logged in they will be unable to access this screen
     * */
    @FXML
    private void LaunchAddRecord() {
        if (authentication) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/files/AddResourceUI.fxml"));
                Parent parent = fxmlLoader.load();

                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);

                stage.setScene(scene);
                stage.showAndWait();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("ERROR: Administrator Login Required");
        }
    }

    /**
     *  LaunchMap method is used to switch scenes to the map.
     * */
    @FXML
    private void LaunchMap() {
        try {
            App.setRoot("/files/Map.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *  LaunchMain method is used to switch scenes to the main menu.
     * */
    @FXML
    private void LaunchMain() {
        try {
            App.setRoot("/files/AccessFBView.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *  Authenticate method is used to authorize administrators before allowing them to add data.
     * */
    void autheticate(boolean auth) {
        authentication = auth;
        System.out.println(authentication);
    }
}
