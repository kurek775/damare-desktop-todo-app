package com.damare.main;

import com.damare.model.Task;
import com.damare.model.User;
import com.damare.model.ApplicationState;
import com.damare.model.ControllerUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static com.damare.model.ApplicationState.getInstance;

public class UpdateTaskController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField importanceField;
    @FXML
    private TextField durationField;
    @FXML
    private TextField placeField;
    @FXML
    private TextField catIdField;
    @FXML
    private TextField descriptionField;
    @FXML
    private DatePicker dateField;
    private ApplicationState state;


    @FXML
    private void initialize() {

        this.state = getInstance();
        Task currentTask = ApplicationState.getInstance().getCurrentlyEditedTask();
        nameField.setText(currentTask.getName());
        importanceField.setText(currentTask.getImportance().toString());
        durationField.setText(currentTask.getDuration().toString());
        placeField.setText(currentTask.getPlace());
        catIdField.setText(currentTask.getCatId().toString());
        descriptionField.setText(currentTask.getDescription());
        dateField.setValue(LocalDate.parse(currentTask.getDate().toString()));


    }

    @FXML
    protected void onSaveButtonClick() {
        this.state = getInstance();
        User currentUser = ApplicationState.getInstance().getCurrentlyLoggedUser();
        Task currentTask = ApplicationState.getInstance().getCurrentlyEditedTask();
        if (nameField.getText().isEmpty()) {
            System.out.println("NAH");
        } else {
            Task task = new Task(currentTask.getId(), Integer.parseInt(catIdField.getText()), currentUser.getId(), Integer.parseInt(importanceField.getText()), Integer.parseInt(durationField.getText()), nameField.getText(), placeField.getText(),
                    descriptionField.getText(), Date.from(dateField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), true);

            ControllerUtils.updateTask(task);
            try {


                FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
                Parent root = loader.load();
                HomeController homeController = loader.getController();


                Stage stage = (Stage) nameField.getScene().getWindow();

                stage.setScene(new Scene(root));
            } catch (IOException ex) {

            }

        }


    }

}
