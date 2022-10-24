package com.example.supplychain;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;

public class Login {

    public static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

   static String encryptedPassword(String passText) throws NoSuchAlgorithmException {

        try {
            BigInteger number = new BigInteger(1, getSHA(passText));
            StringBuilder hex = new StringBuilder(number.toString(16));
              return hex.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
      return "";
    }

    public static boolean loginUser(String email,String pass) throws NoSuchAlgorithmException {
        try {


            DatabaseConnection conn = new DatabaseConnection();
            String password = Login.encryptedPassword(pass);
            String query = String.format("select * from customer where email = '%s' and password = '%s' ", email, password);

            ResultSet rs = conn.getResultant(query);
            if (rs.next()) {
                return true;
            }


            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String demo="ram@123";
        System.out.println(demo);
        System.out.printf(Login.encryptedPassword(demo));
    }
}
