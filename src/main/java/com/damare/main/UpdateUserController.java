package com.damare.main;

import com.damare.model.User;
import com.damare.model.ApplicationState;
import com.damare.model.ControllerUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static com.damare.model.ApplicationState.getInstance;

public class UpdateUserController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;

    @FXML
    private Button homeBtn;

    @FXML
    private Button saveBtn;
    private ApplicationState state;


    @FXML
    private void initialize() {
        this.state = getInstance();
        User currentUser = ApplicationState.getInstance().getCurrentlyLoggedUser();
        nameField.setText(currentUser.getName());
        emailField.setText(currentUser.getEmail());
        passwordField.setText(currentUser.getPassword());
        homeBtn.setStyle("-fx-background-color: #00ff00;");
        saveBtn.setStyle("-fx-background-color: #B0266B;");
    }

    @FXML
    protected void onSaveButtonClick() {
        this.state = getInstance();
        User currentUser = ApplicationState.getInstance().getCurrentlyLoggedUser();


        if (nameField.getText().isEmpty() || emailField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            System.out.println("NAH");
        } else {
            User user = new User(currentUser.getId(), nameField.getText(), emailField.getText(), passwordField.getText());
            ControllerUtils.updateUser(user);
            ApplicationState.getInstance().setCurrentlyLoggedUser(user);

        }


    }

    @FXML
    protected void toHome() {
        try {
            // Load the FXML file for the new controller
            FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
            Parent root = loader.load();
            HomeController homecontroller = loader.getController();

            // Get the current stage
            Stage stage = (Stage) passwordField.getScene().getWindow();

            // Set the new scene on the stage
            stage.setScene(new Scene(root));
        } catch (IOException ex) {
            // Handle exception
        }
    }


}