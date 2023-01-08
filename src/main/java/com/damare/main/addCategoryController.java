package com.damare.main;

import com.damare.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.ZoneId;
import java.util.Date;

import static com.damare.model.applicationState.getInstance;

public class addCategoryController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField descriptionField;

    private applicationState state;


    @FXML
    private void initialize() {

        this.state = getInstance();
        User currentUser = applicationState.getInstance().getCurrentlyLoggedUser();



    }

    @FXML
    protected void onAddButtonClick() {
        this.state = getInstance();
        User currentUser = applicationState.getInstance().getCurrentlyLoggedUser();

        if(nameField.getText().isEmpty() ){
            System.out.println("NAH");
        }
        else{
            Category category = new Category(null,nameField.getText(),descriptionField.getText(),currentUser.getId());
            controllerUtils.addCategory(category);

        }


    }

}
