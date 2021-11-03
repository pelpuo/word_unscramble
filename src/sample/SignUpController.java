package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class SignUpController {

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    TextField username;
    @FXML
    PasswordField password;
    @FXML
    PasswordField confirmPassword;
    @FXML
    Label signUpPrompt;

    public void toLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void signUp(ActionEvent event) throws IOException, SQLException {
        String _username = username.getText();
        String _password = password.getText();
        String _confirmPassword = confirmPassword.getText();

        Connection connection = DbConnection.getInstance().getConnection();

        Statement statement = connection.createStatement();
//        ResultSet resultSet = statement.executeQuery("" );

        if(!_password.equals(_confirmPassword)){
            signUpPrompt.setText("Passwords do not match");
        }else{

            ResultSet resultSet = statement.executeQuery("Select * from users where username='"+_username+"'" );

            if(resultSet.next()){
                signUpPrompt.setText("User already exists");
            }else{

                int addingUserStatus = statement.executeUpdate("Insert into users(username,password) values('" +
                        _username + "','" + _password + "')");

                if(addingUserStatus > 0){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Word Unscramble");
                    alert.setHeaderText("Sign Up Status");
                    alert.setContentText("Sign up was successful. Proceed to Login");
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    }

                }



            }

        }



        connection.close();

    }

}
