// CHECKSTYLE:OFF
package donotconnect.javafx.controller;

import donotconnect.results.GameResult;
import donotconnect.results.GameResultList;
import donotconnect.results.creatingResult;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class HighScoreController{

    private FXMLLoader fxmlLoader = new FXMLLoader();

    @FXML
    private TableView<GameResultList> highScoreTable;

    @FXML
    private TableColumn<GameResultList, String> player;

    @FXML
    private TableColumn<GameResultList, Integer> count;

    @FXML
    private TableColumn<GameResultList, String> lastWin;


    @FXML
    private void initialize() {
        log.debug("Loading high scores...");
        List<GameResultList> highScoreList = getTop5Players();

        player.setCellValueFactory(new PropertyValueFactory<>("player"));
        count.setCellValueFactory(new PropertyValueFactory<>("count"));
        lastWin.setCellValueFactory(new PropertyValueFactory<>("lastWin"));

        ObservableList<GameResultList> observableResult = FXCollections.observableArrayList(highScoreList);

        highScoreTable.setItems(observableResult);
    }

    public void handleRestartButton(ActionEvent actionEvent) throws IOException {
        log.debug("{} is pressed", ((Button) actionEvent.getSource()).getText());
        log.info("Loading launch scene...");
        fxmlLoader.setLocation(getClass().getResource("/fxml/launch.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public List<GameResultList> getTop5Players(){
        List<GameResult> gameResultsList = new creatingResult().getGameResultsList();
        List<GameResultList> display = new ArrayList<>();

        String player;
        String dateTime;
        boolean flag;

        for (GameResult gameResult: gameResultsList) {

            player = gameResult.getWinnerName();
            dateTime = gameResult.getCreated();
            flag = false;

            if (display.size() == 0) {
                display.add(new GameResultList(player, 1, dateTime));
                flag = true;
            }
            else
                for (int i = 0; i < display.size(); i++)
                    if (gameResult.getWinnerName().equals(display.get(i).getPlayer())) {
                        display.get(i).setCount(display.get(i).getCount() + 1);
                        display.get(i).setLastWin(dateTime);
                        flag = true;
                        break;
                    }

            if (!flag)
                display.add(new GameResultList(player, 1, dateTime));
        }

        Collections.sort(display);

        display = display.stream().limit(5).collect(Collectors.toList());

        return display;
    }
}