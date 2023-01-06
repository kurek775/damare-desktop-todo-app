package com.damare.data;

import java.sql.*;

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


    public void insertUserRow(Connection conn,String name, String email, String password) {
        Statement statement;
        try {
            String query = String.format("insert into users(name,email,password) values('%s','%s','%s');",name, email, password);
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

    public void readData(Connection conn, String table_name) {
        Statement statement;
        ResultSet rs = null;
        try {
            String query = String.format("select * from %s", table_name);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            /*  JSONObject jo = new JSONObject();*/
            while (rs.next()) {
              /*  jo.put("id", rs.getString("id"));
                jo.put("name", rs.getString("name"));
                jo.put("email", rs.getString("email"));
                jo.put("password", rs.getString("password"));*/

                System.out.print(rs.getString("id") + " ");
                System.out.print(rs.getString("name") + " ");
                System.out.println(rs.getString("email") + " ");
                System.out.println(rs.getString("password") + " ");
            }
            /* System.out.println(jo);*/

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

    public void updateName(Connection conn, String table_name, String old_name, String new_name) {
        Statement statement;
        try {
            String query = String.format("update %s set name='%s' where name='%s'", table_name, new_name, old_name);
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
