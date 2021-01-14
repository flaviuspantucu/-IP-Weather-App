package ro.mta.se.lab;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ro.mta.se.lab.controller.WeatherController;
import java.io.IOException;
/**
 * Class implementing the Main part of the application
 *
 * @author Panțucu Flavius-Marian
 */
public class MainApp extends Application {

    public static void main(String[] args)
    {
        launch(args);
    }

    public void start(Stage primaryStage) {
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(this.getClass().getResource("/view/WeatherInterface.fxml"));
            loader.setController(new WeatherController("src/main/resources/infile.txt"));
            primaryStage.setScene(new Scene(loader.load()));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}