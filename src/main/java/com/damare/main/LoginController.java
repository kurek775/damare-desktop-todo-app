package com.damare.main;

import com.damare.data.dbFunctions;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;

public class LoginController {
    dbFunctions db = new dbFunctions();
    Connection conn = db.connectDb("Test", "postgre", "Damare123");
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;

    @FXML
    private ListView<String> userView;
    @FXML
    private void initialize() {
        getUsers();
    }

    @FXML
    protected void onRegisterButtonClick() {
        db.insertRow(conn,"users",nameField.getText(),emailField.getText(),passwordField.getText());

    }

    private void getUsers() {

      db.readData(conn,"users");

    }



}