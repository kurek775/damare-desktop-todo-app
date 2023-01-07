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
            String query = String.format("insert into devel_user(username,user_email,user_passwd) values('%s','%s','%s');",user.getName(), user.getEmail(), user.getPassword());
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
            String query = String.format("insert into devel_task(task_category_id, task_user_id, task_importance, task_duration, task_name, task_place, task_desc, task_date , task_finished ) values('%s','%s','%s','%s','%s','%s','%s','%s','%s');"
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
            String query = String.format("update devel_task set task_category_id='%s', task_user_id='%s' , task_importance='%s' , task_duration='%s' , task_name='%s' , task_place='%s' , task_desc='%s' , task_date='%s' , task_finished='%s' where task_id='%s';"
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
            String query = String.format("update devel_user set username='%s',user_email='%s',user_passwd='%s' where user_id='%s'",
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

    public JSONObject searchUsersById(Connection conn,int id) {
        Statement statement;
        JSONObject usr = new JSONObject();
        ResultSet rs = null;
        try {
            String query = String.format("select * from devel_user where user_id='%s'", id);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                usr.put("id", rs.getString("user_id"));
                usr.put("name", rs.getString("username"));
                usr.put("email", rs.getString("user_email"));
                usr.put("password", rs.getString("user_passwd"));

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

    public JSONObject searchByNameOrMail(Connection conn, String name) {
        Statement statement;
        JSONObject usr = new JSONObject();
        ResultSet rs = null;
        try {

            String query = String.format("select * from devel_user where username = '%s' or user_email = '%s'", name, name);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                usr.put("id", rs.getString("user_id"));
                usr.put("name", rs.getString("username"));
                usr.put("email", rs.getString("user_email"));
                usr.put("password", rs.getString("user_passwd"));

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
