package com.damare.model;
import com.damare.data.DbFunctions;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Popup;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.sql.Connection;
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
        db.insertUserRow(conn,user);

    }

    public static void addRequest(FriendRequest req) {
        DbFunctions db = new DbFunctions();
        Connection conn = db.connectDb("devel", "postgre", "Damare123");
        db.insertRequestRow(conn,req);

    }

    public static void updateUser(User user) {
        DbFunctions db = new DbFunctions();
        Connection conn = db.connectDb("devel", "postgre", "Damare123");
        db.updateUserRow(conn,user);

    }

    public static void updateFriendRequest(FriendRequest req) {
        DbFunctions db = new DbFunctions();
        Connection conn = db.connectDb("devel", "postgre", "Damare123");
        db.updateFriendRequestRow(conn,req);

    }
    public static void addTask(Task task){
        DbFunctions db = new DbFunctions();
        Connection conn = db.connectDb("devel", "postgre", "Damare123");
        db.insertTaskRow(conn,task);
    }

    public static void addCategory(Category category){
        DbFunctions db = new DbFunctions();
        Connection conn = db.connectDb("devel", "postgre", "Damare123");
        db.insertCategoryRow(conn,category);
    }
    public static void updateTask(Task task){
        DbFunctions db = new DbFunctions();
        Connection conn = db.connectDb("devel", "postgre", "Damare123");
        db.updateTaskRow(conn,task);
    }

    public static List<Task> viewTasks(Integer id){
        DbFunctions db = new DbFunctions();
        Connection conn = db.connectDb("devel", "postgre", "Damare123");

        return db.readAllTasks(conn,id);

    }

    public static List<User> viewAllUsers(){
        DbFunctions db = new DbFunctions();
        Connection conn = db.connectDb("devel", "postgre", "Damare123");

        return db.readAllUsers(conn);

    }
    public static List<User> viewAllFriends(Integer id){
        DbFunctions db = new DbFunctions();
        Connection conn = db.connectDb("devel", "postgre", "Damare123");

        return db.readAllFriends(conn, id);

    }
    public static List<FriendRequest> viewRequesters(Integer id){
        DbFunctions db = new DbFunctions();
        Connection conn = db.connectDb("devel", "postgre", "Damare123");

       return db.readRequesters(conn,id);

    }
    public static List<Category> viewCategories(Integer id){
        DbFunctions db = new DbFunctions();
        Connection conn = db.connectDb("devel", "postgre", "Damare123");

        return db.readAllCategories(conn,id);

    }
    public static void removeTask(Integer id){
        DbFunctions db = new DbFunctions();
        Connection conn = db.connectDb("devel", "postgre", "Damare123");
        db.deleteRowByAnything(conn,"devel_task","task_id",id);
    }
    public static void removeCategory(Integer id){
        DbFunctions db = new DbFunctions();
        Connection conn = db.connectDb("devel", "postgre", "Damare123");
        db.deleteRowByAnything(conn,"devel_category","category_id",id);
    }

    public static Integer loginUser(String lname, String password, Stage stage) {
        DbFunctions db = new DbFunctions();
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

    public static boolean validateUsername(String userName, Stage stage){
        DbFunctions db = new DbFunctions();
        Connection conn = db.connectDb("devel", "postgre", "Damare123");

        if (db.searchByNameOrMail(conn, userName).isEmpty()){
            System.out.println("Toto jmeno je volne");
            return false;
        }
        else {
            showPopup(stage,"Toto jmeno jiz nekdo vyuziva");
            return true;
        }
    }

    public static boolean validateEmail(String email, Stage stage){
        DbFunctions db = new DbFunctions();
        Connection conn = db.connectDb("devel", "postgre", "Damare123");

        if (db.searchByNameOrMail(conn, email).isEmpty()){
            System.out.println("Tento email je volny");
            return false;
        }
        else {
            showPopup(stage,"Tento email jiz nekdo vyuziva");
            return true;
        }
    }

    public static boolean validatePassword(String password, Stage stage){
        boolean hasNum = false;
        boolean hasCap = false;
        boolean hasLow = false;
        char c;

        if (password.length()<8){

            showPopup(stage,"Heslo musí mít minimálně 8 znaků");
            return true;
        }
        else {


            for (int i = 0; i < password.length(); i++){
                c = password.charAt(i);
                if (Character.isDigit(c)){
                    hasNum = true;

                }
                else if (Character.isUpperCase(c)) {
                    hasCap = true;
                }
                else if (Character.isLowerCase(c)) {
                    hasLow = true;
                }
                if (hasCap && hasLow && hasNum){
                    System.out.println("Heslo je v pořádku");
                    return false;
                }
            }
        }

        showPopup(stage,"Heslo musí obsahovat velké písmeno, malé písmeno a číslo");
        return true;
    }
}
