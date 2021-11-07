package esprit.tn.sae.guessit.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import esprit.tn.sae.guessit.dao.GameDAO;
import esprit.tn.sae.guessit.dao.PlayerDAO;
import esprit.tn.sae.guessit.dao.WordDAO;
import esprit.tn.sae.guessit.entities.Game;
import esprit.tn.sae.guessit.entities.Player;
import esprit.tn.sae.guessit.entities.Word;

@Database(entities = {Game.class, Player.class, Word.class}, version = 1, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {

    private static MyDatabase instance;

    public abstract GameDAO gameDAO();
    public abstract PlayerDAO playerDAO();
    public abstract WordDAO wordDAO();

    public static MyDatabase getDatabase(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), MyDatabase.class, "my_db")
                    .allowMainThreadQueries()
                    .build();
        }

        return instance;
    }
}
