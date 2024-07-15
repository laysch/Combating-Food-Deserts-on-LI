package com.example.csc325_firebase_webview_auth.view;//package modelview;

import com.example.csc325_firebase_webview_auth.model.Resource;
import com.example.csc325_firebase_webview_auth.viewmodel.AccessDataViewModel;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;

import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutionException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class AccessFBView {
    @FXML
    private TextField nameField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField hoursField;
    @FXML
    private TextField stateField;
    @FXML
    private TextField urlField;
    @FXML
    private TextField zipField;
    @FXML
    private Button writeButton;
    @FXML
    private TextArea outputField;
    @FXML
    private Button switchScene;

    private boolean key;
    private ObservableList<Resource> listOfResources = FXCollections.observableArrayList();
    private Resource resource;

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

    public ObservableList<Resource> getListOfResroucess() {
        return listOfResources;
    }

    @FXML
    void initialize() {
        AccessDataViewModel accessDataViewModel = new AccessDataViewModel();
        nameField.textProperty().bindBidirectional(accessDataViewModel.resourceNameProperty());
        addressField.textProperty().bindBidirectional(accessDataViewModel.resourceAddressProperty());
        cityField.textProperty().bindBidirectional(accessDataViewModel.resourceCityProperty());
        stateField.textProperty().bindBidirectional(accessDataViewModel.resourceStateProperty());
        zipField.textProperty().bindBidirectional(accessDataViewModel.resourceZipProperty());
        hoursField.textProperty().bindBidirectional(accessDataViewModel.resourceHoursProperty());
        urlField.textProperty().bindBidirectional(accessDataViewModel.resourceUrlProperty());
        writeButton.disableProperty().bind(accessDataViewModel.isWritePossibleProperty().not());

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        city.setCellValueFactory(new PropertyValueFactory<>("city"));
        hours.setCellValueFactory(new PropertyValueFactory<>("hours"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        state.setCellValueFactory(new PropertyValueFactory<>("state"));
        zipcode.setCellValueFactory(new PropertyValueFactory<>("zipcode"));
        table.setItems(listOfResources);
    }

    @FXML
    private void addRecord(ActionEvent event) {
        addData();
    }

    @FXML
    private void readRecord(ActionEvent event) {
        readFirebase();
    }


    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("/files/WebContainer.fxml");
    }

    public void addData() {
        DocumentReference docRef = App.fstore.collection("Resources").document(UUID.randomUUID().toString());

        Map<String, Object> data = new HashMap<>();
        data.put("name", nameField.getText());
        data.put("address", addressField.getText());
        data.put("city", cityField.getText());
        data.put("state", stateField.getText());
        data.put("zipcode", zipField.getText());
        data.put("hours", hoursField.getText());
        data.put("url", urlField.getText());
        //asynchronously write data
        ApiFuture<WriteResult> result = docRef.set(data);
    }

    public boolean readFirebase() {
        key = false;

        //asynchronously retrieve all documents
        ApiFuture<QuerySnapshot> future = App.fstore.collection("Resources").get();
        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents;
        try {
            documents = future.get().getDocuments();
            if (documents.size() > 0) {
                System.out.println("Outing....");
                for (QueryDocumentSnapshot document : documents) {
                    outputField.setText(outputField.getText() + "Name: " + document.getData().get("name") + " , Address: " +
                            document.getData().get("address") + " , City: " +
                            document.getData().get("city") + " , State: " +
                            document.getData().get("state") + " , Zipcode: " +
                            document.getData().get("zipcode") + " , URL: " +
                            document.getData().get("url") + " , Hours: " +
                            document.getData().get("hours") + " \n ");
                    System.out.println(document.getId() + " => " + document.getData().get("name"));
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

    public boolean registerUser(String email, String password) {
        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail(email)
                .setEmailVerified(false)
                .setPassword(password)
                .setPhoneNumber("+11234567890")
                .setDisplayName("John Doe")
                .setDisabled(false);

        UserRecord userRecord;
        try {
            userRecord = App.fauth.createUser(request);
            System.out.println("Successfully created new user: " + userRecord.getUid());
            return true;

        } catch (FirebaseAuthException ex) {
            // Logger.getLogger(FirestoreContext.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public void Switch() throws IOException {
        App.setRoot("/files/Map.fxml");
    }


}
