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
 * Controller for map fxml file
 */
public class MapController {
    //fxml member variable
    @FXML
    WebView webView;

    //authentication checks if the user has been logged in before allowing them to add data
    private static boolean authentication = false;

    /**
     * Initialize method prepares WebView
     */
    @FXML
    public void initialize() {
        WebEngine engine = webView.getEngine();
        engine.setJavaScriptEnabled(true);
        engine.setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36");
        URL url = getClass().getResource("/files/Map.html");
        assert url != null;
        engine.load(url.toExternalForm());
    }

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
     *  LaunchForum method is used to switch scenes to the Community Forum.
     * */
    @FXML
    private void LaunchForum() {
        try {
            App.setRoot("/files/Forum.fxml");
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
