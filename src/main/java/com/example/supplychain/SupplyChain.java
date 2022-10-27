package com.example.supplychain;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.security.NoSuchAlgorithmException;
import java.sql.*;

import java.io.IOException;

public class SupplyChain extends Application {


   public static int width=700,height=650,topHeight=50;

    Pane body;
    Button search,login,signup;

    ProductDetails pd=new ProductDetails();
    Label showid;

    boolean customerIn=false;
    String customerEmail="";

    private GridPane signUp()
    {
        TextField name=new TextField();
        name.setPromptText("Enter name");

        TextField email=new TextField();
        email.setPromptText("Enter email");

        TextField pass=new TextField();
        pass.setPromptText("Enter password");

        TextField cpass=new TextField();
        cpass.setPromptText("Confirm password");

        TextField code=new TextField();
        code.setPromptText("Enter pincode");

        TextField contact=new TextField();
        contact.setPromptText("Enter mobile no.");

        Label nameLabel=new Label("Name:-");
        Label emailLabel=new Label("Email:-");
        Label passLabel=new Label("Password:-");
        Label cpassLabel=new Label("Confirm Password:-");
        Label codeLabel=new Label("Pincode:-");

        Label mobLabel=new Label("Contact No:-");

        Button localSign=new Button("Sign Up");
        localSign.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(pass.getText().equals(cpass.getText()))
                {
                    String gName= name.getText();
                    String gEmail= email.getText();
                    String gPass= pass.getText();
                    String gCode= code.getText();
                    String gContact= contact.getText();

                    try {
                        Signup.feedData(gName,gEmail,gPass,gCode,gContact);
                    } catch (NoSuchAlgorithmException e) {
                        throw new RuntimeException(e);
                    }
                }
                else {
                    pass.setText("Password Mismatch");
                    cpass.setText("Password Mismatch");
                }
            }
        });

        Button clear=new Button("clear");
        clear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                name.setText("");
                email.setText("");
                pass.setText(" ");
                cpass.setText("");
                code.setText("");
                contact.setText(" ");

            }
        });

        GridPane signpane=new GridPane();
        signpane.setMinSize(width,height-topHeight-50);
        signpane.setAlignment(Pos.CENTER);
        signpane.setHgap(10);
        signpane.setVgap(10);

        signpane.add(nameLabel,0,0);
        signpane.add(emailLabel,0,1);
        signpane.add(passLabel,0,2);
        signpane.add(cpassLabel,0,3);
        signpane.add(mobLabel,0,4);
        signpane.add(codeLabel,0,5);
        signpane.add(code,1,5);
        signpane.add(name,1,0);
        signpane.add(email,1,1);
        signpane.add(pass,1,2);
        signpane.add(cpass,1,3);
        signpane.add(contact,1,4);
        signpane.add(localSign,0,6);
        signpane.add(clear,1,6);


         return signpane;


    }
    private GridPane loginPage()
    {
        Label emailLabel=new Label("E-mail");
        Label passLabel=new Label("Password");

        TextField emailText=new TextField();
        emailText.setPromptText("Enter Email");

        PasswordField passField=new PasswordField();
        passField.setPromptText("Enter Password");

        Button localLogin=new Button("Login");
        localLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String email=emailText.getText();
                String pass=passField.getText();

                if(email.isBlank() || pass.isBlank())
                {
                       emailText.setPromptText("please enter valid email");
                       passField.setPromptText("please enter valid password");
                }
                else
                {
                    try {
                        if(Login.loginUser(email,pass))
                        {
                            customerIn=true;
                            customerEmail=email;
                            showid.setText(customerEmail);
                            body.getChildren().clear();
                            body.getChildren().add(pd.getAllProducts());
                        }
                    } catch (NoSuchAlgorithmException e) {
                        throw new RuntimeException(e);
                    }


                }

            }
        });

        Button cancel=new Button("Cancel");
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                emailText.setText("");
                passField.setText("");
            }
        });

        GridPane gp=new GridPane();
        gp.setMinSize(body.getWidth(),body.getHeight());
        gp.setAlignment(Pos.CENTER);
        gp.setVgap(10);
        gp.setHgap(10);

        gp.add(emailLabel,0,0);
        gp.add(passLabel,0,1);
        gp.add(emailText,1,0);
        gp.add(passField,1,1);
        gp.add(localLogin,0,2);
        gp.add(cancel,1,2);


        return gp;

    }

    private Pane footerbar()
    {
        Pane foot=new Pane();
        //foot.setTranslateX(width);
        foot.setTranslateY(height-topHeight+10);
        foot.setPrefSize(width,topHeight);
        foot.setBackground(Background.fill(Color.RED));

        Dialog<Product> dialog = new Dialog<>();
        //Setting the title
        dialog.setTitle("Congratulations!!");
        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        //Setting the content of the dialog
        dialog.setContentText("You have successfully booked your Buy1&Get1 offer product!!");
        //Adding buttons to the dialog pane
        dialog.getDialogPane().getButtonTypes().add(type);

        Button buy=new Button("Buy Now");
        buy.setTranslateX(300);
        buy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(customerIn) {
                    dialog.showAndWait();
                }
            }
        });

        foot.getChildren().addAll(buy);
        return foot;


    }
    private Pane headerPane()
    {
        Pane toproot =new Pane();
        toproot.setPrefSize(width,topHeight);


        toproot.setBackground(Background.fill(Color.RED));




        showid=new Label("Hey there!!!..");
        showid.setTranslateX(20);
        showid.setTranslateY(10);
        showid.setFont(Font.font(18));

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
                body.getChildren().add(loginPage());
            }
        });

        signup=new Button("Sign Up");
        signup.setTranslateX(width/2+220);
        signup.setTranslateY(topHeight-30);
        signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                body.getChildren().add(signUp());
            }
        });




        toproot.getChildren().addAll(showid,textfill,search,login,signup);
        return toproot;
    }
    private Pane createContent()
    {
        Pane root=new Pane();
        root.setPrefSize(700,700);

        Image img=new Image("C:\\Users\\jogi\\IdeaProjects\\SupplyChain\\src\\supply.jpg");
        ImageView view=new ImageView(img);
        view.setFitWidth(500);
        view.setFitHeight(400);
        view.setTranslateX(50);
        view.setTranslateY(50);

        Label bodytext=new Label();
        bodytext.setText("If already registered...click on LOGIN button or else click on Sign Up");
        bodytext.setTranslateY(height-topHeight-100);
        bodytext.setTranslateX(30);

        body=new Pane();
        body.setPrefSize(width,height-topHeight);
        body.setTranslateY(topHeight);
        body.setTranslateX(0);
       body.getChildren().addAll(view,bodytext);

        root.getChildren().addAll(headerPane(),body,footerbar());
        return root;

    }



    @Override

    public void start(Stage stage) throws IOException {
       // FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Buy1Get1 Store");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}