package com.example.supplychain;

import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class Signup {
    public static String name,email,pass,cpass,mob,pincode;


    public static void feedData(String name,String email,String pass,String pincode,String mob) throws NoSuchAlgorithmException {
        DatabaseConnection db = new DatabaseConnection();
        String hashpass=Login.encryptedPassword(pass);

        try {

            Connection conn= DriverManager.getConnection(db.url,db.user,db.pass);
            String gpassword = Login.encryptedPassword(pass);
            String query = "insert into customer(fullname,email,password,pincode,contact) values(?,?,?,?,?)";

          //  ResultSet rs = conn.getResultant(query);
            PreparedStatement ps=conn.prepareStatement(query);
            ps.setString(1,name);
            ps.setString(2,email);
            ps.setString(3,hashpass);
            ps.setString(4,pincode);
            ps.setString(5,mob);
            ps.execute();


            System.out.println("Customer data added");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    }



