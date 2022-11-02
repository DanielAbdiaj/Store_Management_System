package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import Module.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class AddQuantityScene {



    Stage window;
    Scene scene;
    Label title;
    Button addButton;
    TextField quantityTextField;
    Label selItemErrorLabel;
    Label notIntErrorLabel;
    VBox tableVBox;
    Button closeButton;
    HBox quantityHBox;
    VBox layout;

    ObservableList<ProductCD> productsList;
    TableView<ProductCD> productTable;

    public void showScene() throws FileNotFoundException {
        window = new Stage();


        window = new Stage();
        Image icon = new Image(new FileInputStream("resources/Images/icon.png"));
        window.getIcons().add(icon);

        Image image = new Image(new FileInputStream("resources/Images/addQuantity.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(40);
        imageView.setPreserveRatio(true);


        title = new Label("    Add Product Quantity");
        title.setStyle("-fx-font: normal bold 14px 'arial'; -fx-text-fill: #303030;");

        productsList = FXCollections.observableArrayList();
        productsList.addAll(DataBase.getProductsCD());
        setTable();

        selItemErrorLabel = new Label("");
        selItemErrorLabel.setStyle("-fx-text-fill: #ff5050;");

        tableVBox = new VBox(5);
        tableVBox.getChildren().addAll(productTable, selItemErrorLabel);

        quantityTextField = new TextField();
        quantityTextField.setPromptText("Quantity");
        quantityTextField.setStyle("-fx-background-color: #D3D3D3; -fx-text-fill: #303030; -fx-prompt-text-fill: #888888");

        notIntErrorLabel = new Label("");
        notIntErrorLabel.setStyle("-fx-text-fill: #ff5050;");

        addButton = new Button("Add");
        addButton.setPrefWidth(76);
        addButton.setStyle("-fx-background-color: #8B0000; -fx-text-fill: #eeeeee;-fx-font-weight:bold");
        addButton.setOnAction(actionEvent -> addProductQuantity());

        quantityHBox = new HBox(10);
        quantityHBox.setMaxWidth(Double.MAX_VALUE);
        quantityHBox.setAlignment(Pos.CENTER_RIGHT);
        quantityHBox.getChildren().addAll(notIntErrorLabel, quantityTextField, addButton);

        closeButton = new Button("Close");
        closeButton.setPrefWidth(76);
        closeButton.setStyle("-fx-background-color: #303030; -fx-text-fill: #eeeeee;-fx-font-weight:bold");
        closeButton.setOnAction(actionEvent -> window.close());

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox titleHBox = new HBox();
        titleHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        titleHBox.setPadding(new Insets(0, 0, 10, 0));
        titleHBox.setAlignment(Pos.CENTER);
        titleHBox.getChildren().addAll(imageView,title, spacer, closeButton);

        layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(titleHBox, tableVBox, quantityHBox);
        layout.setStyle("-fx-background-color: #ffffff;");

        scene = new Scene(layout, 376, 568);
        window.setScene(scene);
        window.showAndWait();
    }

    void addProductQuantity()
    {
        String name = "";
        int quantity = 0;
        String input = quantityTextField.getText();
        boolean isFieldCorrect = true;

        if(!productTable.getSelectionModel().isEmpty())
        {
            name = productTable.getSelectionModel().getSelectedItem().getName();
            selItemErrorLabel.setText("");
        }
        else
        {
            selItemErrorLabel.setText("Select a product!");
            isFieldCorrect = false;
        }

        if(!input.isEmpty() && InputCheck.isInt(input))
        {
            quantity = Integer.parseInt(input);
            notIntErrorLabel.setText("");
        }
        else
        {
            notIntErrorLabel.setText("Enter a number!");
            isFieldCorrect = false;
        }

        if(isFieldCorrect)
        {
            for(ProductCD addedProduct : DataBase.getProductsCD())
                if(name.equals(addedProduct.getName()))
                {

                    DataBase.getBoughtProducts().add(new BoughtProduct());
                    int addedBoughtProdIndex = DataBase.getBoughtProducts().size() - 1;
                    BoughtProduct addedBoughtProd = DataBase.getBoughtProducts().get(addedBoughtProdIndex);

                    addedBoughtProd.setName(addedProduct.getName());
                    addedBoughtProd.setID(addedProduct.getID());
                    addedBoughtProd.setGenre(addedProduct.getGenre());
                    addedBoughtProd.setSinger(addedProduct.getSinger());
                    addedBoughtProd.setSupplierID(addedProduct.getSupplierID());
                    addedBoughtProd.setPrice(addedProduct.getPrice());
                    addedBoughtProd.setQuantity(quantity);
                    addedBoughtProd.setReleaseDate(addedProduct.getExpiryDate());

                    addedProduct.setQuantity(addedProduct.getQuantity() + quantity);

                    break;
                }

            setTable();
            tableVBox.getChildren().set(0, productTable);
        }

        quantityTextField.clear();
    }

    void setTable()
    {
        TableColumn<ProductCD, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<ProductCD, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(70);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        productTable = new TableView<>();
        productTable.setItems(productsList);
        productTable.getColumns().addAll(nameColumn, quantityColumn);
    }
}
