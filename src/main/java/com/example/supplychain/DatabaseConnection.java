package com.example.supplychain;
import java.sql.*;


public class DatabaseConnection {

   static final String url="jdbc:mysql://localhost:3306/supply_chain";
   static final String user="root";
    static final String pass="Jogi@7711";

   public Statement getStatement() {
       Statement stmt = null;
       Connection conn;
       try {
           conn = DriverManager.getConnection(url, user, pass);
           stmt = conn.createStatement();
       } catch (Exception e) {
           e.printStackTrace();
       }
       return stmt;
   }

   public ResultSet getResultant(String query) throws SQLException
   {
       Statement stmt=getStatement();
       return stmt.executeQuery(query);
   }


}
