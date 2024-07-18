package com.example.csc325_firebase_webview_auth.view;


import com.example.csc325_firebase_webview_auth.model.FirestoreContext;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * JavaFX App
 */
public class App extends Application {

    public static Firestore fstore;
    public static FirebaseAuth fauth;
    public static Scene scene;
    public static Scene splash;
    public static Stage primaryStage;
    private final FirestoreContext contxtFirebase = new FirestoreContext();

    /**
     * Loads initial fxml file
     */
    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        fstore = contxtFirebase.firebase();
        fauth = FirebaseAuth.getInstance();
        scene = new Scene(loadFXML("/files/AccessFBView.fxml"));
        splash = new Scene(loadFXML("/files/SplashScreen.fxml"));
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setScene(splash);
        primaryStage.show();
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(event -> {
            primaryStage.setScene(scene);
            primaryStage.show();
        });
        delay.play();
    }

    /**
     * Sets root fxml file
     */
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
        primaryStage.sizeToScene();
    }

    /**
     * Loads given fxml file
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
