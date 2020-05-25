package donotconnect.results;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Class representing the database of a game played by two players.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class GameResult {

    /**
     * Indicates the name of the player who won.
     */
    private String winnerName;

    /**
     * Indicates the name of the player1.
     */
    private String player1;

    /**
     * Indicates the name of the player2.
     */
    private String player2;

    /**
     * Indicates the number of steps made by player1.
     */
    private int steps1;

    /**
     * Indicates the number of steps made by player2.
     */
    private int steps2;

    /**
     * The timestamp when the result was saved.
     */
    private String created;

}

