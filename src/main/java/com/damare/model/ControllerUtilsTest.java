package com.damare.model;

import com.damare.data.DbFunctions;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.util.List;

import static org.testng.Assert.assertFalse;

class ControllerUtilsTest {



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
        int userToDeleteId = userToDelete.getId();
        //System.out.println(userToDeleteId);

        db = new DbFunctions();
        conn = db.connectDb("devel", "postgre", "Damare123");
        db.deleteRowByAnything(conn,"devel_user","user_id",userToDeleteId);
    }


    @Test
    void viewAllUsers() {
        DbFunctions db = new DbFunctions();
        Connection conn = db.connectDb("devel", "postgre", "Damare123");

        List<User> userList= db.readAllUsers(conn,30);
        assertFalse(userList.isEmpty());
    }
}