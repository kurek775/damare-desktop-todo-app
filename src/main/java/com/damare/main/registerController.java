package com.damare.main;

import com.damare.model.User;
import com.damare.model.controllerUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class registerController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;

    @FXML
    private Button loginBtn;

    @FXML
    private Button registerBtn;


    @FXML
    private void initialize() {


        registerBtn.setStyle("-fx-background-color: #00ff00;");
        loginBtn.setStyle("-fx-background-color: #B0266B;");
    }

    @FXML
    protected void onRegisterButtonClick() {

        /* TODO -->  Tady musí proběhnout nějaká validace asi klasika e-mail regex(možná na to je nějaká knihovna)
        *  heslo alespon 8 znaků atd možná by stálo za to ho i zacryptovat pomocí bcryptu nebo něčeho takového
        *  at je to fakt validní
        *
        * */
        if(nameField.getText().isEmpty() || emailField.getText().isEmpty() || passwordField.getText().isEmpty() ){
            System.out.println("NAH");
        }
        else{
            User user = new User(null,nameField.getText(),emailField.getText(),passwordField.getText(),null);
            controllerUtils.addUser(user);
            toLogin();

        }


    }

    @FXML
    protected void toLogin() {
        try {
            // Load the FXML file for the new controller
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();
           loginController loginController = loader.getController();

            // Get the current stage
            Stage stage = (Stage) passwordField.getScene().getWindow();

            // Set the new scene on the stage
            stage.setScene(new Scene(root));
        } catch (IOException ex) {
            // Handle exception
        }
    }





}