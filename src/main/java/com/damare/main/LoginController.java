package com.damare.main;

import com.damare.model.ApplicationState;
import javafx.event.EventHandler;
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
    private void initialize() {


        loginBtn.setStyle("-fx-background-color: #00ff00;");
        registerBtn.setStyle("-fx-background-color: #B0266B;");
        registerBtn.setOnAction(ControllerUtils::toRegister);
        loginBtn.setOnAction(e -> {
            Integer id = ControllerUtils.loginUser(unameEmailField.getText(), passwordField.getText(), (Stage) loginBtn.getScene().getWindow());
            if (id != null) {
                ApplicationState.getInstance().setCurrentlyLoggedUser(ControllerUtils.findUser(id));
                ControllerUtils.toHome(e);
            }
            else{

            }
        });

    }






}