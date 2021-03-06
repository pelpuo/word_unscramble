package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LevelsController {

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    private String user;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }


    public void toLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toHighScores(ActionEvent event) throws IOException, SQLException {
//        Parent root = FXMLLoader.load(getClass().getResource("highScores.fxml"));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("highScores.fxml"));
        root = loader.load();

        HighScoresController highScoresController = loader.getController();
        highScoresController.displayScores();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toGamePage(ActionEvent event) throws IOException, SQLException {
//        Parent root = FXMLLoader.load(getClass().getResource("gamePage.fxml"));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("gamepage.fxml"));
        root = loader.load();

        GamePageController gamePageController = loader.getController();

        gamePageController.setUser(this.user);
        gamePageController.showUser(this.user);

        if(event.getSource().toString().contains("easy")){
            gamePageController.setLevel(1);
        }else if(event.getSource().toString().contains("medium")){
            gamePageController.setLevel(2);
        }else if(event.getSource().toString().contains("hard")){
            gamePageController.setLevel(3);
        }else if(event.getSource().toString().contains("sage")){
            gamePageController.setLevel(4);
        }else if(event.getSource().toString().contains("veteran")){
            gamePageController.setLevel(5);
        }

        gamePageController.generateWords();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }




}
