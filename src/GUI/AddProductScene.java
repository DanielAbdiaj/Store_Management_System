package GUI;

import Module.*;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

public class AddProductScene
{
    Stage window;
    Scene scene;
    VBox layout;
    Label title;
    Button exitButton;

    TextField nameTxtField;
    Label fillNameError;
    ChoiceBox<String> genreChBox;
    Label fillGenreError;
    TextField singerTxtField;
    Label fillSingerError;
    TextField supplierIDTxtField;
    Label fillSupplIDError;
    TextField priceTxtField;
    Label fillPriceError;
    TextField quantityTxtField;
    Label fillQuantityError;


    Label yearLabel;
    ChoiceBox<String> yearChoiceBox;
    Label monthLabel;
    ChoiceBox<String> monthChoiceBox;
    Label dayLabel;
    ChoiceBox<String> dayChoiceBox;
    Label releaseDateErrLabel;

    VBox yearVBox;
    VBox monthVBok;
    VBox dayVBox;

    VBox nameHBox;
    VBox genreHBox;
    VBox singerHBox;
    VBox supplierIDHBox;
    VBox priceHBox;
    VBox quantityHBox;
    HBox releaseDateHBox;


    Button addButton;

    public void showScene() throws FileNotFoundException {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        window = new Stage();
        Image icon = new Image(new FileInputStream("resources/Images/icon.png"));
        window.getIcons().add(icon);

        Image image = new Image(new FileInputStream("resources/Images/addCD.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(35);
        imageView.setPreserveRatio(true);


        setFields();


        monthChoiceBox.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> observable, String oldValue, String newValue) -> addDaysInDayChBox());
        yearChoiceBox.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> observable, String oldValue, String newValue) -> addDaysInDayChBox());

        title = new Label("     Add a new CD");
        title.setStyle("-fx-font: normal bold 14px 'arial'; -fx-text-fill: #303030;");


        addButton = new Button("Add CD");
        addButton.setStyle("-fx-background-color: #8B0000; -fx-text-fill: #eeeeee;-fx-font-weight: bold;");
        addButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        addButton.setOnAction(actionEvent -> addButtonPressed());

        VBox addButtonHBox = new VBox();
        addButtonHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        addButtonHBox.setPadding(new Insets(0, 150, 0, 150));
        addButtonHBox.setAlignment(Pos.CENTER);
        addButtonHBox.getChildren().add(addButton);

        exitButton = new Button("Cancel");
        exitButton.setStyle("-fx-background-color: #303030; -fx-text-fill: #eeeeee;-fx-font-weight: bold;");
        exitButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        exitButton.setOnAction(actionEvent -> window.close());

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox titleHBox = new HBox();
        titleHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        titleHBox.setPadding(new Insets(0, 0, 15, 0));
        titleHBox.setAlignment(Pos.CENTER);
        titleHBox.getChildren().addAll(imageView,title, spacer, exitButton);

        layout = new VBox(16);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(titleHBox, nameHBox, genreHBox,singerHBox, supplierIDHBox, priceHBox,quantityHBox,
                releaseDateHBox,addButtonHBox);
        layout.setStyle("-fx-background-color: #ffffff;");

        scene = new Scene(layout, 480, 580);

        window.setScene(scene);
        window.showAndWait();
    }

    void addButtonPressed()
    {
        boolean isFieldCorrect = true;

        String name = "";
        String genre = "";
        String singer = "";
        int supplierID = 0;
        double price = 0.0;
        int quantity = 0;
        int day = 0;
        int month = 0;
        int year = 0;

        if(nameTxtField.getText().isEmpty())
        {
            fillNameError.setText("Enter product name!");
            isFieldCorrect = false;
        }
        else
        {
            // Check if this Cd is already in the Cd list on the database
            if(isOnList(nameTxtField.getText()))
            {
                fillNameError.setText("This product already exists in the database!");
                isFieldCorrect = false;
            }
            else
            {
                name = nameTxtField.getText();
                fillNameError.setText("");
            }
        }

        if(singerTxtField.getText().isEmpty())
        {
            fillSingerError.setText("Enter singer name!");
            isFieldCorrect = false;
        }
        else
        {
                singer = singerTxtField.getText();
                fillSingerError.setText("");

        }

        if(genreChBox.getValue().equals("----------"))
        {
            fillGenreError.setText("Select product category!");
            isFieldCorrect = false;
        }
        else
        {
            genre = genreChBox.getValue();
            fillGenreError.setText("");
        }


        if(supplierIDTxtField.getText().isEmpty() || !InputCheck.isInt(supplierIDTxtField.getText()))
        {
            fillSupplIDError.setText("Enter supplier ID!");
            isFieldCorrect = false;
        }
        else
        {
            supplierID = Integer.parseInt(supplierIDTxtField.getText());
            fillSupplIDError.setText("");
        }

        if(priceTxtField.getText().isEmpty() || !InputCheck.isDouble(priceTxtField.getText()))
        {
            fillPriceError.setText("Enter product price!");
            isFieldCorrect = false;
        }
        else
        {
            price = Double.parseDouble(priceTxtField.getText());
            fillPriceError.setText("");
        }



        if(quantityTxtField.getText().isEmpty() || !InputCheck.isInt(quantityTxtField.getText()))
        {
            fillQuantityError.setText("Enter product quantity!");
            isFieldCorrect = false;
        }
        else
        {
            quantity = Integer.parseInt(quantityTxtField.getText());
            fillQuantityError.setText("");
        }

        if(!yearChoiceBox.getValue().equals("----------") && !monthChoiceBox.getValue().equals("----------") && !dayChoiceBox.getValue().equals("----------"))
        {
            year = Integer.parseInt(yearChoiceBox.getValue());
            month = Integer.parseInt(monthChoiceBox.getValue());
            day = Integer.parseInt(dayChoiceBox.getValue());
            releaseDateErrLabel.setText("");
        }
        else
        {
            isFieldCorrect = false;
            releaseDateErrLabel.setText("Enter expiry date!");
        }



        if(isFieldCorrect)
        {
            DataBase.getProductsCD().add(new ProductCD());
            int addedProdIndex = DataBase.getProductsCD().size() - 1;
            ProductCD addedProduct = DataBase.getProductsCD().get(addedProdIndex);

            addedProduct.setName(name);
            addedProduct.setGenre(genre);
            addedProduct.setSinger(singer);
            addedProduct.setSupplierID(supplierID);
            addedProduct.setPrice(price);
            addedProduct.setQuantity(quantity);
            addedProduct.setExpiryDate(new Date(day, month, year));


            // Add product to products bought list on database
            DataBase.getBoughtProducts().add(new BoughtProduct());
            int addedBoughtProdIndex = DataBase.getBoughtProducts().size() - 1;
            BoughtProduct addedBoughtProd = DataBase.getBoughtProducts().get(addedBoughtProdIndex);

            addedBoughtProd.setName(addedProduct.getName());
            addedBoughtProd.setSinger(addedProduct.getSinger());
            addedBoughtProd.setID(addedProduct.getID());
            addedBoughtProd.setGenre(addedProduct.getGenre());
            addedBoughtProd.setSupplierID(addedProduct.getSupplierID());
            addedBoughtProd.setPrice(addedProduct.getPrice());
            addedBoughtProd.setQuantity(addedProduct.getQuantity());
            addedBoughtProd.setReleaseDate(addedProduct.getExpiryDate());

            window.close();
        }
    }

    void setFields()
    {

        nameTxtField = new TextField();
        nameTxtField.setPromptText("Name");
        nameTxtField.setStyle("-fx-background-color: #D3D3D3; -fx-text-fill: #303030; -fx-prompt-text-fill: #888888;");
        fillNameError = new Label();
        fillNameError.setStyle("-fx-text-fill: #ff5050;");
        nameHBox = new VBox();
        nameHBox.getChildren().addAll(nameTxtField, fillNameError);

        genreChBox = new ChoiceBox<>();
        genreChBox.getItems().add("----------");
        genreChBox.getItems().addAll(DataBase.getGenre());
        genreChBox.setValue("----------");
        genreChBox.setMaxWidth(Double.MAX_VALUE);
        fillGenreError = new Label();
        fillGenreError.setStyle("-fx-text-fill: #ff5050;");
        genreHBox = new VBox();
        genreHBox.getChildren().addAll(genreChBox, fillGenreError);

        singerTxtField = new TextField();
        singerTxtField.setPromptText("Singer");
        singerTxtField.setStyle("-fx-background-color: #D3D3D3; -fx-text-fill: #303030; -fx-prompt-text-fill: #888888;");
        fillSingerError = new Label();
        fillSingerError.setStyle("-fx-text-fill: #ff5050;");
        singerHBox = new VBox();
        singerHBox.getChildren().addAll(singerTxtField, fillSingerError);

        supplierIDTxtField = new TextField();
        supplierIDTxtField.setPromptText("Supplier ID");
        supplierIDTxtField.setStyle("-fx-background-color: #D3D3D3; -fx-text-fill: #303030; -fx-prompt-text-fill: #888888;");
        fillSupplIDError = new Label();
        fillSupplIDError.setStyle("-fx-text-fill: #ff5050;");
        supplierIDHBox = new VBox();
        supplierIDHBox.getChildren().addAll(supplierIDTxtField, fillSupplIDError);

        priceTxtField = new TextField();
        priceTxtField.setPromptText("Price");
        priceTxtField.setStyle("-fx-background-color: #D3D3D3; -fx-text-fill: #303030; -fx-prompt-text-fill: #888888;");
        fillPriceError = new Label();
        fillPriceError.setStyle("-fx-text-fill: #ff5050;");
        priceHBox = new VBox();
        priceHBox.getChildren().addAll(priceTxtField, fillPriceError);


        quantityTxtField = new TextField();
        quantityTxtField.setPromptText("Quantity");
        quantityTxtField.setStyle("-fx-background-color: #D3D3D3; -fx-text-fill: #303030; -fx-prompt-text-fill: #888888;");
        fillQuantityError = new Label();
        fillQuantityError.setStyle("-fx-text-fill: #ff5050;");
        quantityHBox = new VBox();
        quantityHBox.getChildren().addAll(quantityTxtField, fillQuantityError);

        yearLabel = new Label("Year");
        yearChoiceBox = new ChoiceBox<>();
        yearChoiceBox.getItems().add("----------");
        addYearsInYearChBox();
        yearChoiceBox.setValue("----------");
        monthLabel = new Label("Month");
        monthChoiceBox = new ChoiceBox<>();
        monthChoiceBox.getItems().add("----------");
        addMonthsInMonthChBox();
        monthChoiceBox.setValue("----------");
        dayLabel = new Label("Day");
        dayChoiceBox = new ChoiceBox<>();
        dayChoiceBox.getItems().add("----------");
        dayChoiceBox.setValue("----------");
        releaseDateErrLabel = new Label("");
        releaseDateErrLabel.setStyle("-fx-text-fill: #ff5050;");

        yearVBox = new VBox(5);
        yearVBox.getChildren().addAll(yearChoiceBox, yearLabel);
        monthVBok = new VBox(5);
        monthVBok.getChildren().addAll(monthChoiceBox, monthLabel);
        dayVBox = new VBox(5);
        dayVBox.getChildren().addAll(dayChoiceBox, dayLabel);
        releaseDateHBox = new HBox(10);
        releaseDateHBox.getChildren().addAll(yearVBox, monthVBok, dayVBox, releaseDateErrLabel);

    }

    private boolean isOnList(String text)
    {
        String name = text.toLowerCase();

        for(ProductCD p : DataBase.getProductsCD())
        {
            String prodInListName = p.getName().toLowerCase();

            if(name.equals(prodInListName))
                return true;
        }

        return false;
    }

    void addDaysInDayChBox()
    {
        dayChoiceBox.getItems().clear();
        dayChoiceBox.getItems().add("----------");
        dayChoiceBox.setValue("----------");

        int month = 0;
        int year = 0;
        int max = 0;

        if(!monthChoiceBox.getValue().equals("----------")) month = Integer.parseInt(monthChoiceBox.getValue());
        if(!yearChoiceBox.getValue().equals("----------")) year = Integer.parseInt(yearChoiceBox.getValue());

        if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) max = 31;
        if(month == 4 || month == 6 || month == 9 || month == 11) max = 30;

        if(month == 2)
        {
            max = 28;

            for(int i = 2010; i <= 2022; i += 4)
            {
                if(year == i)
                {
                    max = 29;
                    break;
                }

                if(i > year) break;
            }
        }

        if(year == 0) max = 0;

        for(int i = 1; i <= max; i++)
            dayChoiceBox.getItems().add(Integer.toString(i));
    }

    void addMonthsInMonthChBox()
    {
        for(int i = 1; i <= 12; i++)
            monthChoiceBox.getItems().add(Integer.toString(i));
    }

    void addYearsInYearChBox()
    {
        for(int i = 2022; i >= 2010; i--)
            yearChoiceBox.getItems().add(Integer.toString(i));
    }
}

