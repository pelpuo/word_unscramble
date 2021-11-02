package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Flow;

public class HighScoresController {
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    FlowPane positions;
    @FXML
    FlowPane names;
    @FXML
    FlowPane scores;

    private List<Score> scoreList = new ArrayList<Score>();

    public void toLevels(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("levels.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void displayScores() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from scores order by score desc limit 10" );

        while(resultSet.next()){
            Score score = new Score(resultSet.getString("username"), Integer.parseInt(resultSet.getString("score")));
            scoreList.add(score);
        }

        connection.close();

        int i = 0;
        for(Score score: scoreList){
            addPosition(i);
            addUsername(score.getUsername());
            addScore(score.getValue());

            i++;
        }
    }

    public void addPosition(int position){
        Label K = new Label(Integer.toString(position));
        K.resize(128, 28);
        K.setTextFill(Paint.valueOf("#ffffff"));
        K.setFont(new Font(16));
        K.setAlignment(Pos.CENTER);
        K.setStyle("-fx-alignment: center;");

        positions.getChildren().add(K);
    }

    public void addUsername(String username){
        Label K = new Label(username);
        K.resize(128, 28);
        K.setTextFill(Paint.valueOf("#ffffff"));
        K.setFont(new Font(16));
        K.setAlignment(Pos.CENTER);
        K.setStyle("-fx-alignment: center;");
        names.getChildren().add(K);
    }

    public void addScore(int score){
        Label K = new Label(Integer.toString(score));
        K.resize(128, 28);
        K.setTextFill(Paint.valueOf("#ffffff"));
        K.setFont(new Font(16));
        K.setAlignment(Pos.CENTER);
        K.setStyle("-fx-alignment: center;");
        scores.getChildren().add(K);
    }
}
