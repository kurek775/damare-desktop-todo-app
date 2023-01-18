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
import java.awt.Desktop;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static com.damare.model.ApplicationState.getInstance;

public class HomeController {
    @FXML
    private Button addCategoryBtn;
    @FXML
    private Button friendsBtn;
    @FXML
    private Button updateUserBtn;
    @FXML
    private Button logoutBtn;
    @FXML
    private ListView<Task> taskListView;

    @FXML
    private ListView<Category> categoryListView;
    @FXML
    private Button addTaskBtn;

    @FXML
    private Button leaderBoardBtn;

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
        logoutBtn.setOnAction(ControllerUtils::toLogin);
        addTaskBtn.setOnAction(ControllerUtils::toAddTask);
        addCategoryBtn.setOnAction(ControllerUtils::toAddCategory);
        friendsBtn.setOnAction(ControllerUtils::toUsers);
        updateUserBtn.setOnAction(ControllerUtils::toUpdateUser);
        leaderBoardBtn.setOnAction(ControllerUtils::toLeaderBoard);
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
                    Button google = new Button("google");
                    google.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            try {

                                Desktop.getDesktop().browse(new URI(ControllerUtils.createGoogleLink(task.getDate(),task.getName(), task.getDuration())));

                            } catch (IOException | URISyntaxException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                    setGraphic(new HBox(delete,google));

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

        ActionEvent ae = new ActionEvent(mouseEvent.getSource(),mouseEvent.getTarget());
        ApplicationState.getInstance().setCurrentlyEditedTask(clicked);
        if (clicked == null) return;
        ControllerUtils.toUpdateTask(ae);
       /* try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("updateTask.fxml"));
            Parent root = loader.load();
            UpdateTaskController updateTaskController = loader.getController();

            Stage stage = (Stage) logoutBtn.getScene().getWindow();

            stage.setScene(new Scene(root));
        } catch (IOException ex) {

        }*/


    }

}
