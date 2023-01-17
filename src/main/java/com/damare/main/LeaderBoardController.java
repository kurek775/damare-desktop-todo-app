package com.damare.main;

import com.damare.model.ApplicationState;
import com.damare.model.ControllerUtils;
import com.damare.model.FriendRequest;
import com.damare.model.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

import static com.damare.model.ApplicationState.getInstance;

public class LeaderBoardController {


    @FXML
    private ListView<User> globalLeaderBoardListView;
    @FXML
    private ListView<User> friendsLeaderBoardListView;
    @FXML
    private Button homeBtn;
    private Object state;

    @FXML
    private void initialize() {
        this.state = getInstance();
        User currentUser = ApplicationState.getInstance().getCurrentlyLoggedUser();
        homeBtn.setOnAction(ControllerUtils::toHome);

        loadGlobalLeaderBoardUsers();
        loadFriendsLeaderBoardUsers();
    }
    private void loadGlobalLeaderBoardUsers() {

        globalLeaderBoardListView.getItems().clear();
        this.state = getInstance();

        globalLeaderBoardListView.getItems().addAll(ControllerUtils.viewAllUsers());
    }
    private void loadFriendsLeaderBoardUsers() {

        friendsLeaderBoardListView.getItems().clear();
        this.state = getInstance();

        friendsLeaderBoardListView.getItems().addAll(ControllerUtils.viewAllUsers());
    }

}
