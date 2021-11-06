package esprit.tn.sae.guessit.entities;

public class PlayerBonus {
    private Bonus bonus;
    private boolean isUsed;

    public PlayerBonus(Bonus bonus) {
        this.bonus = bonus;
        this.isUsed = false;
    }

    public Bonus getBonus() {
        return bonus;
    }

    public void setBonus(Bonus bonus) {
        this.bonus = bonus;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }
}
