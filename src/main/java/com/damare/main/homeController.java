package com.damare.main;

import com.damare.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import com.damare.model.applicationState;

import java.io.IOException;

import static com.damare.model.applicationState.getInstance;

public class homeController {

    @FXML
    private Button logoutBtn;

    @FXML
    private Button addTaskBtn;

    @FXML
    private Text helloUser;
    private Object state;


    @FXML
    private void initialize() {
        this.state = getInstance();
        User currentUser = applicationState.getInstance().getCurrentlyLoggedUser();
        helloUser.setText("Hello " + currentUser.getName());

        logoutBtn.setStyle("-fx-background-color: #B0266B;");
    }

    @FXML
    protected void toLogin() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();
            loginController loginController = loader.getController();

            Stage stage = (Stage) logoutBtn.getScene().getWindow();

            stage.setScene(new Scene(root));
        } catch (IOException ex) {

        }
    }

    @FXML
    protected void toAddTask() {
        try {
            // Load the FXML file for the new controller
            FXMLLoader loader = new FXMLLoader(getClass().getResource("addTask.fxml"));
            Parent root = loader.load();
            addTaskController addtaskController = loader.getController();

            // Get the current stage
            Stage stage = (Stage) logoutBtn.getScene().getWindow();

            // Set the new scene on the stage
            stage.setScene(new Scene(root));
        } catch (IOException ex) {
            // Handle exception
        }
    }

    @FXML
    protected void toUpdateUser() {
        try {
            // Load the FXML file for the new controller
            FXMLLoader loader = new FXMLLoader(getClass().getResource("updateUser.fxml"));
            Parent root = loader.load();
            updateUserController updateusercontroller = loader.getController();

            // Get the current stage
            Stage stage = (Stage) logoutBtn.getScene().getWindow();

            // Set the new scene on the stage
            stage.setScene(new Scene(root));
        } catch (IOException ex) {
            // Handle exception
        }
    }
}
