package com.damare.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.damare.model.Category;
import com.damare.model.FriendRequest;
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
            String query = String.format("insert into devel_user(username,user_email,user_passwd) values('%s','%s','%s');", user.getName(), user.getEmail(), user.getPassword());
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

    public void insertCategoryRow(Connection conn, Category category) {
        Statement statement;
        try {
            String query = String.format("insert into devel_category(category_name,category_desc, category_user_id) values('%s','%s',%s);", category.getName(), category.getDesc(), category.getUserId());
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

    public void insertRequestRow(Connection conn, FriendRequest req) {
        Statement statement;
        try {
            String query = String.format("insert into devel_friendship(user_id1_requester,user_id2_requested, friendship_status_code, friendship_status_name) values(%s,%s,%s,'%s');",
                    req.getRequesterId(), req.getRequestedId(), req.getStatusCode(), req.getStatusName());
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

    public void insertTaskRow(Connection conn, Task task) {
        Statement statement;
        try {
            String query = String.format("insert into devel_task(task_category_id, task_user_id, task_importance, task_duration, task_name, task_place, task_desc, task_date , task_finished ) values('%s','%s','%s','%s','%s','%s','%s','%s','%s');"
                    , task.getCatId(), task.getUserId(), task.getImportance(), task.getDuration(), task.getName(), task.getPlace(), task.getDescription(), task.getDate(), task.getStatus());
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

    public void updateTaskRow(Connection conn, Task task) {
        Statement statement;
        try {
            String query = String.format("update devel_task set task_category_id='%s', task_user_id='%s' , task_importance='%s' , task_duration='%s' , task_name='%s' , task_place='%s' , task_desc='%s' , task_date='%s' , task_finished='%s' where task_id='%s';"
                    , task.getCatId(), task.getUserId(), task.getImportance(), task.getDuration(), task.getName(), task.getPlace(), task.getDescription(), task.getDate(), task.getStatus(), task.getId());
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

    public void updateUserRow(Connection conn, User user) {
        Statement statement;
        try {
            String query = String.format("update devel_user set username='%s',user_email='%s',user_passwd='%s' where user_id='%s'",
                    user.getName(), user.getEmail(), user.getPassword(), user.getId());
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

    public void updateFriendRequestRow(Connection conn, FriendRequest req) {
        Statement statement;
        try {
            String query = String.format("update devel_friendship set user_id1_requester= %s,user_id2_requested= %s, friendship_status_code= %s, friendship_status_name= '%s'  where friendship_id=%s",
                   req.getRequesterId(),req.getRequestedId(), req.getStatusCode(),req.getStatusName(), req.getId());
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
    /* TODO -> vracet rovnou celého USERA*/
    public User searchUsersById(Connection conn, int id) {
        Statement statement;
        User usr = new User(null,null,null,null);
        ResultSet rs = null;
        try {
            String query = String.format("select * from devel_user where user_id='%s'", id);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                usr =new User(rs.getInt("user_id"),rs.getString("username"),rs.getString("user_email"),rs.getString("user_passwd"));



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

    /* TODO -> vracet rovnou celého USERA*/
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

    public List<Task> readAllTasks(Connection conn, Integer id) {
        Statement statement;
        List<Task> list = new ArrayList<>();
        ResultSet rs = null;
        try {

            String query = String.format("select * from devel_task where task_user_id= %s", id);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            Integer i = 0;
            while (rs.next()) {

                Task tsk = new Task(rs.getInt("task_id"), rs.getInt("task_category_id"),
                        rs.getInt("task_user_id"), rs.getInt("task_importance"),
                        rs.getInt("task_duration"), rs.getString("task_name"),
                        rs.getString("task_place"), rs.getString("task_desc"),
                        rs.getDate("task_date"), rs.getBoolean("task_finished")

                );
                list.add(i, tsk);
                i++;
            }
            return list;
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
        return list;
    }

    public List<Category> readAllCategories(Connection conn, Integer id) {
        Statement statement;
        List<Category> list = new ArrayList<>();
        ResultSet rs = null;
        try {

            String query = String.format("select * from devel_category where category_user_id= %s", id);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            Integer i = 0;
            while (rs.next()) {
                Category ctg = new Category(rs.getInt("category_id"), rs.getString("category_name"),
                        rs.getString("category_desc"), rs.getInt("category_user_id"));

                list.add(i, ctg);
                i++;
            }
            return list;
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
        return list;
    }

    public List<User> readAllUsers(Connection conn) {
        Statement statement;
        List<User> list = new ArrayList<>();
        ResultSet rs = null;
        try {

            String query = String.format("select * from devel_user");
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            Integer i = 0;
            while (rs.next()) {
                User user = new User(rs.getInt("user_id"), rs.getString("username")
                        , rs.getString("user_email"), rs.getString("user_passwd"));


                list.add(i, user);
                i++;
            }
            return list;
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
        return list;
    }
    public List<User> readAllFriends(Connection conn, Integer id) {
        Statement statement;
        List<User> list = new ArrayList<>();
        ResultSet rs = null;
        try {

            String query = String.format("select * from devel_user inner join devel_friendship  on devel_user.user_id=devel_friendship.user_id1_requester where devel_friendship.user_id2_requested=%s and devel_friendship.friendship_status_code=1", id);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            Integer i = 0;
            while (rs.next()) {
                User user = new User(rs.getInt("user_id"), rs.getString("username")
                        , rs.getString("user_email"), rs.getString("user_passwd"));


                list.add(i, user);
                i++;
            }
            return list;
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
        return list;
    }
    public List<FriendRequest> readRequesters(Connection conn, Integer id) {
        Statement statement;
        List<FriendRequest> list = new ArrayList<>();
        ResultSet rs = null;
        try {

            String query = String.format("select * from devel_friendship inner join devel_user  on devel_user.user_id=devel_friendship.user_id1_requester where devel_friendship.user_id2_requested=%s and devel_friendship.friendship_status_code=0", id);

            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            Integer i = 0;
            while (rs.next()) {

                FriendRequest req = new FriendRequest(rs.getInt("friendship_id"), rs.getInt("user_id1_requester"),
                        rs.getInt("user_id2_requested"), rs.getInt("friendship_status_code"), rs.getString("friendship_status_name"));
                list.add(i, req);
                i++;
            }
            return list;
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
        return list;
    }

    /* ALL  */
    public void deleteRowByAnything(Connection conn, String table_name, String cellname, int id) {
        Statement statement;
        try {
            String query = String.format("delete from %s where %s= %s", table_name, cellname, id);
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
