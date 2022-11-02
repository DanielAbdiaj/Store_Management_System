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

public class ModifyUserScene
{
    Stage window;
    Label titleLabel;
    Label errorLabel;
    Button editButton;
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

        Image image = new Image(new FileInputStream("resources/Images/ModUser.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(38);
        imageView.setPreserveRatio(true);

        titleLabel = new Label(" Modify Users");
        titleLabel.setStyle("-fx-font: normal bold 14px 'arial'; -fx-text-fill: #303030;");

        users = FXCollections.observableArrayList();
        users.addAll(DataBase.getCashiers());
        users.addAll(DataBase.getManager());
        setTable();

        errorLabel = new Label("");
        errorLabel.setStyle("-fx-text-fill: #8B0000;");

        editButton = new Button("Edit");
        editButton.setStyle("-fx-background-color: #8B0000; -fx-text-fill: #eeeeee;-fx-font-weight:bold;");
        editButton.setPrefWidth(64);
        editButton.setOnAction(actionEvent -> {
            try {
                editUser();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        Pane spacer2 = new Pane();
        HBox.setHgrow(spacer2, Priority.ALWAYS);
        HBox addHBox = new HBox(15);
        addHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        addHBox.setAlignment(Pos.CENTER);
        addHBox.getChildren().addAll(spacer2, errorLabel, editButton);

        cancelButton = new Button("Exit");
        cancelButton.setStyle("-fx-background-color: #303030; -fx-text-fill: #eeeeee;-fx-font-weight:bold;");
        cancelButton.setPrefWidth(64);
        cancelButton.setOnAction(actionEvent -> window.close());

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox titleHBox = new HBox();
        titleHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        titleHBox.setAlignment(Pos.CENTER);
        titleHBox.getChildren().addAll(imageView,titleLabel, spacer, cancelButton);

        layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(titleHBox,usersTable,addHBox);
        layout.setStyle("-fx-background-color: F5F5F5;");

        scene = new Scene(layout, 340, 500);
        window.setScene(scene);
        window.showAndWait();
    }

    private void editUser() throws FileNotFoundException {

        if (!usersTable.getSelectionModel().isEmpty()) {
            errorLabel.setText("");
            User user = usersTable.getSelectionModel().getSelectedItem();

            if(user.getRoleName().equals("Cashier"))
                new ModifyCashiersScene().showScene(user);

            if(user.getRoleName().equals("Manager"))
                new ModifyManagersScene().showScene(user);

        }
        else
        {
            errorLabel.setText("Select a user!");
        }




        }



        void setTable(){
            TableColumn<User, Integer> idColumn = new TableColumn<>("ID");
            idColumn.setMinWidth(100);
            idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));

            TableColumn<User, String> usernameColumn = new TableColumn<>("Username");
            usernameColumn.setMinWidth(100);
            usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

            TableColumn<User, String> roleColumn = new TableColumn<>("Role");
            roleColumn.setMinWidth(100);
            roleColumn.setCellValueFactory(new PropertyValueFactory<>("roleName"));

            usersTable = new TableView<>();
            usersTable.setItems(users);
            usersTable.getColumns().addAll(idColumn, usernameColumn, roleColumn);
        }
    }


