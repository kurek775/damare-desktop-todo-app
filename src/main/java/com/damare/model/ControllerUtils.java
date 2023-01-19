package com.damare.model;

import com.damare.data.DbFunctions;
import com.damare.main.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Popup;
import javafx.stage.Stage;
import org.json.JSONObject;
import javafx.scene.Node;

import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class ControllerUtils {


    public static User findUser(Integer id) {
        DbFunctions db = new DbFunctions();
        Connection conn = db.connectDb("devel", "postgre", "Damare123");
        User current = (db.searchUsersById(conn, id));


        return current;
    }


    public static void addUser(User user) {
        DbFunctions db = new DbFunctions();
        Connection conn = db.connectDb("devel", "postgre", "Damare123");
        db.insertUserRow(conn, user);

    }

    public static void addRequest(FriendRequest req) {
        DbFunctions db = new DbFunctions();
        Connection conn = db.connectDb("devel", "postgre", "Damare123");
        db.insertRequestRow(conn, req);

    }

    public static void updateUser(User user) {
        DbFunctions db = new DbFunctions();
        Connection conn = db.connectDb("devel", "postgre", "Damare123");
        db.updateUserRow(conn, user);

    }

    public static void updateFriendRequest(FriendRequest req) {
        DbFunctions db = new DbFunctions();
        Connection conn = db.connectDb("devel", "postgre", "Damare123");
        db.updateFriendRequestRow(conn, req);

    }

    public static void addTask(Task task) {
        DbFunctions db = new DbFunctions();
        Connection conn = db.connectDb("devel", "postgre", "Damare123");
        db.insertTaskRow(conn, task);
    }

    public static void addCategory(Category category) {
        DbFunctions db = new DbFunctions();
        Connection conn = db.connectDb("devel", "postgre", "Damare123");
        db.insertCategoryRow(conn, category);
    }

    public static void updateTask(Task task) {
        DbFunctions db = new DbFunctions();
        Connection conn = db.connectDb("devel", "postgre", "Damare123");
        db.updateTaskRow(conn, task);
    }

    public static void finishTask(Integer id) {
        DbFunctions db = new DbFunctions();
        Connection conn = db.connectDb("devel", "postgre", "Damare123");
        db.finishTask(conn, id);
    }

    public static List<Task> viewTasks(Integer id) {
        DbFunctions db = new DbFunctions();
        Connection conn = db.connectDb("devel", "postgre", "Damare123");

        return db.readAllTasks(conn, id);

    }

    public static List<User> viewAllUsers(Integer id) {
        DbFunctions db = new DbFunctions();
        Connection conn = db.connectDb("devel", "postgre", "Damare123");

        return db.readAllUsers(conn,id);

    }

    public static List<User> viewAllFriends(Integer id) {
        DbFunctions db = new DbFunctions();
        Connection conn = db.connectDb("devel", "postgre", "Damare123");

        return db.readAllFriends(conn, id);

    }

    public static List<LeaderBoard> viewGlobalLeaderBoard() {
        DbFunctions db = new DbFunctions();
        Connection conn = db.connectDb("devel", "postgre", "Damare123");

        return db.readGlobalLeaderBoard(conn);
    }


    public static List<LeaderBoard> viewFriendsLeaderBoard(Integer id) {
        DbFunctions db = new DbFunctions();
        Connection conn = db.connectDb("devel", "postgre", "Damare123");

        return db.readFriendsLeaderBoard(conn, id);
    }

    public static List<FriendRequest> viewRequesters(Integer id) {
        DbFunctions db = new DbFunctions();
        Connection conn = db.connectDb("devel", "postgre", "Damare123");
        return db.readRequesters(conn, id);

    }

    public static void declineRequest(Integer id) {
        DbFunctions db = new DbFunctions();
        Connection conn = db.connectDb("devel", "postgre", "Damare123");
        db.deleteRowByAnything(conn, "devel_friendship","friendship_id",id);

    }

    public static List<Category> viewCategories(Integer id) {
        DbFunctions db = new DbFunctions();
        Connection conn = db.connectDb("devel", "postgre", "Damare123");
        return db.readAllCategories(conn, id);

    }

    public static void removeTask(Integer id) {
        DbFunctions db = new DbFunctions();
        Connection conn = db.connectDb("devel", "postgre", "Damare123");
        db.deleteRowByAnything(conn, "devel_task", "task_id", id);
    }

    public static void removeCategory(Integer id) {
        DbFunctions db = new DbFunctions();
        Connection conn = db.connectDb("devel", "postgre", "Damare123");
        db.deleteRowByAnything(conn, "devel_category", "category_id", id);
    }

    public static User loginUser(String lname, String password, Stage stage) {

        DbFunctions db = new DbFunctions();
        Connection conn = db.connectDb("devel", "postgre", "Damare123");

        User usr = (db.searchByNameOrMail(conn, lname));
        if (usr.getId() == null) {

            showPopup(stage, "Uživatel nebyl nalezen");
        } else {
            if (checkPassword(password, usr.getPassword())) {
                return usr;

            } else {
                showPopup(stage, "Špatně zadané heslo");
            }

        }
        return usr;
    }

    public static boolean checkPassword(String password, String passwordDb) {
        if (password.equals(passwordDb)) {
            return true;
        } else {
            return false;
        }
    }

    public static void showPopup(Stage stage, String content) {
        Label label = new Label(content);
        Popup popup = new Popup();
        Button button = new Button("X");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                popup.hide();
            }
        });
        popup.getContent().add(label);
        popup.getContent().add(button);
        label.setMinWidth(180);
        label.setStyle(" -fx-background-color: red;");
        label.setMinHeight(180);
        popup.show(stage);
    }

    public static boolean validateUsername(String userName, Stage stage) {
        DbFunctions db = new DbFunctions();
        Connection conn = db.connectDb("devel", "postgre", "Damare123");
        if (db.searchByNameOrMail(conn, userName).getName() == null) {
            System.out.println("Toto jmeno je volne");
            return false;
        } else {
            showPopup(stage, "Toto jmeno jiz nekdo vyuziva");
            return true;
        }
    }

    public static boolean validateEmail(String email, Stage stage) {
        DbFunctions db = new DbFunctions();
        Connection conn = db.connectDb("devel", "postgre", "Damare123");
        if (db.searchByNameOrMail(conn, email).getEmail() == null) {
            System.out.println("Tento email je volny");
            return false;
        } else {
            showPopup(stage, "Tento email jiz nekdo vyuziva");
            return true;
        }
    }


    public static String createGoogleLink(Date date, String name, Integer duration) {
        Integer starthour = 10;
        Integer endhour = starthour + duration;
        String dat = date.toString().replace("-", "");
        String formateddate = dat + "T" + starthour.toString() + "0000" + "/" + dat + "T" + endhour.toString() + "0000";
        System.out.println(formateddate);
        String Url = String.format("https://calendar.google.com/calendar/u/0/r/eventedit?text=%s&dates=%s", name, formateddate);
        return Url;
    }


    public static boolean validatePassword(String password, Stage stage) {
        boolean hasNum = false;
        boolean hasCap = false;
        boolean hasLow = false;
        char c;

        if (password.length() < 8) {

            showPopup(stage, "Heslo musí mít minimálně 8 znaků");
            return true;
        } else {


            for (int i = 0; i < password.length(); i++) {
                c = password.charAt(i);
                if (Character.isDigit(c)) {
                    hasNum = true;

                } else if (Character.isUpperCase(c)) {
                    hasCap = true;
                } else if (Character.isLowerCase(c)) {
                    hasLow = true;
                }
                if (hasCap && hasLow && hasNum) {
                    System.out.println("Heslo je v pořádku");
                    return false;
                }
            }
        }

        showPopup(stage, "Heslo musí obsahovat velké písmeno, malé písmeno a číslo");
        return true;
    }

    @FXML
    public static void toLogin(ActionEvent event) {
        try {
            // Load the FXML file for the new controller
            FXMLLoader loader = new FXMLLoader(LoginController.class.getResource("login.fxml"));
            Parent root = loader.load();
            LoginController loginController = loader.getController();

            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene on the stage
            stage.setScene(new Scene(root));
        } catch (IOException ex) {
            // Handle exception
        }
    }

    @FXML
    public static void toRegister(ActionEvent event) {
        try {
            // Load the FXML file for the new controller
            FXMLLoader loader = new FXMLLoader(RegisterController.class.getResource("register.fxml"));
            Parent root = loader.load();
            RegisterController registerController = loader.getController();

            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene on the stage
            stage.setScene(new Scene(root));
        } catch (IOException ex) {
            // Handle exception
        }
    }

    @FXML
    public static void toHome(ActionEvent event) {
        try {
            // Load the FXML file for the new controller
            FXMLLoader loader = new FXMLLoader(HomeController.class.getResource("home.fxml"));
            Parent root = loader.load();
            HomeController homeController = loader.getController();

            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene on the stage
            stage.setScene(new Scene(root));
        } catch (IOException ex) {
            // Handle exception
        }
    }

    @FXML
    public static void toAddTask(ActionEvent event) {
        try {
            // Load the FXML file for the new controller
            FXMLLoader loader = new FXMLLoader(AddTaskController.class.getResource("addTask.fxml"));
            Parent root = loader.load();
            AddTaskController addTaskController = loader.getController();

            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene on the stage
            stage.setScene(new Scene(root));
        } catch (IOException ex) {
            // Handle exception
        }
    }

    @FXML
    public static void toUpdateTask(ActionEvent event) {
        try {
            // Load the FXML file for the new controller
            FXMLLoader loader = new FXMLLoader(UpdateTaskController.class.getResource("updateTask.fxml"));
            Parent root = loader.load();
            UpdateTaskController updateTaskController = loader.getController();

            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene on the stage
            stage.setScene(new Scene(root));
        } catch (IOException ex) {
            // Handle exception
        }
    }

    @FXML
    public static void toUpdateUser(ActionEvent event) {
        try {
            // Load the FXML file for the new controller
            FXMLLoader loader = new FXMLLoader(UpdateUserController.class.getResource("updateUser.fxml"));
            Parent root = loader.load();
            UpdateUserController updateUserController = loader.getController();

            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene on the stage
            stage.setScene(new Scene(root));
        } catch (IOException ex) {
            // Handle exception
        }
    }

    @FXML
    public static void toUsers(ActionEvent event) {
        try {
            // Load the FXML file for the new controller
            FXMLLoader loader = new FXMLLoader(UsersController.class.getResource("users.fxml"));
            Parent root = loader.load();
            UsersController userController = loader.getController();

            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene on the stage
            stage.setScene(new Scene(root));
        } catch (IOException ex) {
            // Handle exception
        }
    }

    @FXML
    public static void toAddCategory(ActionEvent event) {
        try {
            // Load the FXML file for the new controller
            FXMLLoader loader = new FXMLLoader(AddCategoryController.class.getResource("addCategory.fxml"));
            Parent root = loader.load();
            AddCategoryController addCategoryController = loader.getController();

            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene on the stage
            stage.setScene(new Scene(root));
        } catch (IOException ex) {
            // Handle exception
        }
    }

    @FXML
    public static void toLeaderBoard(ActionEvent event) {
        try {
            // Load the FXML file for the new controller
            FXMLLoader loader = new FXMLLoader(LeaderBoardController.class.getResource("leaderBoard.fxml"));
            Parent root = loader.load();
            LeaderBoardController leaderBoardController = loader.getController();

            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene on the stage
            stage.setScene(new Scene(root));
        } catch (IOException ex) {
            // Handle exception
        }
    }

}