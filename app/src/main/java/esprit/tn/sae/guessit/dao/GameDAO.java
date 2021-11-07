package esprit.tn.sae.guessit.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import esprit.tn.sae.guessit.entities.Game;
import esprit.tn.sae.guessit.entities.Player;

@Dao
public interface GameDAO {
    @Insert
    void add(Game game);

    @Query("SELECT * FROM game")
    List<Game> get();

//    @Query("SELECT * FROM player WHERE player.playerId = player_game.playerId AND player_game.gameId = game.gameId")
//    List<Player> getPlayers();
}
