package com.damare.main;

import com.damare.data.dbFunctions;
import com.damare.model.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static com.damare.model.applicationState.getInstance;

public class homeController {

    @FXML
    private Button logoutBtn;
    @FXML
    private ListView<Task> taskListView;

    @FXML
    private ListView<Category> categoryListView;
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
        loadMyTasks();
        loadMyCategories();
        logoutBtn.setStyle("-fx-background-color: #B0266B;");

        taskListView.setCellFactory(taskListView -> new ListCell<>() {
            @Override
            protected void updateItem(Task task, boolean empty) {
                super.updateItem(task, empty);
                if (!empty) {
                    setText(task.getName());

                    Button delete = new Button("delete");
                    delete.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            controllerUtils.removeTask(task.getId());
                            loadMyTasks();
                        }
                    });


                    setGraphic(new HBox(delete));

                } else {
                    setText(null);
                    setGraphic(null);
                }
            }
        });


        categoryListView.setCellFactory(taskListView -> new ListCell<>() {
            @Override
            protected void updateItem(Category category, boolean empty) {
                super.updateItem(category, empty);
                if (!empty) {
                    setText(category.getName());

                    Button delete = new Button("delete");
                    delete.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            controllerUtils.removeCategory(category.getId());
                            loadMyCategories();
                        }
                    });


                    setGraphic(new HBox(delete));

                } else {
                    setText(null);
                    setGraphic(null);
                }
            }
        });
    }


    private void loadMyTasks() {

        taskListView.getItems().clear();
        this.state = getInstance();
        User currentUser = applicationState.getInstance().getCurrentlyLoggedUser();
        taskListView.getItems().addAll(controllerUtils.viewTasks(currentUser.getId()));
    }

    private void loadMyCategories() {

        categoryListView.getItems().clear();
        this.state = getInstance();
        User currentUser = applicationState.getInstance().getCurrentlyLoggedUser();
        categoryListView.getItems().addAll(controllerUtils.viewCategories(currentUser.getId()));
    }

    public void clickOnLoadedTask(MouseEvent mouseEvent) {
        this.state = getInstance();
        Task clicked = taskListView.getSelectionModel().getSelectedItem();


        applicationState.getInstance().setCurrentlyEditedTask(clicked);
        if (clicked == null) return;
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("updateTask.fxml"));
            Parent root = loader.load();
            updateTaskController updateTaskController = loader.getController();

            Stage stage = (Stage) logoutBtn.getScene().getWindow();

            stage.setScene(new Scene(root));
        } catch (IOException ex) {

        }


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
    protected void toAddCategory() {
        try {
            // Load the FXML file for the new controller
            FXMLLoader loader = new FXMLLoader(getClass().getResource("addCategory.fxml"));
            Parent root = loader.load();
            addCategoryController addCategoryController = loader.getController();

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
