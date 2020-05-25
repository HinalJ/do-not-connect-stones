package donotconnect.results;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Class representing the high-score table of the game.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@XmlRootElement(name = "position")
@XmlAccessorType(XmlAccessType.FIELD)
public class GameResultList implements Comparable<GameResultList>{

    @XmlElement(name = "players")
    private List<GameResult> gameResultList;

    /**
     * Indicates the name of the player who won.
     */
    private String player;

    /**
     * Indicates the number of games won by that player.
     */
    private int count = 0;

    /**
     * Indicates the timestamp when that player won most recent game.
     */
    private String lastWin;

    /**
     *
     * @param player The player who won the game.
     * @param count Number of game player won.
     * @param lastWin Most recent game that player won.
     */
    public GameResultList(String player, int count, String lastWin) {
        this.player = player;
        this.count = count;
        this.lastWin = lastWin;
    }

    /**
     *
     * @param display It is a GameResultList object.
     * @return Descending order of the GameResultList depending
     * on their number of wins.
     */
    @Override
    public int compareTo(GameResultList display) {
        return -1*Integer.compare(this.count, display.getCount());
    }
}
