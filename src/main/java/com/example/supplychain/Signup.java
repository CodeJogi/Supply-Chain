package com.example.supplychain;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;

public class Signup {
    public static String name,email,pass,cpass,mob,pincode;


    public static void feedData(String name,String email,String pass,String pincode,String mob)
    {

        try {


            DatabaseConnection conn = new DatabaseConnection();
            String gpassword = Login.encryptedPassword(pass);
            String query = "insert into customer(fullname,email,password,pincode,contact) values (name,email,gpassword,pincode,contact)";

            ResultSet rs = conn.getResultant(query);
            System.out.println("Customer data added");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    }



