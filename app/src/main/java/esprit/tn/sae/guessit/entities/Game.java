package esprit.tn.sae.guessit.entities;

import java.util.List;

public class Game {
    private int id;
    private List<Player> players;
    private List<Word> wordsToGuessNumber;
    private int roundsPerWord;

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
