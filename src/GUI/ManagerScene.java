package GUI;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

import Module.*;

public class ManagerScene {

    Manager manager;

    Scene scene;
    Label title;
    Button logoutButton;
    Button addProductButton;
    Button addQuantityButton;
    Button addGenreButton;
    Button showCashierStatsButton;
    Button showProductsSoldButton;
    Button showProductsBoughtButton;
    VBox statsVBox;
    VBox addVBox;
    HBox titleHBox;
    HBox allButtons;

    VBox layout;
    ObservableList<ProductCD> lowQuantityProds;

    public void showScene(Manager manager,Stage window) throws FileNotFoundException {

        this.manager = manager;

        Image image = new Image(new FileInputStream("resources/Images/manager.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(65);
        imageView.setPreserveRatio(true);


        title = new Label(" "+manager.getUsername());
        title.setGraphic(imageView);
        setLayout(window);

        layout = new VBox(35);
        layout.setPadding(new Insets(10, 30, 10, 30));
        layout.getChildren().addAll(titleHBox, allButtons);
        layout.setStyle("-fx-background-color: #F5F5F5;");

        scene = new Scene(layout, 440, 320);
        window.setScene(scene);



        lowQuantityProds = FXCollections.observableArrayList();
        checkProductQuantity();

    }

    void setLayout(Stage window){

        title.setStyle("-fx-font: normal bold 15px 'arial'; -fx-text-fill: #303030;");

        // Add CD button
        addProductButton = new Button("Add a new CD");
        addProductButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        addProductButton.setPrefWidth(128);
        addProductButton.setAlignment(Pos.CENTER);
        addProductButton.setStyle("-fx-background-color: #8B0000; -fx-text-fill: #eeeeee;-fx-font-weight: bold;");
        addProductButton.setOnAction(actionEvent -> {
            try {
                new AddProductScene().showScene();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        // Add quantity button
        addQuantityButton = new Button("Add Quantity");
        addQuantityButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        addQuantityButton.setPrefWidth(128);
        addQuantityButton.setAlignment(Pos.CENTER);
        addQuantityButton.setStyle("-fx-background-color: #8B0000; -fx-text-fill: #eeeeee;-fx-font-weight: bold;");
        addQuantityButton.setOnAction(actionEvent -> {
            try {
                new AddQuantityScene().showScene();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        // Add Genre button
        addGenreButton = new Button("Add Genre");
        addGenreButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        addGenreButton.setPrefWidth(128);
        addGenreButton.setAlignment(Pos.CENTER);
        addGenreButton.setStyle("-fx-background-color: #8B0000; -fx-text-fill: #eeeeee;-fx-font-weight: bold;");
        addGenreButton.setOnAction(actionEvent -> {
            try {
                new AddGenreScene().showScene();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        // Show Cashier stats button
        showCashierStatsButton = new Button("Cashier stats");
        showCashierStatsButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        showCashierStatsButton.setPrefWidth(128);
        showCashierStatsButton.setAlignment(Pos.CENTER);
        showCashierStatsButton.setStyle("-fx-background-color: #8B0000; -fx-text-fill: #eeeeee;-fx-font-weight: bold;");
        showCashierStatsButton.setOnAction(actionEvent -> {
            try {
                new CashierStatsScene().showScene();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        // Show sold products button
        showProductsSoldButton = new Button("CDs sold");
        showProductsSoldButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        showProductsSoldButton.setPrefWidth(128);
        showProductsSoldButton.setAlignment(Pos.CENTER);
        showProductsSoldButton.setStyle("-fx-background-color:#8B0000; -fx-text-fill: #eeeeee;-fx-font-weight: bold;");
        showProductsSoldButton.setOnAction(actionEvent -> {
            try {
                new ProductsSoldScene().showScene();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        // Show bought products button
        showProductsBoughtButton = new Button("CDs bought");
        showProductsBoughtButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        showProductsBoughtButton.setPrefWidth(128);
        showProductsBoughtButton.setAlignment(Pos.CENTER);
        showProductsBoughtButton.setStyle("-fx-background-color: #8B0000; -fx-text-fill: #eeeeee;-fx-font-weight: bold;");
        showProductsBoughtButton.setOnAction(actionEvent -> {
            try {
                new BoughtProductScene().showScene();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });



        // Logout button
        logoutButton = new Button("Logout");
        logoutButton.setStyle("-fx-background-color: #303030; -fx-text-fill: #eeeeee;-fx-font-weight: bold;");
        logoutButton.setOnAction(actionEvent -> {
            try { Login.showScene(window); }
            catch (FileNotFoundException e) { e.printStackTrace(); }
        });

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        titleHBox = new HBox();
        titleHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        titleHBox.setAlignment(Pos.CENTER);
        titleHBox.setPadding(new Insets(25, 0, 10, 0));
        titleHBox.getChildren().addAll(title, spacer, logoutButton);

        statsVBox = new VBox(10);
        statsVBox.getChildren().addAll(showCashierStatsButton, showProductsSoldButton, showProductsBoughtButton);

        addVBox = new VBox(10);
        addVBox.getChildren().addAll(addProductButton, addQuantityButton, addGenreButton);

        Pane spacer2 = new Pane();
        HBox.setHgrow(spacer2, Priority.ALWAYS);

        allButtons = new HBox();
        allButtons.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        allButtons.setPadding(new Insets(0,50,0,50));
        allButtons.setAlignment(Pos.CENTER);
        allButtons.getChildren().addAll(statsVBox,spacer2,addVBox);



    }

    void checkProductQuantity() throws FileNotFoundException {
        for(ProductCD p : DataBase.getProductsCD())
            if(p.getQuantity() < 5)
                lowQuantityProds.add(p);

        if(lowQuantityProds.size() > 0)
            new LowQuantityAlertScene().showScene(lowQuantityProds);
    }



}
