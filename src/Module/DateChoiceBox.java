package Module;

import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DateChoiceBox {

    public static void setDateChoiceBox(Label dateLabel, Label yearLabel, ChoiceBox<String> yearChoiceBox, Label monthLabel,
                                        ChoiceBox<String> monthChoiceBox, Label dayLabel, ChoiceBox<String> dayChoiceBox, Label dateErrLabel,
                                        VBox yearVBox, VBox monthVBok, VBox dayVBox, HBox dateHBox, int minYear, int maxYear)
    {

        dateLabel.setMaxHeight(Double.MAX_VALUE);
        dateLabel.setAlignment(Pos.TOP_CENTER);

        yearChoiceBox.getItems().add("----------");
        addYearsInYearChBox(yearChoiceBox, minYear, maxYear);
        yearChoiceBox.setValue("----------");

        monthChoiceBox.getItems().add("----------");
        addMonthsInMonthChBox(monthChoiceBox);
        monthChoiceBox.setValue("----------");

        dayChoiceBox.getItems().add("----------");
        dayChoiceBox.setValue("----------");

        dateErrLabel.setStyle("-fx-text-fill: #ff5050;");
        dateErrLabel.setMaxHeight(Double.MAX_VALUE);
        dateErrLabel.setAlignment(Pos.TOP_CENTER);

        yearLabel.setMaxWidth(Double.MAX_VALUE);
        yearLabel.setAlignment(Pos.CENTER);
        monthLabel.setMaxWidth(Double.MAX_VALUE);
        monthLabel.setAlignment(Pos.CENTER);
        dayLabel.setMaxWidth(Double.MAX_VALUE);
        dayLabel.setAlignment(Pos.CENTER);



        yearVBox.getChildren().addAll(yearChoiceBox, yearLabel);
        monthVBok.getChildren().addAll(monthChoiceBox, monthLabel);
        dayVBox.getChildren().addAll(dayChoiceBox, dayLabel);

        dateHBox.getChildren().addAll(dateErrLabel, dateLabel, yearVBox, monthVBok, dayVBox);
    }


    //Days

    public static void addDaysInDayChBox(ChoiceBox<String> dayChoiceBox, ChoiceBox<String> monthChoiceBox,
                                         ChoiceBox<String> yearChoiceBox, int minYear, int maxYear)
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

            for(int i = maxYear; i >= minYear; i -= 4)
            {
                if(year == i)
                {
                    max = 29;
                    break;
                }

                if(i < year) break;
            }
        }

        if(year == 0) max = 0;

        for(int i = 1; i <= max; i++)
            dayChoiceBox.getItems().add(Integer.toString(i));
    }


    //Months

    public static void addMonthsInMonthChBox(ChoiceBox<String> monthChoiceBox)
    {
        for(int i = 1; i <= 12; i++)
            monthChoiceBox.getItems().add(Integer.toString(i));//Adds all 12 months from integer to string
    }


    //Years

    public static void addYearsInYearChBox(ChoiceBox<String> yearChoiceBox, int minYear, int maxYear)
    {
        for(int i = maxYear; i >= minYear; i--)
            yearChoiceBox.getItems().add(Integer.toString(i));//Adds all years from min to max
    }

}
