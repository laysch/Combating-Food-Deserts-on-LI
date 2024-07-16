package com.example.csc325_firebase_webview_auth.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * Controller for forum fxml file
 */
public class ForumController {
    @FXML
    WebView webView;

    private static boolean authentication = false;


    /**
     * Initialize method prepares and loads WebView
     */
    @FXML
    public void initialize() {
        WebEngine engine = webView.getEngine();
        //engine.setJavaScriptEnabled(false);
        engine.load("https://www.reddit.com/r/island_eats/");
        //engine.load("https://www.google.com");
    }

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

    @FXML
    private void LaunchMap() {
        try {
            App.setRoot("/files/Map.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    void autheticate(boolean auth) {
        authentication = auth;
        System.out.println(authentication);
    }
}
