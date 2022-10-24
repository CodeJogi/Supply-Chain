package com.example.supplychain;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.sql.*;

import java.io.IOException;

public class SupplyChain extends Application {


   public static int width=700,height=650,topHeight=50;

    Pane body;
    Button search,login,signup;

    ProductDetails pd=new ProductDetails();
    private Pane headerPane()
    {
        Pane toproot =new Pane();
        toproot.setPrefSize(width,topHeight);


        toproot.setBackground(Background.fill(Color.RED));


        Image img=new Image("C:\\Users\\jogi\\IdeaProjects\\SupplyChain\\src\\supplychain.jpg");
        ImageView view=new ImageView(img);
        view.setFitWidth(60);
        view.setFitHeight(50);
        view.setTranslateX(20);

        TextField textfill=new TextField();
        textfill.setTranslateX(width/2-150);
        textfill.setTranslateY(topHeight-30);
        textfill.setPromptText("Search the product here");


        search=new Button("Search");
        search.setTranslateX(width/2+20);
        search.setTranslateY(topHeight-30);
        search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String word= textfill.getText();
                pd.getSelectedRows();
                body.getChildren().clear();
                body.getChildren().add(pd.getProductByName(word));
            }
        });


        login=new Button("Login");
        login.setTranslateX(width/2+150);
        login.setTranslateY(topHeight-30);
        login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
            }
        });

        signup=new Button("Sign Up");
        signup.setTranslateX(width/2+220);
        signup.setTranslateY(topHeight-30);
        signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String text= textfill.getText();
                body.getChildren().clear();
                body.getChildren().add(pd.getProductByName(text));
            }
        });



        toproot.getChildren().addAll(view,textfill,search,login,signup);
        return toproot;
    }
    private Pane createContent()
    {
        Pane root=new Pane();
        root.setPrefSize(700,650);



        body=new Pane();
       // body.setPrefSize(width,height);
        body.setTranslateX(10);
        body.getChildren().addAll(pd.getAllProducts());

        root.getChildren().addAll(headerPane(),body);
        return root;

    }



    @Override

    public void start(Stage stage) throws IOException {
       // FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Supplychain Store");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}