package esprit.tn.sae.guessit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import esprit.tn.sae.guessit.database.MyDatabase;
import esprit.tn.sae.guessit.entities.Game;
import esprit.tn.sae.guessit.entities.Player;
import esprit.tn.sae.guessit.entities.Word;

public class GameMainActivity extends AppCompatActivity {

    Button startMenuButton;

    int wordsCountIndex = 1;
    Integer wordsCount = 0;

    int roundsIndex = 1;
    Integer roundsPerWord = 0;

    TextView wordsCountTextView;
    Button nextWordButton;
    TextView roundsPerWordTextView;
    Button nextRoundButton;

    MyDatabase db;
    List<Word> words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_main);

        wordsCountTextView = findViewById(R.id.wordsCountTextView);
        nextWordButton = findViewById(R.id.nextWordButton);
        roundsPerWordTextView = findViewById(R.id.roundsPerWordTextView);
        nextRoundButton = findViewById(R.id.nextRoundButton);

        db = MyDatabase.getDatabase(this);
        words = (List<Word>) db.wordDAO();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String wordsCount = extras.getString("wordsCount");
            this.wordsCount = Integer.parseInt(wordsCount);

            String roundsPerWord = extras.getString("roundsPerWord");
            this.roundsPerWord = Integer.parseInt(roundsPerWord);

            words = this.randomizeWords();
        }

        this.setWordsCountText();
        this.setRoundsPerWordTex();

        nextWordButton.setOnClickListener(view -> {
            if (wordsCountIndex < wordsCount)
                wordsCountIndex++;
            this.setWordsCountText();
        });

        nextRoundButton.setOnClickListener(view -> {
            if (roundsIndex < roundsPerWord)
                roundsIndex++;
            this.setRoundsPerWordTex();
        });
    }

    private void setWordsCountText() {
        wordsCountTextView.setText("Words (" + wordsCountIndex + " / " + wordsCount +  ")");
    }
    private void setRoundsPerWordTex() {
        roundsPerWordTextView.setText("Rounds (" + roundsIndex + " / " + roundsPerWord +  ")");
    }

    private ArrayList<Word> randomizeWords() {
        ArrayList<Word> randomWords = new ArrayList<>();

        ArrayList<Integer> randomInts = new ArrayList<Integer>();
        for(int i = 0; i < wordsCount; i++) {
            Random random = new Random();
            Integer randomInt = random.nextInt(wordsCount);
            while (randomInts.contains(randomInt)) {
                randomInt = random.nextInt(wordsCount);
            }
        }

        for(Integer randInt: randomInts) {
            randomWords.add(words.get(randInt));
        }

        return randomWords;
    }
}