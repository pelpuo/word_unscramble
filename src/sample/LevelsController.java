package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

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



    public void toGamePage(ActionEvent event) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("gamePage.fxml"));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("gamepage.fxml"));
        root = loader.load();

        GamePageController gamePageController = loader.getController();

        gamePageController.setUser(this.user);
        gamePageController.showUser(this.user);
        gamePageController.addWord("spoon");

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

        gamePageController.generateWord();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }




}
