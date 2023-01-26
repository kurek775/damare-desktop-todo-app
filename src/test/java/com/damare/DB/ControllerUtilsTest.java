package com.damare.DB;

import com.damare.data.DbFunctions;
import com.damare.model.Task;
import com.damare.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertFalse;

class ControllerUtilsTest {

    Connection testConn;

    @BeforeEach
    public void setUp() {
        DbFunctions db = new DbFunctions();
        Connection conn = db.connectDb("devel", "postgre", "Damare123");
        testConn = db.getConn(conn);
    }

    @AfterEach
    public void closeConnection() {
        try {
            testConn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void dbConnect(){
        assertFalse(testConn.equals(null));
    }

    @Test
    void addAndDeleteUser() {
        DbFunctions db = new DbFunctions();
        Connection conn = db.connectDb("devel", "postgre", "Damare123");
        User user = new User(null, "testovaciUserJunit", "testovaci@junit.com","randomPasswd1");
        db.insertUserRow(conn, user);

        db = new DbFunctions();
        conn = db.connectDb("devel", "postgre", "Damare123");
        User userToDelete = db.searchByNameOrMail(conn,"testovaciUserJunit");
        //System.out.println(userToDelete.getId());
        //System.out.println(userToDelete.getName());
        //System.out.println(userToDelete.getEmail());
        //System.out.println(userToDelete.getPassword());
        int userToDeleteId = ((User) userToDelete).getId();
        //System.out.println(userToDeleteId);

        db = new DbFunctions();
        conn = db.connectDb("devel", "postgre", "Damare123");
        db.deleteRowByAnything(conn,"devel_user","user_id",userToDeleteId);
    }

    @Test
    void addTask() {
        DbFunctions db = new DbFunctions();
        Connection conn = db.connectDb("devel", "postgre", "Damare123");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Date now = new Date();
        //System.out.println(now);

        Task task = new Task(null,0,30,20,6,"Testovaci","Testovna","jen test",now,false);
        //System.out.println(task);

        db.insertTaskRow(conn, task);

        db = new DbFunctions();
        conn = db.connectDb("devel", "postgre", "Damare123");

        List<Task> taskList = db.readAllTasks(conn,30);
        assertFalse(taskList.isEmpty());
    }

    @Test
    void viewAllUsers() {
        DbFunctions db = new DbFunctions();
        Connection conn = db.connectDb("devel", "postgre", "Damare123");

        List<User> userList= db.readAllUsers(conn,30);
        assertFalse(userList.isEmpty());
    }
}