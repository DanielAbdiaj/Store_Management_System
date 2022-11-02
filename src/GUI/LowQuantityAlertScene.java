package GUI;

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
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import Module.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LowQuantityAlertScene
{
    Stage window;
    Scene scene;
    Label titleLabel;
    Button closeButton;
    ObservableList<ProductCD> products;
    TableView<ProductCD> productsTable;

    public void showScene(ObservableList<ProductCD> products) throws FileNotFoundException {
        this.products = products;

        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        window = new Stage();
        Image icon = new Image(new FileInputStream("resources/Images/icon.png"));
        window.getIcons().add(icon);


        titleLabel = new Label("The following CDs have a less than 5 quantity");
        titleLabel.setStyle("-fx-font: normal bold 16px 'arial'; -fx-text-fill: #303030;");
        setTable();

        closeButton = new Button("Close");
        closeButton.setStyle("-fx-background-color: #8B0000; -fx-text-fill: #eeeeee;-fx-font-weight:bold;");
        closeButton.setOnAction(actionEvent -> window.close());

        VBox layout = new VBox(20);
        layout.setPadding(new Insets(30, 30, 30, 30));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(titleLabel, productsTable, closeButton);
        layout.setStyle("-fx-background-color: #ffffff;");

        scene = new Scene(layout, 480, 420);
        window.setScene(scene);
        window.showAndWait();
    }

    void setTable()
    {
        TableColumn<ProductCD, String> nameColumn = new TableColumn<>("Product");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<ProductCD, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(70);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        productsTable = new TableView<>();
        productsTable.setItems(products);
        productsTable.getColumns().addAll(nameColumn, quantityColumn);
    }
}

