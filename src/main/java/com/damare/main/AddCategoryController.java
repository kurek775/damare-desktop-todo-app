package com.damare.main;

import com.damare.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import static com.damare.model.ApplicationState.getInstance;

public class AddCategoryController {
    @FXML
    private Button homeBtn;
    @FXML
    private TextField nameField;

    @FXML
    private TextField descriptionField;

    private ApplicationState state;


    @FXML
    private void initialize() {

        this.state = getInstance();
        User currentUser = ApplicationState.getInstance().getCurrentlyLoggedUser();
        homeBtn.setOnAction(ControllerUtils::toHome);


    }

    @FXML
    protected void onAddButtonClick(ActionEvent event) {
        this.state = getInstance();
        User currentUser = ApplicationState.getInstance().getCurrentlyLoggedUser();

        if(nameField.getText().isEmpty() ){
            System.out.println("NAH");
        }
        else{
            Category category = new Category(null,nameField.getText(),descriptionField.getText(),currentUser.getId());
            ControllerUtils.addCategory(category);

        }
        ControllerUtils.toHome(event);

    }

}
