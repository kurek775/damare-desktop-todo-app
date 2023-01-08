package com.damare.model;
import com.damare.data.dbFunctions;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Popup;
import javafx.stage.Stage;
import org.json.JSONObject;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


public class controllerUtils {


    public static User currentUser(Integer id) {
        dbFunctions db = new dbFunctions();
        List<Integer> friendlist = new ArrayList<>();
        friendlist.add(2);
        Connection conn = db.connectDb("devel", "postgre", "Damare123");
        JSONObject usr = (db.searchUsersById(conn, id));
        User current = new User(usr.getInt("id"), usr.getString("name"), usr.getString("email"), usr.getString("password"),
                friendlist);

        return current;
    }


    public static void addUser(User user) {
        dbFunctions db = new dbFunctions();
        Connection conn = db.connectDb("devel", "postgre", "Damare123");
        db.insertUserRow(conn,user);

    }

    public static void updateUser(User user) {
        dbFunctions db = new dbFunctions();
        Connection conn = db.connectDb("devel", "postgre", "Damare123");
        db.updateUserRow(conn,user);

    }

    public static void addTask(Task task){
        dbFunctions db = new dbFunctions();
        Connection conn = db.connectDb("devel", "postgre", "Damare123");
        db.insertTaskRow(conn,task);
    }

    public static void addCategory(Category category){
        dbFunctions db = new dbFunctions();
        Connection conn = db.connectDb("devel", "postgre", "Damare123");
        db.insertCategoryRow(conn,category);
    }
    public static void updateTask(Task task){
        dbFunctions db = new dbFunctions();
        Connection conn = db.connectDb("devel", "postgre", "Damare123");
        db.updateTaskRow(conn,task);
    }

    public static List<Task> viewTasks(Integer id){
        dbFunctions db = new dbFunctions();
        Connection conn = db.connectDb("devel", "postgre", "Damare123");

        return db.readAllTasks(conn,id);

    }

    public static List<Category> viewCategories(Integer id){
        dbFunctions db = new dbFunctions();
        Connection conn = db.connectDb("devel", "postgre", "Damare123");

        return db.readAllCategories(conn,id);

    }
    public static void removeTask(Integer id){
        dbFunctions db = new dbFunctions();
        Connection conn = db.connectDb("devel", "postgre", "Damare123");
        db.deleteRowByAnything(conn,"devel_task","task_id",id);
    }
    public static void removeCategory(Integer id){
        dbFunctions db = new dbFunctions();
        Connection conn = db.connectDb("devel", "postgre", "Damare123");
        db.deleteRowByAnything(conn,"devel_category","category_id",id);
    }

    public static Integer loginUser(String lname, String password, Stage stage) {
        dbFunctions db = new dbFunctions();
        Integer rt = null;
        Connection conn = db.connectDb("devel", "postgre", "Damare123");
        JSONObject usr = (db.searchByNameOrMail(conn, lname));
        if (usr.isEmpty()) {

            showPopup(stage, "Uživatel nebyl nalezen");
        } else {
            if (checkPassword(password, usr.getString("password"))) {
                rt = usr.getInt("id");

            } else {
                showPopup(stage, "Špatně zadané heslo");
            }

        }
        return rt;
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
}
