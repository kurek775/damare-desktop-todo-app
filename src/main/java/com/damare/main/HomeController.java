package com.damare.main;

import com.damare.model.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

import static com.damare.model.ApplicationState.getInstance;

public class HomeController {

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
        User currentUser = ApplicationState.getInstance().getCurrentlyLoggedUser();
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
                            ControllerUtils.removeTask(task.getId());
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
                            ControllerUtils.removeCategory(category.getId());
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
        User currentUser = ApplicationState.getInstance().getCurrentlyLoggedUser();
        taskListView.getItems().addAll(ControllerUtils.viewTasks(currentUser.getId()));
    }

    private void loadMyCategories() {

        categoryListView.getItems().clear();
        this.state = getInstance();
        User currentUser = ApplicationState.getInstance().getCurrentlyLoggedUser();
        categoryListView.getItems().addAll(ControllerUtils.viewCategories(currentUser.getId()));
    }

    public void clickOnLoadedTask(MouseEvent mouseEvent) {
        this.state = getInstance();
        Task clicked = taskListView.getSelectionModel().getSelectedItem();


        ApplicationState.getInstance().setCurrentlyEditedTask(clicked);
        if (clicked == null) return;
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("updateTask.fxml"));
            Parent root = loader.load();
            UpdateTaskController updateTaskController = loader.getController();

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
            LoginController loginController = loader.getController();

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
            AddTaskController addtaskController = loader.getController();

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
            AddCategoryController addCategoryController = loader.getController();

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
            UpdateUserController updateusercontroller = loader.getController();

            // Get the current stage
            Stage stage = (Stage) logoutBtn.getScene().getWindow();

            // Set the new scene on the stage
            stage.setScene(new Scene(root));
        } catch (IOException ex) {
            // Handle exception
        }
    }

    @FXML
    protected void toFriends() {
        try {
            // Load the FXML file for the new controller
            FXMLLoader loader = new FXMLLoader(getClass().getResource("users.fxml"));
            Parent root = loader.load();
            UsersController usersController = loader.getController();

            // Get the current stage
            Stage stage = (Stage) logoutBtn.getScene().getWindow();

            // Set the new scene on the stage
            stage.setScene(new Scene(root));
        } catch (IOException ex) {
            // Handle exception
        }
    }
}
