package GUI;

import javafx.beans.value.ObservableValue;
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
import javafx.stage.Modality;
import javafx.stage.Stage;


import Module.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CashierStatsScene {

    Stage window;
    Scene scene;
    Label title;
    Button closeButton;
    VBox layout;

    Label dateFromLabel;
    Label yearLabel;
    ChoiceBox<String> yearChoiceBox;
    Label monthLabel;
    ChoiceBox<String> monthChoiceBox;
    Label dayLabel;
    ChoiceBox<String> dayChoiceBox;
    Label dateFromErrLabel;

    VBox yearVBox;
    VBox monthVBok;
    VBox dayVBox;
    HBox dateFromHBox;

    Label dateToLabel;
    Label yearLabel2;
    ChoiceBox<String> yearChoiceBox2;
    Label monthLabel2;
    ChoiceBox<String> monthChoiceBox2;
    Label dayLabel2;
    ChoiceBox<String> dayChoiceBox2;
    Label dateToErrLabel;

    VBox yearVBox2;
    VBox monthVBok2;
    VBox dayVBox2;
    HBox dateToHBox;

    Button filterButton;
    VBox dateFilterVBox;

    TableView<Cashier> cashierStatsTable;
    ObservableList<Cashier> cashiers;

    public void showScene() throws FileNotFoundException {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        window = new Stage();
        Image icon = new Image(new FileInputStream("resources/Images/icon.png"));
        window.getIcons().add(icon);

        Image image = new Image(new FileInputStream("resources/Images/cashier.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(45);
        imageView.setPreserveRatio(true);


        title = new Label("     All Cashiers");
        title.setStyle("-fx-font: normal bold 16px 'arial'; -fx-text-fill: #303030;");

        cashiers = FXCollections.observableArrayList();
        cashiers.addAll(DataBase.getCashiers());
        resetStatsOverAPeriod();

        instantiateDateFilters();
        DateChoiceBox.setDateChoiceBox(dateFromLabel, yearLabel, yearChoiceBox, monthLabel, monthChoiceBox, dayLabel,
                dayChoiceBox, dateFromErrLabel, yearVBox, monthVBok, dayVBox, dateFromHBox, 2010, 2022);
        DateChoiceBox.setDateChoiceBox(dateToLabel, yearLabel2, yearChoiceBox2, monthLabel2, monthChoiceBox2, dayLabel2,
                dayChoiceBox2, dateToErrLabel, yearVBox2, monthVBok2, dayVBox2, dateToHBox, 2010, 2022);

        monthChoiceBox.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> observable, String oldValue, String newValue) ->
                        DateChoiceBox.addDaysInDayChBox(dayChoiceBox, monthChoiceBox, yearChoiceBox, 2010, 2022));
        yearChoiceBox.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> observable, String oldValue, String newValue) ->
                        DateChoiceBox.addDaysInDayChBox(dayChoiceBox, monthChoiceBox, yearChoiceBox, 2010, 2022));

        monthChoiceBox2.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> observable, String oldValue, String newValue) ->
                        DateChoiceBox.addDaysInDayChBox(dayChoiceBox2, monthChoiceBox2, yearChoiceBox2, 2010, 2022));
        yearChoiceBox2.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> observable, String oldValue, String newValue) ->
                        DateChoiceBox.addDaysInDayChBox(dayChoiceBox2, monthChoiceBox2, yearChoiceBox2, 2010, 2020));

        dateFromHBox.setMaxWidth(Double.MAX_VALUE);
        dateFromHBox.setAlignment(Pos.CENTER_RIGHT);
        dateToHBox.setMaxWidth(Double.MAX_VALUE);
        dateToHBox.setAlignment(Pos.CENTER_RIGHT);

        filterButton = new Button("Filter");
        filterButton.setPrefWidth(68);
        filterButton.setStyle("-fx-background-color: #2050bb; -fx-text-fill: #eeeeee;-fx-text-fill: #eeeeee;-fx-font-weight:bold");
        filterButton.setOnAction(actionEvent -> filter());

        dateFilterVBox = new VBox(10);
        dateFilterVBox.setMaxWidth(Double.MAX_VALUE);
        dateFilterVBox.setAlignment(Pos.CENTER_RIGHT);
        dateFilterVBox.setPadding(new Insets(25, 0, 10, 0));
        dateFilterVBox.getChildren().addAll(dateFromHBox, dateToHBox, filterButton);

        setTable();

        closeButton = new Button("Close");
        closeButton.setPrefWidth(68);
        closeButton.setStyle("-fx-background-color: #8B0000; -fx-text-fill: #eeeeee;-fx-font-weight:bold");
        closeButton.setOnAction(actionEvent -> window.close());

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox titleHBox = new HBox();
        titleHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        titleHBox.setAlignment(Pos.CENTER);
        titleHBox.setPadding(new Insets(5, 0, 5, 0));
        titleHBox.getChildren().addAll(imageView,title, spacer, closeButton);

        layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(titleHBox, dateFilterVBox, cashierStatsTable);
        layout.setStyle("-fx-background-color: #F5F5F5;");

        scene = new Scene(layout, 642, 600);
        window.setScene(scene);
        window.showAndWait();
    }

    void resetStatsOverAPeriod()
    {
        for(Cashier c : cashiers)
            c.resetStatsOverAPeriod();
    }

    void filter()
    {
        boolean areFieldsCorrect = true;

        int yearFrom = 0;
        int monthFrom = 0;
        int dayFrom = 0;

        int yearTo = 0;
        int monthTo = 0;
        int dayTo = 0;

        if(!yearChoiceBox.getValue().equals("----------") && !monthChoiceBox.getValue().equals("----------") && !dayChoiceBox.getValue().equals("----------"))
        {
            yearFrom = Integer.parseInt(yearChoiceBox.getValue());
            monthFrom = Integer.parseInt(monthChoiceBox.getValue());
            dayFrom = Integer.parseInt(dayChoiceBox.getValue());
            dateFromErrLabel.setText("");
        }
        else
        {
            areFieldsCorrect = false;
            dateFromErrLabel.setText("Select Date!");
        }

        if(!yearChoiceBox2.getValue().equals("----------") && !monthChoiceBox2.getValue().equals("----------") && !dayChoiceBox2.getValue().equals("----------"))
        {
            yearTo = Integer.parseInt(yearChoiceBox2.getValue());
            monthTo = Integer.parseInt(monthChoiceBox2.getValue());
            dayTo = Integer.parseInt(dayChoiceBox2.getValue());
            dateToErrLabel.setText("");
        }
        else
        {
            areFieldsCorrect = false;
            dateToErrLabel.setText("Select Date!");
        }

        if(areFieldsCorrect)
        {
            for(Cashier c : cashiers)
            {
                c.setBillAndMoneyGenOverAPeriod(dayFrom, monthFrom, yearFrom, dayTo, monthTo, yearTo);
                c.setSoldProdsOverAPeriod(dayFrom, monthFrom, yearFrom, dayTo, monthTo, yearTo);
            }

            setTable();
            layout.getChildren().set(2, cashierStatsTable);
        }
    }

    void setTable()
    {
        TableColumn<Cashier, String> nameColumn = new TableColumn<>("Username");
        nameColumn.setMinWidth(150);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<Cashier, Integer> nrOfBillsColumn = new TableColumn<>("Bills");
        nrOfBillsColumn.setMinWidth(150);
        nrOfBillsColumn.setCellValueFactory(new PropertyValueFactory<>("billOverAPeriod"));

        TableColumn<Cashier, Integer> prodSoldColumn = new TableColumn<>("CDs Sold");
        prodSoldColumn.setMinWidth(150);
        prodSoldColumn.setCellValueFactory(new PropertyValueFactory<>("soldProdsOverAPeriod"));

        TableColumn<Cashier, Double> moneyGenColumn = new TableColumn<>("Money Generated");
        moneyGenColumn.setMinWidth(150);
        moneyGenColumn.setCellValueFactory(new PropertyValueFactory<>("moneyGenOverAPeriod"));

        cashierStatsTable = new TableView<>();
        cashierStatsTable.setItems(cashiers);
        cashierStatsTable.getColumns().addAll(nameColumn, nrOfBillsColumn, prodSoldColumn, moneyGenColumn);
    }

    void instantiateDateFilters()
    {
        dateFromLabel = new Label("From");
        dateFromLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #303030;");
        dateFromErrLabel = new Label("");

        yearLabel = new Label("Year");
        yearLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #303030;");
        yearChoiceBox = new ChoiceBox<>();

        monthLabel = new Label("Month");
        monthLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #303030;");
        monthChoiceBox = new ChoiceBox<>();

        dayLabel = new Label("Day");
        dayLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #303030;");
        dayChoiceBox = new ChoiceBox<>();

        yearVBox = new VBox(5);
        monthVBok = new VBox(5);
        dayVBox = new VBox(5);

        dateFromHBox = new HBox(10);

        //------------------------------------------------------

        dateToLabel = new Label("To");
        dateToLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #303030;");
        dateToErrLabel = new Label("");

        yearLabel2 = new Label("Year");
        yearLabel2.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #303030;");
        yearChoiceBox2 = new ChoiceBox<>();

        monthLabel2 = new Label("Month");
        monthLabel2.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #303030;");
        monthChoiceBox2 = new ChoiceBox<>();

        dayLabel2 = new Label("Day");
        dayLabel2.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #303030;");
        dayChoiceBox2 = new ChoiceBox<>();

        yearVBox2 = new VBox(5);
        monthVBok2 = new VBox(5);
        dayVBox2 = new VBox(5);

        dateToHBox = new HBox(10);
    }
}
