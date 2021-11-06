package esprit.tn.sae.guessit.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.util.List;

@Entity
public class Game {
    @PrimaryKey
    private int id;
    private List<Player> players;
    private List<Word> wordsToGuessNumber;
    @ColumnInfo
    private int roundsPerWord;

    public Game() {

    }

    public Game(List<Player> players, List<Word> wordsToGuessNumber, int roundsPerWord) {
        this.players = players;
        this.wordsToGuessNumber = wordsToGuessNumber;
        this.roundsPerWord = roundsPerWord;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Word> getWordsToGuessNumber() {
        return wordsToGuessNumber;
    }

    public void setWordsToGuessNumber(List<Word> wordsToGuessNumber) {
        this.wordsToGuessNumber = wordsToGuessNumber;
    }

    public int getRoundsPerWord() {
        return roundsPerWord;
    }

    public void setRoundsPerWord(int roundsPerWord) {
        this.roundsPerWord = roundsPerWord;
    }
}
