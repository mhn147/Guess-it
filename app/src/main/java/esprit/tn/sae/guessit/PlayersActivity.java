package esprit.tn.sae.guessit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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

        _setPlayersListAdapter();

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

            _updateRecyclerView();

            editTextPlayerName.setText("");

            LocalBroadcastManager.getInstance(this).registerReceiver(playerDeletedReceiver, new IntentFilter("player-deleted"));
        });
    }

    public BroadcastReceiver playerDeletedReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int playerId = intent.getIntExtra("player-deleted", -1);
            if (playerId > 0) {
                db.playerDAO().deleteById(playerId);
                players = db.playerDAO().get();
            }
            _updateRecyclerView();
        }
    };

    private void _setPlayersListAdapter() {
        PlayersRecyclerAdapter adapter = new PlayersRecyclerAdapter(players);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void _updateRecyclerView() {
        PlayersRecyclerAdapter adapter = new PlayersRecyclerAdapter(players);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.invalidate();
    }
}