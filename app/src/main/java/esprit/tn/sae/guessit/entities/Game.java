package esprit.tn.sae.guessit.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class Game {
    @PrimaryKey(autoGenerate = true)
    private int gameId;
    @ColumnInfo
    private int roundsPerWord;
    @ColumnInfo
    private String winnerName;
    @Ignore
    private List<Player> players;

    public Game() {

    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getRoundsPerWord() {
        return roundsPerWord;
    }

    public void setRoundsPerWord(int roundsPerWord) {
        this.roundsPerWord = roundsPerWord;
    }

    public String getWinnerName() {
        return winnerName;
    }

    public void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
