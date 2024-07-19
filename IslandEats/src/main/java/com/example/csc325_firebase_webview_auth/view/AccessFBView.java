package com.example.csc325_firebase_webview_auth.view;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutionException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *  AccessFBView class is the controller for the main menu AccessFBView fxml document
 * */
public class AccessFBView {
    //member variables
    private boolean key;
    private ObservableList<Resource> listOfResources = FXCollections.observableArrayList();
    private Resource resource;

    //authentication checks if the user has been logged in before allowing them to add data
    private static boolean authentication = false;

    //Tableview + Columns declaration
    @FXML
    private TableView<Resource> table;
    @FXML
    private TableColumn<Resource, String> name;
    @FXML
    private TableColumn<Resource, String> address;
    @FXML
    private TableColumn<Resource, String> city;
    @FXML
    private TableColumn<Resource, String> hours;
    @FXML
    private TableColumn<Resource, String> state;
    @FXML
    private TableColumn<Resource, String> zipcode;

    /**
     *  initialize sets up the table view
     * */
    @FXML
    void initialize() {
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        city.setCellValueFactory(new PropertyValueFactory<>("city"));
        hours.setCellValueFactory(new PropertyValueFactory<>("hours"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        state.setCellValueFactory(new PropertyValueFactory<>("state"));
        zipcode.setCellValueFactory(new PropertyValueFactory<>("zipcode"));
        table.setItems(listOfResources);

        readFirebase();
    }

    /**
     *  public readRecord to call private method
     * */
    @FXML
    public void readRecord(ActionEvent event) {
        readFirebase();
    }

    /**
     *  private readFirebase reads records from firebase no sql database
     * */
    private boolean readFirebase() {
        key = false;

        //asynchronously retrieve all documents
        ApiFuture<QuerySnapshot> future = App.fstore.collection("Resources").get();
        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents;
        try {
            documents = future.get().getDocuments();
            if (documents.size() > 0) {
                //System.out.println("Outing....");
                for (QueryDocumentSnapshot document : documents) {
                    //System.out.println(document.getId() + " => " + document.getData().get("name"));
                    resource = new Resource(String.valueOf(document.getData().get("name")),
                            document.getData().get("address").toString(),
                            document.getData().get("city").toString(),
                            document.getData().get("zipcode").toString(),
                            document.getData().get("state").toString(),
                            document.getData().get("url").toString(),
                            document.getData().get("hours").toString());
                    listOfResources.add(resource);
                }
            } else {
                System.out.println("No data");
            }
            key = true;

        } catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
        }
        return key;
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
     *  LaunchLogin method is used to switch scenes to the login screen
     * */
    @FXML
    private void LaunchUserGuide() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/files/UserGuide.fxml"));
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
     *  LaunchAbout method is used to switch scenes to the About screen.
     * */
    @FXML
    private void LaunchAbout() {
        try {
            App.setRoot("/files/About.fxml");
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
