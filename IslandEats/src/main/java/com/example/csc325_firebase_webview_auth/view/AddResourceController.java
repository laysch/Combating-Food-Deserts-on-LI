package com.example.csc325_firebase_webview_auth.view;

import com.example.csc325_firebase_webview_auth.viewmodel.AccessDataViewModel;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.WriteResult;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 *  AddResourceController class is the controller for the AddResourceUI fxml document
 * */
public class AddResourceController {
    //fxml member variables
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
    private TextField zipField;
    @FXML
    private TextField urlField;
    @FXML
    private Button writeButton;

    /**
     *  initialize prepares textfields prior to user input
     * */
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
    }

    /**
     *  addRecord adds user inputted records to the firebase no sql database
     * */
    @FXML
    private void addData() {
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

        try {
            if(result.get() != null) {
                System.out.println("Data Added Successfully");
                resetFields();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *  resetFields method is used to clear all text fields
     * */
    @FXML
    private void resetFields() {
        nameField.clear();
        addressField.clear();
        cityField.clear();
        stateField.clear();
        zipField.clear();
        hoursField.clear();
        urlField.clear();
    }
}
