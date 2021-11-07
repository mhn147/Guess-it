package esprit.tn.sae.guessit;

public class InGamePlayer {
    public int id;
    private String name;
    private Integer currentScore = 1;

    public InGamePlayer(int id, String name, int currentScore) {
        this.id = id;
        this.name = name;
        this.currentScore = currentScore;
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

    public Integer getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(Integer currentScore) {
        this.currentScore = currentScore;
    }
}
