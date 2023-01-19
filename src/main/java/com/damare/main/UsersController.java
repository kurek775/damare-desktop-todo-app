package com.damare.main;

import com.damare.model.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

import java.util.List;

import static com.damare.model.ApplicationState.getInstance;

public class UsersController {


    @FXML
    private ListView<User> userListView;
    @FXML
    private ListView<FriendRequest> requestListView;

    @FXML
    private ListView<User> friendsListView;
    @FXML
    private Button homeBtn;
    private Object state;


    @FXML
    private void initialize() {
        this.state = getInstance();
        User currentUser = ApplicationState.getInstance().getCurrentlyLoggedUser();
        homeBtn.setOnAction(ControllerUtils::toHome);

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
/*
                            List<FriendRequest> requests = ControllerUtils.viewRequesters(currentUser.getId());
                            Boolean checker = true;
                            for (FriendRequest fr : requests) {
                                if ((fr.getRequestedId().equals(user.getId()) && fr.getRequesterId().equals(currentUser.getId())) ||
                                        (fr.getRequestedId().equals(currentUser.getId()) && fr.getRequesterId().equals(user.getId()))
                                ) {
                                    checker = false;
                                }
                            }
                            if (checker) {


                            }
*/

                            FriendRequest request = new FriendRequest(null, currentUser.getId(), user.getId(), 0, "send");
                            ControllerUtils.addRequest(request);
                            loadUsers();
                            loadRequesters();

                        }
                    });
                    Boolean disableButton = false;
                    List<User> friends = ControllerUtils.viewAllFriends(currentUser.getId());
                   /* List<FriendRequest> requests = ControllerUtils.viewRequesters(currentUser.getId());
                    for (FriendRequest fr : requests) {
                        if (fr.getRequestedId().equals(user.getId()) || fr.getRequesterId().equals(user.getId())) {
                            disableButton = true;
                        }
                    }*/

                    for (User usr : friends) {
                        if (usr.getId().equals(user.getId())) {
                            disableButton = true;
                            break;
                        }
                    }
                    if (disableButton) {
                        setGraphic(new HBox());
                    } else {
                        setGraphic(new HBox(add));
                    }

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
                    User usr = ControllerUtils.findUser(req.getRequesterId());
                    setText(usr.getName());

                    Button add = new Button("ACCEPT");
                    add.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            req.setStatusCode(1);
                            req.setStatusName("accepted");
                            ControllerUtils.updateFriendRequest(req);
                            loadRequesters();
                            loadUsers();
                            loadFriends();
                        }
                    });

                    Button dec = new Button("DECLINE");
                    dec.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                            ControllerUtils.declineRequest(req.getId());
                            loadRequesters();
                            loadUsers();
                            loadFriends();
                        }
                    });


                    setGraphic(new HBox(add, dec));

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
        User currentUser = ApplicationState.getInstance().getCurrentlyLoggedUser();
        userListView.getItems().addAll(ControllerUtils.viewAllUsers(currentUser.getId()));
    }


    private void loadRequesters() {
        requestListView.getItems().clear();
        this.state = getInstance();
        User currentUser = ApplicationState.getInstance().getCurrentlyLoggedUser();
        requestListView.getItems().addAll(ControllerUtils.viewRequesters(currentUser.getId()));
    }

    private void loadFriends() {
        friendsListView.getItems().clear();
        this.state = getInstance();
        User currentUser = ApplicationState.getInstance().getCurrentlyLoggedUser();
        friendsListView.getItems().addAll(ControllerUtils.viewAllFriends(currentUser.getId()));
    }

}
