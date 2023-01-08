package com.damare.main;

import com.damare.model.ApplicationState;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.damare.model.ControllerUtils;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField unameEmailField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button loginBtn;

    @FXML
    private Button registerBtn;

    @FXML

    private  Button btn;
    @FXML
    private void initialize() {


        loginBtn.setStyle("-fx-background-color: #00ff00;");
        registerBtn.setStyle("-fx-background-color: #B0266B;");

    }

    @FXML
    protected void onLoginButtonClick() {
        Integer id = ControllerUtils.loginUser(unameEmailField.getText(), passwordField.getText(), (Stage) loginBtn.getScene().getWindow());
        if (id != null) {
            try {
                ApplicationState.getInstance().setCurrentlyLoggedUser(ControllerUtils.findUser(id));

                FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
                Parent root = loader.load();
                HomeController homeController = loader.getController();


                Stage stage = (Stage) loginBtn.getScene().getWindow();

                stage.setScene(new Scene(root));
            } catch (IOException ex) {

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
            RegisterController registerController = loader.getController();

            // Get the current stage
            Stage stage = (Stage) passwordField.getScene().getWindow();

            // Set the new scene on the stage
            stage.setScene(new Scene(root));
        } catch (IOException ex) {
            // Handle exception
        }

    }


}