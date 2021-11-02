package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import javax.swing.plaf.nimbus.State;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginController {

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    TextField username;
    @FXML
    PasswordField password;
    @FXML
    Label errorMessage;

    public void toSignup(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("signUp.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toLevels(ActionEvent event) throws IOException, SQLException {

        String _username = username.getText();
        String _password = password.getText();

        Connection connection = DbConnection.getInstance().getConnection();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("Select * from users where username" + " = '" +
                _username + "' and password" + " = '" + _password + "'" );

        if(resultSet.next()){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("levels.fxml"));
            root = loader.load();

            LevelsController levelsController = loader.getController();
            levelsController.setUser(_username);

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else{
            errorMessage.setText("Invalid Login Credentials");
        }

        connection.close();
    }


}
