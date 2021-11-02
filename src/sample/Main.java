package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private int width = 640;
    private int height = 480;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
//        Group root = new Group();
        primaryStage.setTitle("Word Unscramble");

        Scene primaryScene = new Scene(root);

        String mainCss = getClass().getResource("application.css").toExternalForm();
        primaryScene.getStylesheets().add(mainCss);


        primaryStage.setScene(primaryScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
