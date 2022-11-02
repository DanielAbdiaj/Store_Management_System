package GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import Module.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ModifyManagersScene{

    Stage window;
    Label titleLabel;
    Button cancelButton;
    Scene scene;
    VBox layout;

    Button chUsernameButton;
    Button chPasswordButton;
    Button chPhoneNrButton;
    Button chEmailButton;
    Button chSalaryButton;
    VBox buttonsVBox;

    String username;
    Manager manager;


    public void showScene(User user) throws FileNotFoundException {
        username = user.getUsername();
        getUser();

        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        Image icon = new Image(new FileInputStream("resources/Images/icon.png"));
        window.getIcons().add(icon);

        Image image = new Image(new FileInputStream("resources/Images/edit.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(45);
        imageView.setPreserveRatio(true);

        titleLabel = new Label("Edit Manager " + manager.getUsername());
        titleLabel.setStyle("-fx-font: normal bold 14px 'arial'; -fx-text-fill: #303030;");

        chUsernameButton = new Button("Change username");
        chUsernameButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        chUsernameButton.setStyle("-fx-background-color: #8B0000; -fx-text-fill: #eeeeee;-fx-font-weight:bold;");
        chUsernameButton.setOnAction(actionEvent -> {
            String newUsername = null;
            try {
                newUsername = new GetInputScene().showScene("username");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println(newUsername);
            changeUsername(newUsername);
        });

        chPasswordButton = new Button("Change password");
        chPasswordButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        chPasswordButton.setStyle("-fx-background-color: #8B0000; -fx-text-fill: #eeeeee;-fx-font-weight:bold;");
        chPasswordButton.setOnAction(actionEvent -> {
            String newPassword = null;
            try {
                newPassword = new ChangePasswordScene().showScene(manager.getPassword());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            changePassword(newPassword);
        });

        chPhoneNrButton = new Button("Change phone number");
        chPhoneNrButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        chPhoneNrButton.setStyle("-fx-background-color: #8B0000; -fx-text-fill: #eeeeee;-fx-font-weight:bold;");
        chPhoneNrButton.setOnAction(actionEvent -> {
            String newPhoneNr = null;
            try {
                newPhoneNr = new GetInputScene().showScene("phone number");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            changePhoneNr(newPhoneNr);
        });

        chEmailButton = new Button("Change email");
        chEmailButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        chEmailButton.setStyle("-fx-background-color: #8B0000; -fx-text-fill: #eeeeee;-fx-font-weight:bold;");
        chEmailButton.setOnAction(actionEvent -> {
            String newEmail = null;
            try {
                newEmail = new GetInputScene().showScene("email");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            changeEmail(newEmail);
        });

        chSalaryButton = new Button("Change salary");
        chSalaryButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        chSalaryButton.setStyle("-fx-background-color: #8B0000; -fx-text-fill: #eeeeee;-fx-font-weight:bold;");
        chSalaryButton.setOnAction(actionEvent -> {
            String newSalary = null;
            try {
                newSalary = new GetInputScene().showScene("salary");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            changeSalary(newSalary);
        });

        buttonsVBox = new VBox(10);
        buttonsVBox.setPadding(new Insets(10,40,10,40));
        buttonsVBox.getChildren().addAll(chUsernameButton, chPasswordButton, chPhoneNrButton, chEmailButton, chSalaryButton);


        HBox titleHBox = new HBox();
        titleHBox.setPadding(new Insets(20,0,0,0));
        titleHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        titleHBox.setAlignment(Pos.CENTER);
        titleHBox.setSpacing(10);
        titleHBox.getChildren().addAll(imageView,titleLabel);

        cancelButton = new Button("Exit");
        cancelButton.setStyle("-fx-background-color: #303030; -fx-text-fill: #eeeeee;-fx-font-weight:bold;");
        cancelButton.setOnAction(actionEvent -> window.close());

        VBox exitVBox = new VBox();
        exitVBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        exitVBox.setPadding(new Insets(0, 70, 0, 70));
        exitVBox.setAlignment(Pos.CENTER);
        exitVBox.getChildren().add(cancelButton);

        layout = new VBox(20);
        layout.setPadding(new Insets(15));
        layout.getChildren().addAll(titleHBox, buttonsVBox,exitVBox);
        layout.setStyle("-fx-background-color: #F5F5F5;");

        scene = new Scene(layout, 300, 380);
        window.setScene(scene);
        window.showAndWait();
    }

    void changeUsername(String newUsername)
    {
        if(!newUsername.equals(""))
            manager.setUsername(newUsername);
    }

    void changePassword(String newPassword)
    {
        if(!newPassword.equals(""))
            manager.setPassword(newPassword);
    }

    void changePhoneNr(String newPhoneNr)
    {
        if(!newPhoneNr.equals(""))
            manager.setPhoneNumber(newPhoneNr);
    }

    void changeEmail(String newEmail)
    {
        if(!newEmail.equals(""))
            manager.setEmail(newEmail);
    }

    void changeSalary(String newSalary)
    {
        if(!newSalary.equals(""))
            manager.setSalary(Double.parseDouble(newSalary));
    }

    void getUser()
    {
        manager = new Manager();

        for(Manager e : DataBase.getManager())
            if(username.equals(e.getUsername()))
            {
                manager = e;
                return;
            }
    }


}
