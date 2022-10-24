package com.example.supplychain;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.sql.SQLException;

public class Product {

    public SimpleIntegerProperty id,quantity;
    public SimpleDoubleProperty price;
    public SimpleStringProperty name;

    public Product(int id,String name,double price,int quantity)
    {
        this.id=new SimpleIntegerProperty(id);
        this.name=new SimpleStringProperty(name);
        this.price=new SimpleDoubleProperty(price);

        this.quantity=new SimpleIntegerProperty(quantity);

    }

    public int getId()
    {
        return id.get();
    }
    public int getQuantity()
    {
        return quantity.get();
    }
    public String getName()
    {
        return name.get();
    }
    public Double getPrice()
    {
        return price.get();
    }


    public static ObservableList<Product> getProductList(String query)
    {
        DatabaseConnection dbconn=new DatabaseConnection();
        ObservableList<Product> data= FXCollections.observableArrayList();
        try
        {
            ResultSet rs= dbconn.getResultant(query);
            while(rs.next())
            {
                data.add(new Product(rs.getInt("id"),rs.getString("name"),rs.getDouble("price"),rs.getInt("quantity")));
            }
            rs.close();
        }

        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        return data;

    }


    public static ObservableList<Product> getAllProducts()
    {

         String query="select * from products";
         return getProductList(query);

    }

    public static ObservableList<Product> getProductsByName(String productName) {

        String query = String.format("select * from products where name like '%%%s%%'",productName.toLowerCase());
              return getProductList(query);
    }
}
