package GUI;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileInputStream;
import Module.DataBase;


public class Controller extends Application {

    static Stage window;

    public void start(Stage primaryStage) throws Exception{


        window=primaryStage;
        Image icon = new Image(new FileInputStream("resources/Images/icon.png"));
        window.getIcons().add(icon);
        window.setTitle(" CDMAX");

        window.setOnCloseRequest(e -> DataBase.save());

        Login.showScene(window);
        window.show();

    }


}
