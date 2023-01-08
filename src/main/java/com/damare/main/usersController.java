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

import static com.damare.model.applicationState.getInstance;

public class usersController {


    @FXML
    private ListView<User> userListView;
    @FXML
    private ListView<FriendRequest> requestListView;

    @FXML
    private ListView<User> friendsListView;

    private Object state;


    @FXML
    private void initialize() {
        this.state = getInstance();
        User currentUser = applicationState.getInstance().getCurrentlyLoggedUser();


        loadUsers();
        loadRequesters();
        loadFriends();

        userListView.setCellFactory(taskListView -> new ListCell<>() {
            @Override
            protected void updateItem(User user, boolean empty) {
                super.updateItem(user, empty);
                if (!empty) {
                    setText(user.getName());

                    Button add = new Button("+");
                    add.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            FriendRequest request = new FriendRequest(null, currentUser.getId(), user.getId(), 0, "send");
                            controllerUtils.addRequest(request);
                            loadUsers();
                            loadRequesters();


                        }
                    });


                    setGraphic(new HBox(add));

                } else {
                    setText(null);
                    setGraphic(null);
                }
            }
        });
       friendsListView.setCellFactory(taskListView -> new ListCell<>() {
            @Override
            protected void updateItem(User user, boolean empty) {
                super.updateItem(user, empty);
                if (!empty) {
                    setText(user.getName());



                } else {
                    setText(null);
                    setGraphic(null);
                }
            }
        });

        requestListView.setCellFactory(taskListView -> new ListCell<>() {
            @Override
            protected void updateItem(FriendRequest req, boolean empty) {
                super.updateItem(req, empty);
                if (!empty) {
                    User usr = controllerUtils.findUser(req.getRequesterId());
                    setText(usr.getName());

                    Button add = new Button("ACCEPT");
                    add.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            req.setStatusCode(1);
                            req.setStatusName("accepted");
                            controllerUtils.updateFriendRequest(req);
                            loadRequesters();
                            loadFriends();
                        }
                    });


                    setGraphic(new HBox(add));

                } else {
                    setText(null);
                    setGraphic(null);
                }
            }
        });

    }


    private void loadUsers() {

        userListView.getItems().clear();
        this.state = getInstance();

        userListView.getItems().addAll(controllerUtils.viewAllUsers());
    }

    private void loadRequesters() {
        requestListView.getItems().clear();
        this.state = getInstance();
        User currentUser = applicationState.getInstance().getCurrentlyLoggedUser();
        requestListView.getItems().addAll(controllerUtils.viewRequesters(currentUser.getId()));
    }
    private void loadFriends() {
        friendsListView.getItems().clear();
        this.state = getInstance();
        User currentUser = applicationState.getInstance().getCurrentlyLoggedUser();
        friendsListView.getItems().addAll(controllerUtils.viewAllFriends(currentUser.getId()));
    }

}
