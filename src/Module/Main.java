package Module;

import GUI.Controller;
import javafx.application.Application;


public class Main {

    public static void main(String[] args) {

        DataBase.putDataInLists();
        Application.launch(Controller.class,args);
    }
}
