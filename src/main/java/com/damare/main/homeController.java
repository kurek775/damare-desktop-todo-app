package com.damare.main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class homeController {

    @FXML
    private Button logoutBtn;


    @FXML
    private void initialize() {



        logoutBtn.setStyle("-fx-background-color: #B0266B;");
    }
    @FXML
    protected void toLogin() {
        try {
            // Load the FXML file for the new controller
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();
            loginController loginController = loader.getController();

            // Get the current stage
            Stage stage = (Stage) logoutBtn.getScene().getWindow();

            // Set the new scene on the stage
            stage.setScene(new Scene(root));
        } catch (IOException ex) {
            // Handle exception
        }
    }
}
