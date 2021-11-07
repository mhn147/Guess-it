package esprit.tn.sae.guessit.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import esprit.tn.sae.guessit.entities.Player;

@Dao
public interface PlayerDAO {
    @Insert
    void add(Player player);

    @Delete
    void delete(Player player);

    @Query("SELECT * FROM player")
    List<Player> get();
}
