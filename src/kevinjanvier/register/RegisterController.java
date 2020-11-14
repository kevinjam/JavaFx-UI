package kevinjanvier.register;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import kevinjanvier.db.DatabaseConnection;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    @FXML
    private ImageView shieldImageView;
    @FXML
    private Button closeButton;

    @FXML
    private Label registrationMessageLabel;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Label confirmPasswordLabel;

    @FXML
    private TextField firstnameTextField;
    @FXML
    private TextField lastnameTextField;
    @FXML
    private TextField usernameTextField;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File shieldField = new File("images/signup.png");
        Image shieldImage = new Image(shieldField.toURI().toString());
        shieldImageView.setImage(shieldImage);
    }

    public void closeButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    public void registerButtonOnAction(ActionEvent event) {
        if (passwordField.getText().equals(confirmPasswordField.getText())) {
            confirmPasswordLabel.setText("");
            registerUser();

        } else {
            confirmPasswordLabel.setText("Password does not match");
        }
    }

    public void registerUser() {
        var connectNow = new DatabaseConnection();
        var connectDb = connectNow.getConnection();

        String firstname =firstnameTextField.getText();
        String lastname=lastnameTextField.getText();
        String username=usernameTextField.getText();
        String password=passwordField.getText();

        String insertFields = "INSERT INTO user_account(lastname, firstname,username, password) VALUES('";
        String insertValues =firstname+"','"+lastname+"','"+username+"','"+password+ "')";
        String insertToRegister =insertFields+insertValues;
        try {
            var statement = connectDb.createStatement();
            statement.executeUpdate(insertToRegister);
            registrationMessageLabel.setText("User has registered successfully !");

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }
}
