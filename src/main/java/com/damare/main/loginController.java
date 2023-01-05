package com.damare.main;

import com.damare.data.dbFunctions;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.damare.model.controllerUtils;

import java.io.IOException;
import java.sql.Connection;

public class loginController {

    @FXML
    private TextField unameEmailField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button loginBtn;

    @FXML
    private Button registerBtn;

    @FXML
    private void initialize() {


        loginBtn.setStyle("-fx-background-color: #00ff00;");
        registerBtn.setStyle("-fx-background-color: #B0266B;");
    }

    @FXML
    protected void onLoginButtonClick() {
        Integer id = controllerUtils.loginUser(unameEmailField.getText(), passwordField.getText(), (Stage) loginBtn.getScene().getWindow());
        if (id != null) {
            try {
                System.out.println(controllerUtils.currentUser(id).getName());
                // Load the FXML file for the new controller
                FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
                Parent root = loader.load();
                homeController homeController = loader.getController();

                // Get the current stage
                Stage stage = (Stage) loginBtn.getScene().getWindow();

                // Set the new scene on the stage
                stage.setScene(new Scene(root));
            } catch (IOException ex) {
                // Handle exception
            }
        }
        else{

        }


    }

    @FXML
    protected void toRegister() {

        try {
            // Load the FXML file for the new controller
            FXMLLoader loader = new FXMLLoader(getClass().getResource("register.fxml"));
            Parent root = loader.load();
            registerController registerController = loader.getController();

            // Get the current stage
            Stage stage = (Stage) passwordField.getScene().getWindow();

            // Set the new scene on the stage
            stage.setScene(new Scene(root));
        } catch (IOException ex) {
            // Handle exception
        }

    }


}