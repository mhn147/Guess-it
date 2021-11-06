package esprit.tn.sae.guessit.entities;

import androidx.room.Entity;

@Entity(primaryKeys = {"gameId", "playerId"}, tableName = "game_player")
public class GamePlayer {
    private int playerId;
    private int songId;

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }
}
