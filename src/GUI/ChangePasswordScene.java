package GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ChangePasswordScene{

    Stage window;
    Label titleLabel;
    Label oldPassErrLabel;
    Label newPassErrLabel;
    Label newPass2ErrLabel;
    PasswordField oldPassTxtField;
    PasswordField newPassTxtField;
    PasswordField newPass2TxtField;
    Button confirmButton;
    Button cancelButton;
    VBox oldPassHBox;
    VBox newPassHBox;
    VBox newPAss2HBox;
    HBox buttonsHBox;
    Scene scene;
    VBox layout;
    String value;
    String oldPassword;

    public String showScene(String oldPassword) throws FileNotFoundException {
        value = "";
        this.oldPassword = oldPassword;

        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        window = new Stage();
        Image icon = new Image(new FileInputStream("resources/Images/icon.png"));
        window.getIcons().add(icon);


        titleLabel = new Label("Change password");
        titleLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #eeeeee;");
        titleLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        titleLabel.setAlignment(Pos.CENTER);

        oldPassTxtField = new PasswordField();
        oldPassTxtField.setPromptText("Current password");
        oldPassTxtField.setStyle("-fx-background-color: #505050; -fx-text-fill: #eeeeee; -fx-prompt-text-fill: #888888;");
        oldPassErrLabel = new Label("");
        oldPassErrLabel.setStyle("-fx-text-fill: #ff5050;");

        oldPassHBox = new VBox(5);
        oldPassHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        oldPassHBox.getChildren().addAll(oldPassTxtField, oldPassErrLabel);

        newPassTxtField = new PasswordField();
        newPassTxtField.setPromptText("New password");
        newPassTxtField.setStyle("-fx-background-color: #505050; -fx-text-fill: #eeeeee; -fx-prompt-text-fill: #888888;");
        newPassErrLabel = new Label("");
        newPassErrLabel.setStyle("-fx-text-fill: #ff5050;");

        newPassHBox = new VBox(5);
        newPassHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        newPassHBox.getChildren().addAll(newPassTxtField, newPassErrLabel);

        newPass2TxtField = new PasswordField();
        newPass2TxtField.setPromptText("Confirm new password");
        newPass2TxtField.setStyle("-fx-background-color: #505050; -fx-text-fill: #eeeeee; -fx-prompt-text-fill: #888888;");
        newPass2ErrLabel = new Label("");
        newPass2ErrLabel.setStyle("-fx-text-fill: #ff5050;");

        newPAss2HBox = new VBox(5);
        newPass2TxtField.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        newPAss2HBox.getChildren().addAll(newPass2TxtField, newPass2ErrLabel);

        confirmButton = new Button("Confirm");
        confirmButton.setStyle("-fx-background-color: #108010; -fx-text-fill: #eeeeee;-fx-font-weight:bold;");
        confirmButton.setPrefWidth(96);
        confirmButton.setOnAction(actionEvent -> setValue());

        cancelButton = new Button("Cancel");
        cancelButton.setStyle("-fx-background-color: #8B0000; -fx-text-fill: #eeeeee;-fx-font-weight:bold;");
        cancelButton.setPrefWidth(96);
        cancelButton.setOnAction(actionEvent -> window.close());

        buttonsHBox = new HBox(30);
        buttonsHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        buttonsHBox.setAlignment(Pos.CENTER);
        buttonsHBox.getChildren().addAll(confirmButton,cancelButton);

        layout = new VBox(30);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(titleLabel, oldPassHBox, newPassHBox, newPAss2HBox, buttonsHBox);
        layout.setStyle("-fx-background-color: #303030;");

        scene = new Scene(layout, 480, 360);
        window.setScene(scene);
        window.showAndWait();

        return value;
    }

    void setValue()
    {
        boolean areFieldsCorrect = true;

        if(oldPassTxtField.getText().equals(oldPassword))
            oldPassErrLabel.setText("");
        else
        {
            areFieldsCorrect = false;
            oldPassErrLabel.setText("Incorrect password");
        }

        if(!newPassTxtField.getText().isEmpty())
            newPassErrLabel.setText("");
        else
        {
            areFieldsCorrect = false;
            newPassErrLabel.setText("Enter the new password!");
        }

        if(newPass2TxtField.getText().equals(newPassTxtField.getText()))
            newPass2ErrLabel.setText("");
        else
        {
            areFieldsCorrect = false;
            newPass2ErrLabel.setText("Password doesn't match");
        }

        if(areFieldsCorrect)
        {
            value = newPass2TxtField.getText();
            window.close();
        }
    }
}
