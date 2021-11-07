package esprit.tn.sae.guessit.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Player {
    @PrimaryKey(autoGenerate = true)
    private int playerId;
    @ColumnInfo
    private String name;
    @Ignore
    private boolean guessHolder;
    @Ignore
    private int score = 1;
    @Ignore
    private List<PlayerBonus> bonuses = new ArrayList<PlayerBonus>();

    public Player() {
        bonuses.add(new PlayerBonus(Bonus.DoubleScore));
        bonuses.add(new PlayerBonus(Bonus.AskTwice));
        bonuses.add(new PlayerBonus(Bonus.BlockNext));
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGuessHolder() {
        return guessHolder;
    }

    public void setGuessHolder(boolean guessHolder) {
        this.guessHolder = guessHolder;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<PlayerBonus> getBonuses() {
        return bonuses;
    }

    public void setBonuses(List<PlayerBonus> bonuses) {
        this.bonuses = bonuses;
    }
}
