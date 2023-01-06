package com.damare.data;

import java.sql.*;

import com.damare.model.Task;
import com.damare.model.User;
import org.json.JSONObject;

public class dbFunctions {
    public Connection connectDb(String dbname, String user, String pass) {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://damare.c5s0nmo9q9mi.us-east-1.rds.amazonaws.com/" + dbname, user, pass);
            if (conn != null) {
                System.out.println("Connection Established");
            } else {
                System.out.println("Connection Failed");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return conn;
    }


    public void insertUserRow(Connection conn, User user) {
        Statement statement;
        try {
            String query = String.format("insert into users(name,email,password) values('%s','%s','%s');",user.getName(), user.getEmail(), user.getPassword());
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row Inserted");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void insertTaskRow(Connection conn, Task task){
        Statement statement;
        try {
            String query = String.format("insert into tasks(catid, userid, importance, duration, name, place, description, date , status ) values('%s','%s','%s','%s','%s','%s','%s','%s','%s');"
                    ,task.getCatId(),task.getUserId(),task.getImportance(),task.getDuration(),task.getName(),task.getPlace(),task.getDescription(), task.getDate(),task.getStatus());
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row Inserted");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void updateTaskRow(Connection conn, Task task){
        Statement statement;
        try {
            String query = String.format("update tasks set catid='%s', userid='%s' , importance='%s' , duration='%s' , name='%s' , place='%s' , description='%s' , date='%s' , status='%s' where id='%s';"
                    ,task.getCatId(),task.getUserId(),task.getImportance(),task.getDuration(),task.getName(),task.getPlace(),task.getDescription(), task.getDate(),task.getStatus(),task.getId());
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row Updated");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void updateUserRow(Connection conn,User user) {
        Statement statement;
        try {
            String query = String.format("update users set name='%s',email='%s',password='%s' where id='%s'",
                    user.getName(), user.getEmail(),user.getPassword(), user.getId());
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data Updated");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /*
    public void readAllTasks(Connection conn, Integer usrId){
        Statement statement;
        try {
            String query = String.format("select * from tasks where userid= %s",usrId);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row Inserted");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
*/

    public JSONObject searchById(Connection conn, String table_name, int id) {
        Statement statement;
        JSONObject usr = new JSONObject();
        ResultSet rs = null;
        try {
            String query = String.format("select * from %s where id= %s", table_name, id);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                usr.put("id", rs.getString("id"));
                usr.put("name", rs.getString("name"));
                usr.put("email", rs.getString("email"));
                usr.put("password", rs.getString("password"));

                return usr;

            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return usr;
    }

    public JSONObject searchByNameOrMail(Connection conn, String table_name, String name) {
        Statement statement;
        JSONObject usr = new JSONObject();
        ResultSet rs = null;
        try {

            String query = String.format("select * from %s where name = '%s' or email = '%s'", table_name, name, name);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                usr.put("id", rs.getString("id"));
                usr.put("name", rs.getString("name"));
                usr.put("email", rs.getString("email"));
                usr.put("password", rs.getString("password"));

                return usr;

            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return usr;
    }

/* ALL  */
    public void deleteRowById(Connection conn, String table_name, int id) {
        Statement statement;
        try {
            String query = String.format("delete from %s where id= %s", table_name, id);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data Deleted");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
