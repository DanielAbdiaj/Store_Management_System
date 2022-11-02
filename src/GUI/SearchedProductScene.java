package GUI;
        import Module.*;
        import javafx.geometry.Insets;
        import javafx.geometry.Pos;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.scene.control.Label;
        import javafx.scene.control.Spinner;
        import javafx.scene.image.Image;
        import javafx.scene.image.ImageView;
        import javafx.scene.layout.HBox;
        import javafx.scene.layout.Pane;
        import javafx.scene.layout.Priority;
        import javafx.scene.layout.VBox;
        import javafx.stage.Modality;
        import javafx.stage.Stage;

        import java.io.FileInputStream;
        import java.io.FileNotFoundException;

public class SearchedProductScene
{
    ProductCD product;
    Stage window;
    Scene scene;
    VBox layout;
    Label productLabel;
    Label quantityLabel;
    Label spinnerLabel;
    Label errorLabel;
    Spinner<Integer> quantitySpinner;
    Button addButton;
    Button cancelButton;
    int quantity;

    public int showScene(ProductCD product) throws FileNotFoundException {
        this.product = product;

        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        window = new Stage();
        Image icon = new Image(new FileInputStream("resources/Images/icon.png"));
        window.getIcons().add(icon);



        setLayout();
        quantity = 0;

        addButton.setOnAction(actionEvent -> addButtonClicked());
        cancelButton.setOnAction(actionEvent -> window.close());

        layout.setPadding(new Insets(20, 20, 10, 20));
        scene = new Scene(layout, 200, 260);
        window.setScene(scene);
        window.showAndWait();

        return quantity;
    }

    void addButtonClicked()
    {
        if(quantitySpinner.getValue() < 1)
            errorLabel.setText("Select a number!");
        else
        {
            quantity = quantitySpinner.getValue();
            window.close();
        }
    }

    void setLayout() throws FileNotFoundException {
        layout = new VBox(20);
        layout.setStyle("-fx-background-color: #ffffff;");

        productLabel = new Label("");
        productLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        productLabel.setAlignment(Pos.CENTER);
        productLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #303030;");

        quantityLabel = new Label("");
        quantityLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        quantityLabel.setAlignment(Pos.CENTER);
        quantityLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #303030;");

        spinnerLabel = new Label("Select quantity:");

        errorLabel = new Label("");
        errorLabel.setStyle("-fx-text-fill: #ff5050;");
        errorLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        errorLabel.setAlignment(Pos.CENTER_RIGHT);

        addButton = new Button("Add to cart");
        addButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        addButton.setStyle("-fx-background-color: #108010; -fx-text-fill: #eeeeee;-fx-font-weight:bold");

        cancelButton = new Button("Cancel");
        cancelButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        cancelButton.setStyle("-fx-background-color: #8B0000; -fx-text-fill: #eeeeee;-fx-font-weight:bold");

        if(product.getName().equals(""))
        {
            productLabel.setText("No results");
            VBox vBox = new VBox(10);
            vBox.setPadding(new Insets(60,0,0,0));
            vBox.getChildren().addAll(productLabel, cancelButton);
            layout.getChildren().addAll(vBox);
        }
        else
        {
            productLabel.setText(product.getName());

            if(product.getQuantity() > 0)
            {
                quantityLabel.setText("Available: " + product.getQuantity());
                quantitySpinner = new Spinner<>(0, product.getQuantity(), 0);

                Image image = new Image(new FileInputStream("resources/Images/searchCD.png"));
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(30);
                imageView.setPreserveRatio(true);

                Pane spacer = new Pane();
                HBox.setHgrow(spacer, Priority.ALWAYS);
                Pane spacer1 = new Pane();
                HBox.setHgrow(spacer1, Priority.ALWAYS);


                HBox productHBox = new HBox(10); // productLabel, quantityLabel
                productHBox.getChildren().addAll(imageView,spacer, productLabel, quantityLabel);

                VBox spinnerVBox = new VBox(10); // spinnerLabel, quantityLabel, errorLabel
                spinnerVBox.getChildren().addAll(spinnerLabel, quantitySpinner, errorLabel);
                HBox spinnerHBox = new HBox();
                spinnerHBox.getChildren().addAll(spacer1, spinnerVBox);

                VBox buttonsVBox = new VBox(15); // addButton, cancelButton
                buttonsVBox.getChildren().addAll(addButton, cancelButton);
                HBox buttonsHBox = new HBox();
                buttonsHBox.setAlignment(Pos.CENTER);
                buttonsHBox.getChildren().addAll(buttonsVBox);


                layout.getChildren().addAll(productHBox, spinnerHBox, buttonsHBox);


            }
            else
            {
                quantityLabel.setText("This product is out of stock.");
                VBox vBox = new VBox(20);
                vBox.setPadding(new Insets(10));
                vBox.getChildren().addAll(productLabel, quantityLabel, cancelButton);
                layout.getChildren().addAll(vBox);
            }
        }
    }
}
