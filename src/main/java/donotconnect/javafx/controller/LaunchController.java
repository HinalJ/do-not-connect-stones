package donotconnect.javafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LaunchController {


    private FXMLLoader fxmlLoader = new FXMLLoader();

    @FXML
    private TextField player1;
    @FXML
    private TextField player2;

    @FXML
    private Label errorLabel1;
    @FXML
    private Label errorLabel2;

    public void startAction(ActionEvent actionEvent) throws IOException {
        if (player1.getText().isEmpty() && player2.getText().isEmpty()){
            errorLabel1.setText("Enter Player One's name!");
            errorLabel2.setText("Enter Player Two's name!");
        }
        else if (player2.getText().isEmpty())
            errorLabel2.setText("Enter Player Two's name!");
        else if (player1.getText().isEmpty())
            errorLabel1.setText("Enter Player One's name!");
        else {
            fxmlLoader.setLocation(getClass().getResource("/fxml/game.fxml"));
            Parent root = fxmlLoader.load();
            fxmlLoader.<GameController>getController().setPlayer1(player1.getText());
            fxmlLoader.<GameController>getController().setPlayer2(player2.getText());
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            }
    }

}
