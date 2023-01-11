package com.damare.main;

import com.damare.model.ApplicationState;
import com.damare.model.User;
import com.damare.model.ControllerUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginBtn;

    @FXML
    private Button registerBtn;


    @FXML
    private void initialize() {


        registerBtn.setStyle("-fx-background-color: #00ff00;");
        loginBtn.setStyle("-fx-background-color: #B0266B;");
        loginBtn.setOnAction(ControllerUtils::toLogin);
        registerBtn.setOnAction(e -> {

            boolean validatePassword = ControllerUtils.validatePassword(passwordField.getText(), (Stage) loginBtn.getScene().getWindow());
            boolean validateName = ControllerUtils.validateUsername(nameField.getText(), (Stage) loginBtn.getScene().getWindow());
            boolean validateEmail = ControllerUtils.validateEmail(emailField.getText(), (Stage) loginBtn.getScene().getWindow());
            if (nameField.getText().isEmpty() || emailField.getText().isEmpty() || passwordField.getText().isEmpty() || validateEmail || validateName || validatePassword) {
                System.out.println("NAH");
            } else {
                User user = new User(null, nameField.getText(), emailField.getText(), passwordField.getText());
                ControllerUtils.addUser(user);
                ControllerUtils.toLogin(e);

            }
        });
    }


}