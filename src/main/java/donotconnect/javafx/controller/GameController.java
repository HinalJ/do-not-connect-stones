package donotconnect.javafx.controller;

import donotconnect.results.creatingResult;
import donotconnect.state.DoNotConnectState;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

public class GameController {

    private FXMLLoader fxmlLoader = new FXMLLoader();

    private String winnerName;
    private String player1;
    private String player2;
    private DoNotConnectState gameState;
    private IntegerProperty steps1 = new SimpleIntegerProperty();
    private IntegerProperty steps2 = new SimpleIntegerProperty();
    private List<Image> gemImages;
    private int flag = 0;

    @FXML
    private Label messageLabel;

    @FXML
    private GridPane gameGrid;

    @FXML
    private Button resetButton;

    @FXML
    private Button giveUpButton;

    private boolean isGameFinished;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);

    private BooleanProperty gameOver = new SimpleBooleanProperty();

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }
    public void setPlayer2(String player2) {
        this.player2 = player2;
    }


    @FXML
    public void initialize() {
        gemImages = List.of(
                new Image(getClass().getResource("/images/red.png").toExternalForm()),
                new Image(getClass().getResource("/images/blue.png").toExternalForm())
                );
        gameOver.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                createGameResult();
            }
        });
        resetGame();
    }

    private void resetGame() {
        gameState = new DoNotConnectState();
        steps1.set(0);
        steps2.set(0);
        flag = 0;
        gameOver.setValue(false);
        displayGameState();
        Platform.runLater(() -> messageLabel.setText("Good luck,  " + player1 + " and " + player2 + "!"));
    }

    private void displayGameState() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                ImageView view = (ImageView) gameGrid.getChildren().get(i * 5 + j);
                if(gameState.getBoard()[i][j] == 'o')
                {
                    view.setImage(gemImages.get(0));
                }
                else if(gameState.getBoard()[i][j] == 'x')
                {
                    view.setImage(gemImages.get(1));
                }
                else
                    view.setImage(null);
            }
        }
    }

    public void handleClickOnCell(MouseEvent mouseEvent) {
        int row = GridPane.getRowIndex((Node) mouseEvent.getSource());
        int col = GridPane.getColumnIndex((Node) mouseEvent.getSource());
        if (gameState.isValidMove(row, col)) {

            if(flag == 0) {
                steps1.set(steps1.get() + 1);
                gameState.draw(row,col,'o');
                flag = 1;
            }
            else {
                steps2.set(steps2.get() + 1);
                gameState.draw(row, col, 'x');
                flag = 0;
            }

            if (gameState.isFinished(row,col)) {
                isGameFinished = true;

                if(flag==0)
                    winnerName = player1;
                else
                    winnerName = player2;

                gameOver.setValue(true);

                messageLabel.setText("Congratulations,  " + winnerName + "!");

                resetButton.setDisable(true);
                giveUpButton.setText("Finish");
            }

            if(gameState.isAllFilled()){
                gameOver.setValue(true);
                messageLabel.setText("Match Tied!!!");
                resetButton.setText("Rematch");
                giveUpButton.setText("EXIT");
            }

        }
        displayGameState();
    }

    public void handleResetButton()  {
        resetGame();
    }

    public void handleGiveUpButton(ActionEvent actionEvent) throws IOException {
        gameOver.setValue(true);
        fxmlLoader.setLocation(getClass().getResource("/fxml/highscores.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void createGameResult() {

        if(isGameFinished)
            new creatingResult().addResultData(winnerName, player1, player2, steps1.get(), steps2.get(), ZonedDateTime.now().format(dateTimeFormatter));
    }
}
