package com.example.csc325_firebase_webview_auth.view;


import com.example.csc325_firebase_webview_auth.model.FirestoreContext;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;

import java.io.FileInputStream;
import java.io.IOException;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.util.Duration;

//test
/**
 * JavaFX App
 */
public class App extends Application {

    @FXML
    Button RegisterButton;

    public static Firestore fstore;
    public static FirebaseAuth fauth;
    public static Scene scene;
    private final FirestoreContext contxtFirebase = new FirestoreContext();

    @Override
    public void start(Stage primaryStage) throws Exception {
        fstore = contxtFirebase.firebase();
        fauth = FirebaseAuth.getInstance();
        scene = new Scene(loadFXML("/files/AccessFBView.fxml"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml ));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
