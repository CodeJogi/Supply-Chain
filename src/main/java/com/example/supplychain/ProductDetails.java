package com.example.supplychain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import java.sql.*;

public class ProductDetails {

     TableView<Product> productTable;
    public Pane getProductByName(String s)
    {
        TableView<Product> tb=new TableView<>();

        TableColumn idcol=new TableColumn("ID");
        idcol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn namecol=new TableColumn("NAME");
        namecol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn pricecol=new TableColumn("PRICE");
        pricecol.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn quantitycol=new TableColumn("QUANTITY");
        quantitycol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        //ObservableList<Product> data= FXCollections.observableArrayList(new Product(2,"abc",9856.00,2),
        //new Product(1,"jkl",8000.00,3),new Product(3,"xyz",5800.50,5));
        // manually adding data

        ObservableList<Product>  datasql=Product.getProductsByName(s);
        // List taking data from sql

        tb.setItems(datasql);
        tb.getColumns().addAll(idcol,namecol,pricecol,quantitycol);
        tb.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
     //   tb.setPrefSize(SupplyChain.width,SupplyChain.height);
        tb.setPrefSize(SupplyChain.width-100,SupplyChain.height);

        productTable=tb;
        Pane Vpane=new Pane();
        Vpane.getChildren().add(tb);
        Vpane.setTranslateX(50);
       // Vpane.setTranslateY(50);
        return Vpane;

    }

    public void getSelectedRows()
    {
        TableView <Product> table=productTable;
        if (productTable==null)
        {
            System.out.println("Table object not Found");
            return;
        }
        if(productTable.getSelectionModel().getSelectedIndex()!=-1)
        {
            Product selectedItems=productTable.getSelectionModel().getSelectedItem();
            System.out.println("Found");
            System.out.println(selectedItems.getId()+selectedItems.getName()+selectedItems.getPrice()+selectedItems.getQuantity());

        }
        else {
            System.out.println("Nothind Selected");
        }

    }

    public Pane getAllProducts()
    {
        TableView<Product> tb=new TableView<>();

        TableColumn idcol=new TableColumn("ID");
        idcol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn namecol=new TableColumn("NAME");
        namecol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn pricecol=new TableColumn("PRICE");
        pricecol.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn quantitycol=new TableColumn("QUANTITY");
        quantitycol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        //ObservableList<Product> data= FXCollections.observableArrayList(new Product(2,"abc",9856.00,2),
                //new Product(1,"jkl",8000.00,3),new Product(3,"xyz",5800.50,5));
        // manually adding data

        ObservableList<Product>  datasql=Product.getAllProducts();
        // List taking data from sql

        tb.setItems(datasql);
        tb.getColumns().addAll(idcol,namecol,pricecol,quantitycol);
        tb.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tb.setPrefSize(SupplyChain.width-100,SupplyChain.height);
        productTable=tb;

        Pane Vpane=new Pane();
        Vpane.getChildren().add(tb);
        Vpane.setTranslateX(50);
      //  Vpane.setTranslateY(50);
        return Vpane;

    }
}
