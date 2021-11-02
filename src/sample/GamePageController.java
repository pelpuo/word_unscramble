package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

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
    @FXML
    Label tries;


    private String user;

    private int level;
    private String currentWord;
    private int _points = 0;

    private int _tries = 3;

    private List<Word> wordList = new ArrayList<Word>();

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
        currentWord = word;

        String shuffledWord = shuffleString(word);

        for(int i = 0; i < shuffledWord.length(); i++){
            addLetter(shuffledWord.charAt(i));
        }

    }

    public void deleteWord(){
        letterBox.getChildren().clear();
    }

    public void validateGuess() throws IOException, SQLException {
        String _guess = guess.getText().toLowerCase();

        if(_guess.equals(currentWord)){
            _points += currentWord.length() - 2;
            points.setText("Points: " + _points );
            guess.setText("");
            guessPrompt.setText("");

            deleteWord();

            Random rand = new Random();

            int wordPos = rand.nextInt(wordList.size());
            addWord(wordList.get(wordPos).getValue());

        }else{
            if(_tries > 0){
                guessPrompt.setText("Invalid Guess. Try again");
                _tries -= 1;
                tries.setText("Tries: "+ _tries);
                guess.setText("");

            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Word Unscramble");
                alert.setHeaderText("Game Over!");
                storeScore();
                alert.setContentText("Well done, your score was " + _points);
                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent() && result.get() == ButtonType.OK) {

                    System.out.println("Ok Clicked");

                    Parent root = FXMLLoader.load(getClass().getResource("levels.fxml"));
                    stage = (Stage)guess.getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
            }
        }

    }

    public void storeScore() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        Statement statement = connection.createStatement();
//        ResultSet resultSet = statement.executeQuery("INSERT INTO scores('username', 'score') VALUES('" + user + "' , '" + _points + "')" );
        int status = statement.executeUpdate("INSERT INTO scores('username', 'score') VALUES('" + user + "' , '" + _points + "')" );

        if(status > 0){
            System.out.println("Score saved");
        }
        connection.close();
    }


    public void generateWords() throws IOException, SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        Statement statement = connection.createStatement();
        ResultSet resultSet = null;

        if(level != 5){
            resultSet = statement.executeQuery("Select * from words where level =" + level );
        }else{
            resultSet = statement.executeQuery("Select * from words");
        }


        while(resultSet.next()) {
            Word word = new Word(resultSet.getString("word"), Integer.parseInt(resultSet.getString("level")));
            wordList.add(word);

            System.out.println(resultSet.getString("word"));
        }

        if(wordList.size() == 0){
//            No words
        }else{
            Random rand = new Random();

            int wordPos = rand.nextInt(wordList.size());
            addWord(wordList.get(wordPos).getValue());
        }

        connection.close();


    }


    public static String shuffleString(String string)
    {
        List<String> letters = Arrays.asList(string.split(""));
        Collections.shuffle(letters);
        String shuffled = "";
        for (String letter : letters) {
            shuffled += letter;
        }
        return shuffled;
    }

    public void Exit(ActionEvent event) throws IOException, SQLException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Word Unscramble");
        alert.setHeaderText("Exiting to Level Select");
        storeScore();
        alert.setContentText("Try again later. Your score was " + _points);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            storeScore();
            Parent root = FXMLLoader.load(getClass().getResource("levels.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }



}
