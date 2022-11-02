package GUI;

import javafx.collections.FXCollections;
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
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AdminScene extends ManagerScene{

    Button allUsersButton;
    Button addUserButton;
    Button modifyUserButton;
    Button deleteUserButton;

    Button incomesButton;
    Button investmentsButton;
    HBox economyHBox;

    public void showScene(Stage window) throws FileNotFoundException {

        Image image = new Image(new FileInputStream("resources/Images/admin.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(38);
        imageView.setPreserveRatio(true);

      title=new Label("    Administrator");
      title.setGraphic(imageView);


        setLayout(window);
        setAdminLayout();

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        layout = new VBox(35);
        layout.setPadding(new Insets(10, 30, 30, 30));
        layout.getChildren().addAll(titleHBox, allButtons, spacer, economyHBox);
        layout.setStyle("-fx-background-color: #ffffff;");

        scene = new Scene(layout, 480, 350);
        window.setResizable(false);
        window.setScene(scene);

        lowQuantityProds = FXCollections.observableArrayList();
        checkProductQuantity();

    }

    void setAdminLayout(){

        allUsersButton = new Button("All Users");
        allUsersButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        allUsersButton.setPrefWidth(128);
        allUsersButton.setAlignment(Pos.CENTER);
        allUsersButton.setStyle("-fx-background-color: #8B0000; -fx-text-fill: #eeeeee;-fx-font-weight: bold;");
        allUsersButton.setOnAction(actionEvent -> {
            try {
                new AllUsersScene().showScene();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        addUserButton = new Button("Add User");
        addUserButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        addUserButton.setPrefWidth(128);
        addUserButton.setAlignment(Pos.CENTER);
        addUserButton.setStyle("-fx-background-color: #8B0000; -fx-text-fill: #eeeeee;-fx-font-weight: bold;");
        addUserButton.setOnAction(actionEvent -> {
            try {
                new AddUserScene().showScene();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        modifyUserButton = new Button("Modify User");
        modifyUserButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        modifyUserButton.setPrefWidth(128);
        modifyUserButton.setAlignment(Pos.CENTER);
        modifyUserButton.setStyle("-fx-background-color:#8B0000; -fx-text-fill: #eeeeee;-fx-font-weight: bold;");
        modifyUserButton.setOnAction(actionEvent -> {
            try {
                new ModifyUserScene().showScene();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        deleteUserButton = new Button("Delete User");
        deleteUserButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        deleteUserButton.setPrefWidth(128);
        deleteUserButton.setAlignment(Pos.CENTER);
        deleteUserButton.setStyle("-fx-background-color: #8B0000; -fx-text-fill: #eeeeee;-fx-font-weight: bold;");
        deleteUserButton.setOnAction(actionEvent -> {
            try {
                new DeleteUserScene().showScene();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        investmentsButton = new Button("Investments");
        investmentsButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        investmentsButton.setPrefWidth(96);
        investmentsButton.setStyle("-fx-background-color: #108010; -fx-text-fill: #eeeeee;-fx-font-weight: bold;");
        investmentsButton.setOnAction(actionEvent -> {
            try {
                new InvestmentsScene().showScene();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        incomesButton = new Button("Incomes");
        incomesButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        incomesButton.setPrefWidth(96);
        incomesButton.setStyle("-fx-background-color: #108010; -fx-text-fill: #eeeeee;-fx-font-weight: bold;");
        incomesButton.setOnAction(actionEvent -> {
            try {
                new IncomesScene().showScene();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        economyHBox = new HBox(10);
        economyHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        economyHBox.setAlignment(Pos.CENTER_RIGHT);
        economyHBox.getChildren().addAll(incomesButton, investmentsButton);

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        titleHBox = new HBox();
        titleHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        titleHBox.setAlignment(Pos.CENTER);
        titleHBox.setPadding(new Insets(5, 0, 5, 0));
        titleHBox.getChildren().addAll(title, spacer, logoutButton);

        VBox usersVBox = new VBox(10);
        usersVBox.getChildren().addAll(allUsersButton, addUserButton, modifyUserButton, deleteUserButton);

        allButtons = new HBox(30);
        allButtons.getChildren().addAll(usersVBox, statsVBox, addVBox);

    }



}
