package GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import Module.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class  GetInputScene {

    Stage window;
    Label titleLabel;
    TextField input;
    Label errorLabel;
    Button confirmButton;
    Button cancelButton;
    HBox buttonsHBox;
    Scene scene;
    VBox layout;
    String value;
    String instruction;

    public String showScene(String instruction) throws FileNotFoundException {
        this.instruction = instruction;
        value = "";

        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        window = new Stage();
        Image icon = new Image(new FileInputStream("resources/Images/icon.png"));
        window.getIcons().add(icon);


        titleLabel = new Label("Change " + instruction);
        titleLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #eeeeee;");
        titleLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        titleLabel.setAlignment(Pos.CENTER);

        input = new TextField();
        input.setPromptText(instruction);
        input.setStyle("-fx-background-color: #505050; -fx-text-fill: #eeeeee; -fx-prompt-text-fill: #888888;");

        errorLabel = new Label("");
        errorLabel.setStyle("-fx-text-fill: #ff5050;");

        VBox inputVBox = new VBox(5);
        inputVBox.getChildren().addAll(input, errorLabel);

        confirmButton = new Button("Confirm");
        confirmButton.setPrefWidth(96);
        confirmButton.setStyle("-fx-background-color: #108010; -fx-text-fill: #eeeeee;-fx-font-weight:bold;");
        confirmButton.setOnAction(actionEvent -> setValue());

        cancelButton = new Button("Cancel");
        cancelButton.setPrefWidth(96);
        cancelButton.setStyle("-fx-background-color: #8B0000; -fx-text-fill: #eeeeee;-fx-font-weight:bold;");
        cancelButton.setOnAction(actionEvent -> window.close());

        buttonsHBox = new HBox(30);
        buttonsHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        buttonsHBox.setAlignment(Pos.CENTER);
        buttonsHBox.getChildren().addAll(confirmButton,cancelButton);

        layout = new VBox(30);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(titleLabel, inputVBox, buttonsHBox);
        layout.setStyle("-fx-background-color: #303030;");

        scene = new Scene(layout, 480, 200);
        window.setScene(scene);
        window.showAndWait();

        return value;
    }

    private void setValue()
    {
        if(instruction.equals("username"))
        {
            if(!input.getText().isEmpty() && InputCheck.isUsernameValid(input.getText()))
            {
                if(!InputCheck.doesUsernameExists(input.getText()))
                {
                    value = input.getText();
                    errorLabel.setText("");
                    window.close();
                }
                else
                {
                    errorLabel.setText("Username already exists.");
                }
            }
            else
            {
                errorLabel.setText("Enter new username!");
            }

            return;
        }

        if(instruction.equals("phone number"))
        {
            if(!input.getText().isEmpty() && InputCheck.isPhoneNrValid(input.getText()))
            {
                value = input.getText();
                errorLabel.setText("");
                window.close();
            }
            else
            {
                errorLabel.setText("Enter phone number (10 digits only)");
            }

            return;
        }

        if(instruction.equals("email"))
        {
            if(!input.getText().isEmpty())
            {
                value = input.getText();
                errorLabel.setText("");
                window.close();
            }
            else
            {
                errorLabel.setText("Enter email");
            }

            return;
        }

        if(instruction.equals("salary"))
        {
            if(!input.getText().isEmpty() && InputCheck.isDouble(input.getText()))
            {
                value = input.getText();
                errorLabel.setText("");
                window.close();
            }
            else
            {
                errorLabel.setText("Enter salary");
            }
        }
    }
}
