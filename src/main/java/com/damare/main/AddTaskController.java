package com.damare.main;

import com.damare.model.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import org.w3c.dom.Text;

import java.awt.event.MouseEvent;
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
    private TextField Priority;
    @FXML
    private TextField durationField;
    @FXML
    private TextField placeField;
    @FXML
    private ComboBox<Category> catIdField;
    @FXML
    private TextField descriptionField;
    @FXML
    private DatePicker dateField;
    private ApplicationState state;

    @FXML
    private Text priorityText;



    @FXML
    private void initialize() {

        this.state = getInstance();
        User currentUser = ApplicationState.getInstance().getCurrentlyLoggedUser();
        homeBtn.setOnAction(ControllerUtils::toHome);
        loadMyCategories();
        formatComboBox();


    }



    @FXML
    protected void onAddButtonClick() {
        this.state = getInstance();
        User currentUser = getInstance().getCurrentlyLoggedUser();

        inputVerification();

        String name = nameField.getText();

        int importance = Integer.parseInt(importanceField.getText());

        int duration = Integer.parseInt(durationField.getText());

        String place = placeField.getText();
        if(place.isEmpty()) {
            place = "-";
        }

        String description = descriptionField.getText();
        if(description.isEmpty()) {
            description="-";
        }

        Date dateTime = Date.from(dateField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

        int currentUserId = currentUser.getId();

        int categoryId;
        if(catIdField.getValue().equals(null)) {
            categoryId = 0;
        }
        else {
            categoryId = catIdField.getValue().getId();
        }

        Task task = new Task(
                null,
                categoryId,
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

    private void inputVerification() {
        if (nameField.getText().isEmpty()) {
            System.out.println("Name is required.");
        }
        if (!importanceField.getText().isEmpty() && !importanceField.getText().matches("\\d\\d?")) {
            System.out.println("Importance has to be a number from 0 to 99.");
        }
        if (!durationField.getText().isEmpty() && !durationField.getText().matches("\\d\\d?\\d?")) {
            System.out.println("Duration has to be a number from 0 to 999.");
        }
    }

    private void loadMyCategories() {
        catIdField.getItems().clear();
        this.state = getInstance();
        User currentUser = ApplicationState.getInstance().getCurrentlyLoggedUser();
        catIdField.getItems().addAll(ControllerUtils.viewCategories(currentUser.getId()));
    }

    private void formatComboBox() {
        catIdField.setCellFactory(catIdField -> new ListCell<>() {
            @Override
            protected void updateItem(Category category, boolean empty) {
                super.updateItem(category, empty);
                if (!empty) {
                    setText(category.getName());

                } else {
                    setText(null);
                    setGraphic(null);
                }
            }

        });
    }

    public void updateText(javafx.scene.input.MouseEvent mouseEvent) {
    }
}

