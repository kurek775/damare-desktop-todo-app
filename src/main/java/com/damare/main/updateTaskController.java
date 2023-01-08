package com.damare.main;

import com.damare.model.Task;
import com.damare.model.User;
import com.damare.model.applicationState;
import com.damare.model.controllerUtils;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static com.damare.model.applicationState.getInstance;

public class updateTaskController {

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
    private applicationState state;


    @FXML
    private void initialize() {

        this.state = getInstance();
        Task currentTask = applicationState.getInstance().getCurrentlyEditedTask();
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
        User currentUser = applicationState.getInstance().getCurrentlyLoggedUser();
        Task currentTask = applicationState.getInstance().getCurrentlyEditedTask();
        if (nameField.getText().isEmpty()) {
            System.out.println("NAH");
        } else {
            Task task = new Task(currentTask.getId(), Integer.parseInt(catIdField.getText()), currentUser.getId(), Integer.parseInt(importanceField.getText()), Integer.parseInt(durationField.getText()), nameField.getText(), placeField.getText(),
                    descriptionField.getText(), Date.from(dateField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), true);

            controllerUtils.updateTask(task);

        }


    }

}
