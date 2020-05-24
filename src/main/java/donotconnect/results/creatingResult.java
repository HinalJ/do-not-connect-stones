package donotconnect.results;

import util.jaxb.JAXBHelper;

import javax.xml.bind.JAXBException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class creatingResult {

    private GameResultList gameResultsList = new GameResultList();

    public creatingResult() {
        try {
            this.gameResultsList = JAXBHelper.fromXML(GameResultList.class, new FileInputStream("Players.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
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
            e.printStackTrace();
        }
    }

    public List<GameResult> getGameResultsList() {
        return gameResultsList.getGameResultList();
    }
}
