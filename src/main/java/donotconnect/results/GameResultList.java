package donotconnect.results;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@XmlRootElement(name = "position")
@XmlAccessorType(XmlAccessType.FIELD)

public class GameResultList implements Comparable<GameResultList>{

    @XmlElement(name = "players")
    private List<GameResult> gameResultList;

    private String player;
    private int count = 0;
    private String lastWin;

    public GameResultList(String player, int count, String lastWin) {
        this.player = player;
        this.count = count;
        this.lastWin = lastWin;
    }

    @Override
    public int compareTo(GameResultList display) {
        return -1*Integer.compare(this.count, display.getCount());
    }
}
