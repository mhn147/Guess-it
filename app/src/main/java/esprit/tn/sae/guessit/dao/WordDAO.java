package esprit.tn.sae.guessit.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import esprit.tn.sae.guessit.entities.Game;
import esprit.tn.sae.guessit.entities.Word;

@Dao
public interface WordDAO {
    @Insert
    void add(Word word);

    @Query("SELECT * FROM word")
    List<Word> get();
}
