package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GamePageController {

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    FlowPane letterBox;
    @FXML
    Label currentUser;
    @FXML
    TextField guess;
    @FXML
    Label points;
    @FXML
    Label guessPrompt;


    private String user;

    private int level;
    private String currentWord;
    private int _points = 0;
    private String site = "https://random-word-api.herokuapp.com/word?number=200";

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    public void showUser(String user){
        currentUser.setText("User: " + this.user);
    }


    public void toLevels(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("levels.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void addLetter(char letter){
        Label K = new Label(Character.toString(letter).toUpperCase());
        K.resize(111, 111);
        K.setTextFill(Paint.valueOf("#ffffff"));
        K.setFont(new Font(32));
        K.setStyle("-fx-background-color: #F1C918; -fx-border-width: 2; -fx-border-color: #125EA6; -fx-font-weight: bold; -fx-min-width: 53; -fx-min-height: 53;");
        K.setAlignment(Pos.CENTER);

        letterBox.getChildren().add(K);
    }

    public void addWord(String word){
        for(int i = 0; i < word.length(); i++){
            addLetter(word.charAt(i));
        }

    }

    public void deleteWord(){
        letterBox.getChildren().removeAll();
    }

    public void validateGuess(){
        String _guess = guess.getText().toLowerCase();

        if(_guess.equals("spoon")){
            _points += 1;
            points.setText("Points: " + _points );
            guess.setText("");
            guessPrompt.setText("");
        }else{
            guessPrompt.setText("Invalid Guess. Try again");
        }

    }


    public void generateWord() throws IOException {
        int wordLength = 0;
        if(this.level == 1)wordLength = 3;
        else if(this.level == 2)wordLength = 4;
        else if(this.level == 3)wordLength = 5;

        URL url = new URL(site);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        in.close();

    }

}
