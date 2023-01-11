package com.damare.main;

import com.damare.model.Task;
import com.damare.model.User;
import com.damare.model.ApplicationState;
import com.damare.model.ControllerUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.ZoneId;
import java.util.Date;

import static com.damare.model.ApplicationState.getInstance;

public class AddTaskController {

    @FXML
    private Button homeBtn;
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
        User currentUser = ApplicationState.getInstance().getCurrentlyLoggedUser();
        homeBtn.setOnAction(ControllerUtils::toHome);


    }

    @FXML
    protected void onAddButtonClick() {
        this.state = getInstance();
        User currentUser = getInstance().getCurrentlyLoggedUser();

        inputVerification();

        String name = nameField.getText();

        /*
        int importance = 0;
        if (importanceField.getText().isEmpty()) {
            importance = 99999;
        }
        else {
        */
        int importance = Integer.parseInt(importanceField.getText());
        //}

        int duration = Integer.parseInt(durationField.getText());
        String place = placeField.getText();
        String description = descriptionField.getText();
        Date dateTime = Date.from(dateField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        int currentUserId = currentUser.getId();

        Task task = new Task(
                null,
                Integer.parseInt(catIdField.getText()),
                currentUserId,
                importance,
                duration,
                name,
                place,
                description,
                dateTime,
                false
        );

        ControllerUtils.addTask(task);

    }

    private void inputVerification(){
        if(nameField.getText().isEmpty() ){
            System.out.println("Name is required.");
        }
        if(!importanceField.getText().isEmpty() && !importanceField.getText().matches("\\d\\d?")) {
            System.out.println("Importance has to be a number from 0 to 99.");
        }
        if(!durationField.getText().isEmpty() && !durationField.getText().matches("\\d\\d?\\d?")) {
            System.out.println("Duration has to be a number from 0 to 999.");
        }
    }

}

