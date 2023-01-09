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
        User currentUser = ApplicationState.getInstance().getCurrentlyLoggedUser();

        if(nameField.getText().isEmpty() ){
            System.out.println("NAH");
        }
        else{
            Task task = new Task(null,Integer.parseInt(catIdField.getText()),currentUser.getId(),Integer.parseInt(importanceField.getText()),Integer.parseInt(durationField.getText()),nameField.getText(),placeField.getText(),
                    descriptionField.getText(), Date.from(dateField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),true);

            ControllerUtils.addTask(task);

        }


    }

}
