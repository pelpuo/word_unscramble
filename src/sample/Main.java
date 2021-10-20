package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");

        Scene primaryScene = new Scene(root, 300, 275, Color.BLACK);

        primaryStage.setScene(primaryScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
