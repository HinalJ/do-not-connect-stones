package donotconnect.results;

import util.jaxb.JAXBHelper;

import javax.xml.bind.JAXBException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
/**
 * Class creating the result of game.
 */
@Slf4j
public class creatingResult {

    /**
     * Indicates the GameResultList class object.
     */
    private GameResultList gameResultsList = new GameResultList();

    /**
     * Constructor creates a new file {@code Players.xml} if not found.
     */
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

    /**
     * Following method add result to file {@code Players.xml} and gameResultsList.
     * @param winnerName Player name who won.
     * @param player1 Name of Player1.
     * @param player2 Name of Player2.
     * @param steps1 Number of Steps made by Player1.
     * @param steps2 Number of Steps made by Player2.
     * @param created Timestamp when the game ended.
     */
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

    /**
     * Getter for gameResultsList.
     * @return Gets the list of the {@link GameResult} class.
     */
    public List<GameResult> getGameResultsList() {
        return gameResultsList.getGameResultList();
    }
}
