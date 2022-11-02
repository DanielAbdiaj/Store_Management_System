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

public class AddGenreScene
{
    Stage window;
    Label instructionLabel;
    TextField GenreTxtField;
    Label errorLabel;
    Button addButton;
    Button exitButton;
    HBox buttonsHBox;
    VBox layout;

    public void showScene() throws FileNotFoundException {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        window = new Stage();
        Image icon = new Image(new FileInputStream("resources/Images/icon.png"));
        window.getIcons().add(icon);


        instructionLabel = new Label("Enter Genre name!");
        instructionLabel.setStyle("-fx-font: normal bold 14px 'arial'; -fx-text-fill: #eeeeee;");
        instructionLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        instructionLabel.setAlignment(Pos.CENTER);

        GenreTxtField = new TextField();
        GenreTxtField.setPromptText("Genre");
        GenreTxtField.setStyle("-fx-background-color: #505050; -fx-text-fill: #eeeeee; -fx-prompt-text-fill: #888888;");

        errorLabel = new Label("");
        errorLabel.setStyle("-fx-text-fill: #ff5050;");

        VBox inputVBox = new VBox(5);
        inputVBox.getChildren().addAll(GenreTxtField, errorLabel);

        addButton = new Button("Add");
        addButton.setPrefWidth(96);
        addButton.setStyle("-fx-background-color: #108010; -fx-text-fill: #eeeeee;-fx-font-weight:bold");
        addButton.setOnAction(actionEvent -> add());

        exitButton = new Button("Cancel");
        exitButton.setPrefWidth(96);
        exitButton.setStyle("-fx-background-color: #bb2020; -fx-text-fill: #eeeeee;-fx-font-weight:bold");
        exitButton.setOnAction(actionEvent -> window.close());

        buttonsHBox = new HBox(30);
        buttonsHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        buttonsHBox.setAlignment(Pos.CENTER);
        buttonsHBox.getChildren().addAll( addButton,exitButton);

        layout = new VBox(30);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(instructionLabel, inputVBox, buttonsHBox);
        layout.setStyle("-fx-background-color: #303030;");

        Scene scene = new Scene(layout, 480, 200);
        window.setScene(scene);
        window.showAndWait();
    }

    void add()
    {
        if(GenreTxtField.getText().isEmpty())
            errorLabel.setText("The text field is empty.");
        else
        {
            errorLabel.setText("");
            DataBase.getGenre().add(GenreTxtField.getText());
            window.close();
        }
    }
}
