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
        primaryStage.setTitle("Hello World");

        Scene primaryScene = new Scene(root);

        String mainCss = getClass().getResource("application.css").toExternalForm();
        primaryScene.getStylesheets().add(mainCss);

//        Text text = new Text();
//        text.setText("Hello World");
//        text.setX(50);
//        text.setY(50);
//        text.setFont(Font.font("Poppins", 32));
//
//        root.getChildren().add(text);


        primaryStage.setScene(primaryScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
