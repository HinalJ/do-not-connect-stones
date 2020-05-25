package donotconnect.results;

import util.jaxb.JAXBHelper;

import javax.xml.bind.JAXBException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class creatingResult {

    private GameResultList gameResultsList = new GameResultList();

    public creatingResult() {
        try {
            this.gameResultsList = JAXBHelper.fromXML(GameResultList.class, new FileInputStream("Players.xml"));
        } catch (JAXBException e) {
            log.error("JAXB exception catched {}", e.toString());
        } catch (FileNotFoundException e) {
            log.error("File Not Found!");
            gameResultsList.setGameResultList(new ArrayList<>());
        }
    }


    public void addResultData(String winnerName, String player1, String player2, int steps1, int steps2, String created){

        List<GameResult> gameResults = gameResultsList.getGameResultList();
        gameResults.add(new GameResult(winnerName, player1, player2, steps1, steps2, created));

        gameResultsList.setGameResultList(gameResults);
        try {
            JAXBHelper.toXML(gameResultsList, new FileOutputStream("Players.xml"));
        } catch (JAXBException | FileNotFoundException e) {
            log.error(e.toString());
        }
    }

    public List<GameResult> getGameResultsList() {
        return gameResultsList.getGameResultList();
    }
}
