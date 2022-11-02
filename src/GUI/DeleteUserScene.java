package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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

public class DeleteUserScene {

    Stage window;
    Label titleLabel;
    Label errorLabel;
    Button deleteButton;
    Button cancelButton;
    Scene scene;
    VBox layout;
    ObservableList<User> users;
    TableView<User> usersTable;

    public void showScene() throws FileNotFoundException {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        Image icon = new Image(new FileInputStream("resources/Images/icon.png"));
        window.getIcons().add(icon);

        Image image = new Image(new FileInputStream("resources/Images/deleteUser.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(38);
        imageView.setPreserveRatio(true);

        titleLabel = new Label("  Delete User");
        titleLabel.setStyle("-fx-font: normal bold 14px 'arial'; -fx-text-fill: #303030;");


        setTable();

        errorLabel = new Label("");
        errorLabel.setStyle("-fx-text-fill: #8B0000;");

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        deleteButton = new Button("Delete");
        deleteButton.setStyle("-fx-background-color: #8B0000; -fx-text-fill: #eeeeee;-fx-font-weight: bold;");
        deleteButton.setOnAction(actionEvent -> deleteUser());
        HBox deleteHBox = new HBox(20);
        deleteHBox.getChildren().addAll(spacer, errorLabel, deleteButton);

        cancelButton = new Button("Exit");
        cancelButton.setStyle("-fx-background-color: #303030; -fx-text-fill: #eeeeee;-fx-font-weight: bold;");
        cancelButton.setOnAction(actionEvent -> window.close());

        Pane spacer1 = new Pane();
        HBox.setHgrow(spacer1, Priority.ALWAYS);
        HBox titleHBox = new HBox();
        titleHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        titleHBox.setAlignment(Pos.CENTER);
        titleHBox.setPadding(new Insets(5, 0, 5, 0));
        titleHBox.getChildren().addAll(imageView,titleLabel, spacer1, cancelButton);

        layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(titleHBox, usersTable, deleteHBox);
        layout.setStyle("-fx-background-color: #F5F5F5;");

        scene = new Scene(layout, 620, 380);
        window.setScene(scene);
        window.showAndWait();
    }

    private void deleteUser()
    {
        String username = "";

        if(!usersTable.getSelectionModel().isEmpty())
        {
            username = usersTable.getSelectionModel().getSelectedItem().getUsername();
            errorLabel.setText("");
        }
        else
        {
            errorLabel.setText("Select a user!");
            return;
        }

        for(int i = 0; i < DataBase.getCashiers().size(); i++)
        {
            Cashier cashier = DataBase.getCashiers().get(i);

            if(username.equals(cashier.getUsername()))
            {
                DataBase.getCashiers().remove(i);
                setTable();
                layout.getChildren().set(1, usersTable);
                return;
            }
        }

        for(int i = 0; i < DataBase.getManager().size(); i++)
        {
            Manager manager = DataBase.getManager().get(i);

            if(username.equals(manager.getUsername()))
            {
                DataBase.getManager().remove(i);
                setTable();
                layout.getChildren().set(1, usersTable);
                return;
            }
        }
    }

    void setTable()
    {
        TableColumn<User, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setMinWidth(80);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));

        TableColumn<User, String> idCardNrColumn = new TableColumn<>("ID Card Number");
        idCardNrColumn.setMinWidth(100);
        idCardNrColumn.setCellValueFactory(new PropertyValueFactory<>("idCardNumber"));

        TableColumn<User, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setMinWidth(100);
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<User, String> roleColumn = new TableColumn<>("Role");
        roleColumn.setMinWidth(100);
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("roleName"));

        TableColumn<User, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<User, String> surnameColumn = new TableColumn<>("Surname");
        surnameColumn.setMinWidth(100);
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));

        users = FXCollections.observableArrayList();
        users.addAll(DataBase.getCashiers());
        users.addAll(DataBase.getManager());

        usersTable = new TableView<>();
        usersTable.setItems(users);
        usersTable.getColumns().addAll(idColumn, usernameColumn,nameColumn,surnameColumn, roleColumn,idCardNrColumn);
    }
}
