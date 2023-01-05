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
        Connection conn = db.connectDb("Test", "postgre", "Damare123");
        JSONObject usr = (db.searchById(conn, "users", id));
        User current = new User(usr.getInt("id"), usr.getString("name"), usr.getString("email"), usr.getString("password"),
                friendlist);

        return current;
    }


    public static void addUser(String uname, String email, String password) {
        dbFunctions db = new dbFunctions();
        Connection conn = db.connectDb("Test", "postgre", "Damare123");
        db.insertRow(conn, "users", uname, email, password);

    }


    public static Integer loginUser(String lname, String password, Stage stage) {
        dbFunctions db = new dbFunctions();
        Integer rt = null;
        Connection conn = db.connectDb("Test", "postgre", "Damare123");
        JSONObject usr = (db.searchByNameOrMail(conn, "users", lname));
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
