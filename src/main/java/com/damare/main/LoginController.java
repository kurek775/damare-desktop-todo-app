package com.damare.main;

import com.damare.model.ApplicationState;
import com.damare.model.User;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
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
    private PasswordField passwordField;

    @FXML
    private Button loginBtn;

    @FXML
    private Button registerBtn;


    @FXML
    private void initialize() {


       // loginBtn.setStyle("-fx-background-color: #F2796B;");
       // registerBtn.setStyle("-fx-background-color: #F2796B;");
        registerBtn.setOnAction(ControllerUtils::toRegister);
        loginBtn.setOnAction(e -> {
            User usr = ControllerUtils.loginUser(unameEmailField.getText(), passwordField.getText(), (Stage) loginBtn.getScene().getWindow());
            if (usr.getId() != null) {
                ApplicationState.getInstance().setCurrentlyLoggedUser(usr);
                ControllerUtils.toHome(e);
            }
        });

    }


}