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
import Module.Cashier;
import Module.Manager;
import Module.DataBase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class AllUsersScene {

    Stage window;
    Label titleLabel;
    Label cashiersLabel;
    Label managersLabel;
    Button cancelButton;
    VBox cashiersVBox;
    VBox managersVBox;
    Scene scene;
    VBox layout;
    ObservableList<Cashier> cashiers;
    TableView<Cashier> cashiersTable;
    ObservableList<Manager> managers;
    TableView<Manager> managersTable;

    public void showScene() throws FileNotFoundException {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        Image icon = new Image(new FileInputStream("resources/Images/icon.png"));
        window.getIcons().add(icon);

        Image image = new Image(new FileInputStream("resources/Images/allUsers.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(38);
        imageView.setPreserveRatio(true);

        titleLabel = new Label("  All Users");
        titleLabel.setStyle("-fx-font: normal bold 14px 'arial'; -fx-text-fill: #303030;");

        setCashiersTable();
        setManagersTable();

        cancelButton = new Button("Exit");
        cancelButton.setStyle("-fx-background-color:  #8B0000; -fx-text-fill: #eeeeee;-fx-font-weight: bold;");
        cancelButton.setOnAction(actionEvent -> window.close());

        cashiersLabel = new Label("Cashiers");
        cashiersLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #303030;");
        cashiersVBox = new VBox(10);
        cashiersVBox.getChildren().addAll(cashiersLabel, cashiersTable);

        managersLabel = new Label("Managers");
        managersLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #303030;");
        managersVBox = new VBox(10);
        managersVBox.getChildren().addAll(managersLabel, managersTable);

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox titleHBox = new HBox();
        titleHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        titleHBox.setAlignment(Pos.CENTER);
        titleHBox.setPadding(new Insets(5, 5, 10, 5));
        titleHBox.getChildren().addAll(imageView,titleLabel, spacer, cancelButton);

        layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(titleHBox, cashiersVBox, managersVBox);
        layout.setStyle("-fx-background-color: #F5F5F5;");

        scene = new Scene(layout, 1130, 520);
        window.setScene(scene);
        window.showAndWait();
    }

    void setCashiersTable()
    {
        TableColumn<Cashier, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setMinWidth(50);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));

        TableColumn<Cashier, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setMinWidth(90);
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<Cashier, String> roleColumn = new TableColumn<>("Role");
        roleColumn.setMinWidth(100);
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("roleName"));

        TableColumn<Cashier, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(70);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Cashier, String> surnameColumn = new TableColumn<>("Surname");
        surnameColumn.setMinWidth(70);
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));

        TableColumn<Cashier, String> phNrColumn = new TableColumn<>("Phone Number");
        phNrColumn.setMinWidth(100);
        phNrColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        // Birthday Column

        TableColumn<Cashier, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setMinWidth(150);
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Cashier, Double> salaryColumn = new TableColumn<>("Salary");
        salaryColumn.setMinWidth(70);
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));

        TableColumn<Cashier, String> idCardNrColumn = new TableColumn<>("ID Card Number");
        idCardNrColumn.setMinWidth(100);
        idCardNrColumn.setCellValueFactory(new PropertyValueFactory<>("idCardNumber"));

        TableColumn<Cashier, Integer> nrOfBillsColumn = new TableColumn<>("Bills");
        nrOfBillsColumn.setMinWidth(50);
        nrOfBillsColumn.setCellValueFactory(new PropertyValueFactory<>("numOfBills"));

        TableColumn<Cashier, Integer> nrOfProdSoldColumn = new TableColumn<>("CD Sold");
        nrOfProdSoldColumn.setMinWidth(100);
        nrOfProdSoldColumn.setCellValueFactory(new PropertyValueFactory<>("numOfCDSold"));

        TableColumn<Cashier, Double> moneyGenColumn = new TableColumn<>("Money Generated");
        moneyGenColumn.setMinWidth(150);
        moneyGenColumn.setCellValueFactory(new PropertyValueFactory<>("moneyGenerated"));

        cashiers = FXCollections.observableArrayList();
        cashiers.addAll(DataBase.getCashiers());

        cashiersTable = new TableView<>();
        cashiersTable.setItems(cashiers);
        cashiersTable.getColumns().addAll(idColumn, usernameColumn, roleColumn, nameColumn,surnameColumn,phNrColumn, emailColumn,
                salaryColumn, idCardNrColumn, nrOfBillsColumn, nrOfProdSoldColumn, moneyGenColumn);
    }

    void setManagersTable()
    {
        TableColumn<Manager, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setMinWidth(50);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));

        TableColumn<Manager, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setMinWidth(90);
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<Manager, String> roleColumn = new TableColumn<>("Role");
        roleColumn.setMinWidth(100);
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("roleName"));

        TableColumn<Manager, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(70);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Manager, String> surnameColumn = new TableColumn<>("Surname");
        surnameColumn.setMinWidth(70);
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));

        TableColumn<Manager, String> phNrColumn = new TableColumn<>("Phone Number");
        phNrColumn.setMinWidth(100);
        phNrColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        // Birthday Column

        TableColumn<Manager, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setMinWidth(150);
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Manager, Double> salaryColumn = new TableColumn<>("Salary");
        salaryColumn.setMinWidth(70);
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));

        TableColumn<Manager, String> idCardNrColumn = new TableColumn<>("ID Card Number");
        idCardNrColumn.setMinWidth(150);
        idCardNrColumn.setCellValueFactory(new PropertyValueFactory<>("idCardNumber"));

        managers = FXCollections.observableArrayList();
        managers.addAll(DataBase.getManager());

        managersTable = new TableView<>();
        managersTable.setItems(managers);
        managersTable.getColumns().addAll(idColumn, usernameColumn, roleColumn, nameColumn,surnameColumn,phNrColumn, emailColumn,
                salaryColumn, idCardNrColumn);
    }
}



