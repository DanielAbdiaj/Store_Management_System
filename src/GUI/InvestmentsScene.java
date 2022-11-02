package GUI;

import javafx.beans.value.ObservableValue;
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

public class InvestmentsScene extends BoughtProductScene {

    Label productsLabel;
    VBox productsVBox;

    Label totalInvLabel;
    Label amountLabel;
    HBox investmentsHBox;

    Label usersLabel;
    HBox usersHBox;
    VBox usersVBox;

    Label usersSalaryLabel;
    Label usersAmountLabel;
    HBox usersSalaryHBox;

    ObservableList<Cashier> cashiers;
    ObservableList<Manager> managers;
    TableView<Cashier> cashierTable;
    TableView<Manager> managerTable;


    public void showScene() throws FileNotFoundException {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        window = new Stage();
        Image icon = new Image(new FileInputStream("resources/Images/icon.png"));
        window.getIcons().add(icon);

        Image image = new Image(new FileInputStream("resources/Images/investments.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(45);
        imageView.setPreserveRatio(true);


        title = new Label("     Investments");
        title.setStyle("-fx-font: normal bold 14px 'arial'; -fx-text-fill: #303030;");

        productsList = FXCollections.observableArrayList();
        productsList.addAll(DataBase.getBoughtProducts());
        resetStatsOverAPeriod();

        cashiers = FXCollections.observableArrayList();
        cashiers.addAll(DataBase.getCashiers());
        managers = FXCollections.observableArrayList();
        managers.addAll(DataBase.getManager());
        resetUserStatsOverAPeriod();

        instantiateDateFilters();
        DateChoiceBox.setDateChoiceBox(dateFromLabel, yearLabel, yearChoiceBox, monthLabel, monthChoiceBox, dayLabel,
                dayChoiceBox, dateFromErrLabel, yearVBox, monthVBok, dayVBox, dateFromHBox, 2010, 2022);
        DateChoiceBox.setDateChoiceBox(dateToLabel, yearLabel2, yearChoiceBox2, monthLabel2, monthChoiceBox2, dayLabel2,
                dayChoiceBox2, dateToErrLabel, yearVBox2, monthVBok2, dayVBox2, dateToHBox, 2010, 2022);

        monthChoiceBox.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> observable, String oldValue, String newValue) ->
                        DateChoiceBox.addDaysInDayChBox(dayChoiceBox, monthChoiceBox, yearChoiceBox, 2010, 2030));
        yearChoiceBox.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> observable, String oldValue, String newValue) ->
                        DateChoiceBox.addDaysInDayChBox(dayChoiceBox, monthChoiceBox, yearChoiceBox, 2010, 2030));

        monthChoiceBox2.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> observable, String oldValue, String newValue) ->
                        DateChoiceBox.addDaysInDayChBox(dayChoiceBox2, monthChoiceBox2, yearChoiceBox2, 2010, 2030));
        yearChoiceBox2.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> observable, String oldValue, String newValue) ->
                        DateChoiceBox.addDaysInDayChBox(dayChoiceBox2, monthChoiceBox2, yearChoiceBox2, 2010, 2030));

        dateFromHBox.setMaxWidth(Double.MAX_VALUE);
        dateFromHBox.setAlignment(Pos.CENTER_RIGHT);
        dateToHBox.setMaxWidth(Double.MAX_VALUE);
        dateToHBox.setAlignment(Pos.CENTER_RIGHT);

        filterButton = new Button("Filter");
        filterButton.setPrefWidth(68);
        filterButton.setStyle("-fx-background-color: #2050bb; -fx-text-fill: #eeeeee;-fx-font-weight:bold");
        filterButton.setOnAction(actionEvent -> filter());

        dateFilterVBox = new VBox(10);
        dateFilterVBox.setMaxWidth(Double.MAX_VALUE);
        dateFilterVBox.setAlignment(Pos.CENTER_RIGHT);
        dateFilterVBox.setPadding(new Insets(25, 0, 10, 0));
        dateFilterVBox.getChildren().addAll(dateFromHBox, dateToHBox, filterButton);

        setTable();

        productsLabel = new Label("CDs");
        productsLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #303030;-fx-font-weight:bold");
        productsVBox = new VBox(5);
        productsVBox.getChildren().addAll(productsLabel, productTable);

        totalInvLabel = new Label("Product investments:");
        totalInvLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #303030;");
        amountLabel = new Label("0.0");
        amountLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #303030;");
        investmentsHBox = new HBox(5);
        investmentsHBox.getChildren().addAll(totalInvLabel, amountLabel);

        usersLabel = new Label("Users");
        usersLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #303030;-fx-font-weight:bold");
        setCashierTable();
        setManagerTable();

        usersHBox = new HBox(30);
        usersHBox.getChildren().addAll(cashierTable, managerTable);

        usersVBox = new VBox(5);
        usersVBox.getChildren().addAll(usersLabel, usersHBox);

        usersSalaryLabel = new Label("Total users salary:");
        usersSalaryLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #303030;");
        usersAmountLabel = new Label("0.0");
        usersAmountLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #303030;");
        usersSalaryHBox = new HBox(5);
        usersSalaryHBox.getChildren().addAll(usersSalaryLabel, usersAmountLabel);

        closeButton = new Button("Close");
        closeButton.setStyle("-fx-background-color: #8B0000; -fx-text-fill: #eeeeee;-fx-font-weight:bold");
        closeButton.setOnAction(actionEvent -> window.close());

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox titleHBox = new HBox();
        titleHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        titleHBox.setAlignment(Pos.CENTER);
        titleHBox.setPadding(new Insets(5, 0, 5, 0));
        titleHBox.getChildren().addAll(imageView,title, spacer, closeButton);

        layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(titleHBox, dateFilterVBox, productsVBox, investmentsHBox, usersVBox, usersSalaryHBox);
        layout.setStyle("-fx-background-color: #ffffff;");

        scene = new Scene(layout, 680, 620);
        window.setScene(scene);
        window.showAndWait();
    }

    void resetUserStatsOverAPeriod()
    {
        for(Cashier c : cashiers)
            c.resetStatsOverAPeriod();

        for(Manager e : managers)
            e.resetSalaryOverAPeriod();
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
            double productsTotal = 0;
            double cashiersTotal = 0;
            double managersTotal = 0;

            for(BoughtProduct bp : productsList)
            {
                bp.setBoughtOverAPeriod(dayFrom, monthFrom, yearFrom, dayTo, monthTo, yearTo);
                productsTotal += bp.getPriceOverAPeriod();
            }

            for(Cashier c : cashiers)
            {
                c.setSalaryOverAPeriod(monthFrom, yearFrom, monthTo, yearTo);
                cashiersTotal += c.getSalaryOverAPeriod();
            }

            for(Manager e : managers)
            {
                e.setSalaryOverAPeriod(monthFrom, yearFrom, monthTo, yearTo);
                managersTotal += e.getSalaryOverAPeriod();
            }

            double totalUserSalary = cashiersTotal + managersTotal;

            usersAmountLabel.setText(Double.toString(totalUserSalary));
            amountLabel.setText(Double.toString(productsTotal));

            setTable();
            setCashierTable();
            setManagerTable();
            layout.getChildren().set(2, productTable);
            usersHBox.getChildren().set(0, cashierTable);
            usersHBox.getChildren().set(1, managerTable);
        }
    }

    @Override
    void setTable()
    {
        TableColumn<BoughtProduct, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<BoughtProduct, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setMinWidth(80);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<BoughtProduct, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(40);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("boughtOverAPeriod"));

        TableColumn<BoughtProduct, Double> totalPriceColumn = new TableColumn<>("Total price");
        totalPriceColumn.setMinWidth(80);
        totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("priceOverAPeriod"));

        productTable = new TableView<>();
        productTable.setItems(productsList);
        productTable.getColumns().addAll(nameColumn, priceColumn, quantityColumn, totalPriceColumn);
    }

    void setCashierTable()
    {
        TableColumn<Cashier, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Cashier, Double> salaryPerMonthColumn = new TableColumn<>("Salary/month");
        salaryPerMonthColumn.setMinWidth(100);
        salaryPerMonthColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));

        TableColumn<Cashier, Double> salaryColumn = new TableColumn<>("Total salary");
        salaryColumn.setMinWidth(100);
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salaryOverAPeriod"));

        cashierTable = new TableView<>();
        cashierTable.setItems(cashiers);
        cashierTable.getColumns().addAll(nameColumn, salaryPerMonthColumn, salaryColumn);
    }

    void setManagerTable()
    {
        TableColumn<Manager, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Manager, Double> salaryPerMonthColumn = new TableColumn<>("Salary/month");
        salaryPerMonthColumn.setMinWidth(100);
        salaryPerMonthColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));

        TableColumn<Manager, Double> salaryColumn = new TableColumn<>("Total salary");
        salaryColumn.setMinWidth(100);
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salaryOverAPeriod"));

        managerTable = new TableView<>();
        managerTable.setItems(managers);
        managerTable.getColumns().addAll(nameColumn, salaryPerMonthColumn, salaryColumn);
    }
}
