package esprit.tn.sae.guessit.entities;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int id;
    private String name;
    private boolean guessHolder;
    private int score;
    private List<PlayerBonus> bonuses;

    public Player() {
        bonuses.add(new PlayerBonus(Bonus.DoubleScore));
        bonuses.add(new PlayerBonus(Bonus.AskTwice));
        bonuses.add(new PlayerBonus(Bonus.BlockNext));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
