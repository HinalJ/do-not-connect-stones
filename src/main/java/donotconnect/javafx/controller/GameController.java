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
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

@Slf4j
public class GameController {

    private FXMLLoader fxmlLoader = new FXMLLoader();

    private String winnerName;
    private String player1;
    private String player2;
    private DoNotConnectState gameState;
    private IntegerProperty steps1 = new SimpleIntegerProperty();
    private IntegerProperty steps2 = new SimpleIntegerProperty();
    private IntegerProperty steps = new SimpleIntegerProperty();
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
                log.info("Game is over");
                log.debug("Saving result to database...");
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
                if (view.getImage() != null) {
                    log.trace("Image({}, {}) = {}", i, j, view.getImage().getUrl());
                }
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
        log.debug("Cell ({}, {}) is clicked", row, col);
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

                if(flag==0) {
                    winnerName = player1;
                    steps.setValue(steps1.get());
                }
                else {
                    winnerName = player2;
                    steps.setValue(steps2.get());
                }

                gameOver.setValue(true);
                log.info("Player {} has won the game in {} steps", winnerName, steps.get());
                messageLabel.setText("Congratulations,  " + winnerName + "!");

                resetButton.setDisable(true);
                giveUpButton.setText("Finish");
            }

            if(gameState.isAllFilled()){
                gameOver.setValue(true);
                log.info("The game is draw between Player {} and Player {}.", player1, player2);
                messageLabel.setText("Match Tied!!!");
                resetButton.setText("Rematch");
                giveUpButton.setText("EXIT");
            }

        }
        displayGameState();
    }

    public void handleResetButton(ActionEvent actionEvent)  {
        String buttonText = ((Button) actionEvent.getSource()).getText();
        log.debug("{} is pressed", buttonText);
        if(buttonText.equals("Reset"))
            log.info("Resetting game...");
        else if(buttonText.equals("Rematch"))
            log.info("Rematching the game");
        resetGame();
    }

    public void handleGiveUpButton(ActionEvent actionEvent) throws IOException {
        String buttonText = ((Button) actionEvent.getSource()).getText();
        log.debug("{} is pressed", buttonText);
        if (buttonText.equals("Give Up")) {
            log.info("The game has been given up");
        }
        else if(buttonText.equals("EXIT")){
            log.info("The game is exited");
        }
        gameOver.setValue(true);
        log.info("Displaying top 5 players high score scene...");
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
