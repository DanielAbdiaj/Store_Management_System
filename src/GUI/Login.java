package GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Module.DataBase;
import Module.Manager;
import Module.Cashier;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Login {

    static Stage window;
    static Scene scene;

    static TextField usernameField;
    static PasswordField passwordField;
    static Label incorrectMessage;

    static Button loginButton;
    static Button exitButton;

    public static void showScene(Stage window)throws FileNotFoundException {
        Login.window = window;

        Image image = new Image(new FileInputStream("resources/Images/logo.png"));
        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(300);
        imageView.setFitHeight(300);
        imageView.setPreserveRatio(true);

        usernameField = new TextField();
        usernameField.setPromptText("username");
        usernameField.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000; -fx-prompt-text-fill: #888888;-fx-border-color:#A9A9A9");

        passwordField = new PasswordField();
        passwordField.setPromptText("password");
        passwordField.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000; -fx-prompt-text-fill: #888888;-fx-border-color:#A9A9A9");

        incorrectMessage = new Label("");
        incorrectMessage.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        incorrectMessage.setAlignment(Pos.CENTER);
        incorrectMessage.setStyle("-fx-text-fill: #ff5050;");

        loginButton = new Button("Login");
        loginButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        loginButton.setStyle("-fx-background-color: #008000; -fx-text-fill: #eeeeee;-fx-font-weight: bold;");

        loginButton.setOnAction(actionEvent -> {
            try {
                login(usernameField.getText(), passwordField.getText()); //Checks weather it is admin,cashier or manager
            }
            catch (FileNotFoundException e) { e.printStackTrace(); }
        });



        exitButton = new Button("Exit");
        exitButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        exitButton.setStyle("-fx-background-color: #bb2020; -fx-text-fill: #eeeeee;-fx-font-weight: bold;");

        exitButton.setOnAction(actionEvent -> {
            DataBase.save();//Saves the data to the DataBase
            window.close();
        });

        VBox inputVBox = new VBox(10);
        inputVBox.setPadding(new Insets(10, 0, 0, 0));
        inputVBox.getChildren().addAll(usernameField, passwordField, incorrectMessage);

        HBox buttonsHBox = new HBox(10);
        buttonsHBox.getChildren().addAll(loginButton, exitButton);
        buttonsHBox.setSpacing(15);
        buttonsHBox.setPadding(new Insets(0, 0, 0 ,90));

        VBox imageVBox = new VBox(10);
        imageVBox.setPadding(new Insets(20, 0, 20, 0));
        imageVBox.getChildren().addAll(imageView);

        VBox layout = new VBox(0);
        layout.setPadding(new Insets(30, 100, 10, 100));
        layout.getChildren().addAll(imageVBox,inputVBox , buttonsHBox);
        layout.setStyle("-fx-background-color: #ffffff;");

        scene = new Scene(layout, 500, 350);
        window.setResizable(false);
        Login.window.setScene(scene);

    }


    static void login(String username, String password) throws FileNotFoundException
    {
        if(username.equals("daniel_"))
            if(password.equals("abdiaj1"))
            {
                new AdminScene().showScene(window);//Opens Admin scene
                return;
            }

       for(Cashier c : DataBase.getCashiers())
            if(username.equals(c.getUsername()))
                if(password.equals(c.getPassword()))
                {
                    new CashierScene().showScene(c, window);//Opens Cashier Scene
                    return;
                }

        for(Manager e : DataBase.getManager())
            if(username.equals(e.getUsername()))
                if(password.equals(e.getPassword()))
                {
                    new ManagerScene().showScene(e, window);//Opens Manager Scene
                    return;
                }

        incorrectMessage.setText("Incorrect username or password.");
    }
}



