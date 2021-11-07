package esprit.tn.sae.guessit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import esprit.tn.sae.guessit.database.MyDatabase;
import esprit.tn.sae.guessit.entities.Player;

public class PlayersActivity extends AppCompatActivity {

    Button addNewPlayerButton;
    Button startMenuButton;
    EditText editTextPlayerName;
    RecyclerView recyclerView;

    MyDatabase db;
    List<Player> players;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

        startMenuButton = findViewById(R.id.startMenuButton);
        addNewPlayerButton = findViewById(R.id.addNewPlayerButton);
        editTextPlayerName = findViewById(R.id.editTextPlayerName);
        recyclerView = findViewById(R.id.playersListRecyclerView);

        db = MyDatabase.getDatabase(this);
        players = db.playerDAO().get();

        setPlayersListAdapter();

        startMenuButton.setOnClickListener(view -> {
            finish();
        });

        addNewPlayerButton.setOnClickListener(view -> {
            if (editTextPlayerName.getText() == null
                    || editTextPlayerName.getText().toString().trim().isEmpty())
                return;

            Player player = new Player();
            player.setName(editTextPlayerName.getText().toString());

            db.playerDAO().add(player);
            players = db.playerDAO().get();

            updateRecyclerView();

            editTextPlayerName.setText("");
        });
    }

    private void setPlayersListAdapter() {
        PlayersRecyclerAdapter adapter = new PlayersRecyclerAdapter(players);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void updateRecyclerView() {
        PlayersRecyclerAdapter adapter = new PlayersRecyclerAdapter(players);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.invalidate();
    }
}