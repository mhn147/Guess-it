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
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import esprit.tn.sae.guessit.database.MyDatabase;
import esprit.tn.sae.guessit.entities.Player;
import esprit.tn.sae.guessit.entities.Word;

public class  GameMainActivity extends AppCompatActivity {

    Button startMenuButton;

    int wordsCountIndex = 1;
    Integer wordsCount = 0;

    int roundsIndex = 1;
    Integer roundsPerWord = 0;

    TextView wordsCountTextView;
    Button nextWordButton;
    TextView roundsPerWordTextView;
    Button nextRoundButton;
    Button hideWordButton;
    TextView wordTextView;
    RecyclerView recyclerView;
    Button gameStartMenuButton;
    Button endGameButton;

    MyDatabase db;
    List<Word> words;
    ArrayList<Player> players;
    List<InGamePlayer> inGamePlayers;

    private int scoreValue = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_main);

        _setupElements();

        db = MyDatabase.getDatabase(this);
        words = (List<Word>) db.wordDAO().get();
        players = (ArrayList<Player>) db.playerDAO().get();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String wordsCount = extras.getString("wordsCount");
            this.wordsCount = Integer.parseInt(wordsCount);

            String roundsPerWord = extras.getString("roundsPerWord");
            this.roundsPerWord = Integer.parseInt(roundsPerWord);

            words = _randomizeWords();
        }

        _setWordsCountText();
        _setRoundsPerWordText();

        _updateWord();

        inGamePlayers = _updateInGameUsersPlayers();

        _setupMainGamePlayersListAdapter();

        _setupEventListeners();

        LocalBroadcastManager.getInstance(this).registerReceiver(playerScoreIncreaseReceiver, new IntentFilter("player-score-increase"));
    }

    public BroadcastReceiver playerScoreIncreaseReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int playerId = intent.getIntExtra("playerId", -1);
            Player player = players.get(playerId - 1);
            if (player != null) {
                player.setScore(player.getScore() + scoreValue);
            }
            inGamePlayers = _updateInGameUsersPlayers();
            _updateMainGamePlayersListRecyclerView();
        }
    };

    private void _setupElements() {
        wordsCountTextView = findViewById(R.id.wordsCountTextView);
        nextWordButton = findViewById(R.id.nextWordButton);
        roundsPerWordTextView = findViewById(R.id.roundsPerWordTextView);
        nextRoundButton = findViewById(R.id.nextRoundButton);
        hideWordButton = findViewById(R.id.hideWordButton);
        wordTextView = findViewById(R.id.wordTextView);
        recyclerView = findViewById(R.id.mainGamePlayersRecyclerView);
        gameStartMenuButton = findViewById(R.id.gameStartMenuButton);
        endGameButton = findViewById(R.id.endGameButton);
    }

    private void _setupEventListeners() {
        nextWordButton.setOnClickListener(view -> {
            if (wordsCountIndex < wordsCount)
                wordsCountIndex++;
            _setWordsCountText();
            _updateWord();
        });

        nextRoundButton.setOnClickListener(view -> {
            if (roundsIndex < roundsPerWord)
                roundsIndex++;
            _setRoundsPerWordText();
            _updateScoreValue();
        });

        hideWordButton.setOnClickListener(view -> {
            if (this.wordTextView.getVisibility() == View.VISIBLE) {
                this.wordTextView.setVisibility(View.INVISIBLE);
                this.hideWordButton.setText("Show the Word");
            } else {
                this.wordTextView.setVisibility(View.VISIBLE);
                this.hideWordButton.setText("Hide the Word");
            }
        });

        gameStartMenuButton.setOnClickListener(view -> {
            finish();
        });

        endGameButton.setOnClickListener(view -> {
            Intent intent = new Intent(GameMainActivity.this, GameResultActivity.class);

            Player winner = _getWinner();

            intent.putExtra("winner-name", winner.getName());
            intent.putExtra("winner-score", winner.getScore());

            startActivity(intent);
        });
    }

    private void _updateWord() {
        this.wordTextView.setText(words.get(this.wordsCountIndex - 1).getWord());
    }

    private ArrayList<InGamePlayer> _updateInGameUsersPlayers() {
       ArrayList<InGamePlayer> inGamePlayers = new ArrayList<InGamePlayer>();

        for (Player player : this.players) {
            inGamePlayers.add(new InGamePlayer(player.getPlayerId(), player.getName(), player.getScore()));
        }

        return inGamePlayers;
    }

    private void _setWordsCountText() {
        wordsCountTextView.setText("Words (" + wordsCountIndex + " / " + wordsCount +  ")");
    }
    private void _setRoundsPerWordText() {
        roundsPerWordTextView.setText("Rounds (" + roundsIndex + " / " + roundsPerWord +  ")");
    }

    private ArrayList<Word> _randomizeWords() {
        ArrayList<Word> randomWords = new ArrayList<>();

        ArrayList<Integer> randomInts = new ArrayList<Integer>();
        for(int i = 0; i < wordsCount; i++) {
            Random random = new Random();
            Integer randomInt = random.nextInt(words.size());
            while (randomInts.contains(randomInt)) {
                randomInt = random.nextInt(wordsCount);
            }
            randomInts.add(randomInt);
        }

        for(Integer randInt: randomInts) {
            randomWords.add(words.get(randInt));
        }

        return randomWords;
    }

    private void _updateScoreValue() {
        //Score value = roundIndex²
        this.scoreValue = roundsIndex * roundsIndex;
    }

    private void _setupMainGamePlayersListAdapter() {
        MainGamePlayersRecyclerAdapter adapter = new MainGamePlayersRecyclerAdapter(inGamePlayers);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void _updateMainGamePlayersListRecyclerView() {
        MainGamePlayersRecyclerAdapter adapter = new MainGamePlayersRecyclerAdapter(inGamePlayers);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.invalidate();
    }

    private Player _getWinner() {
        Player winner = players.get(0);
        for(int i = 1; i < players.size(); i++) {
            if (players.get(i).getScore() > winner.getScore())
                winner = players.get(i);
        }
        return winner;
    }
}