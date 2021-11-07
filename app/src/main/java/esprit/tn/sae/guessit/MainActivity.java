package esprit.tn.sae.guessit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

import esprit.tn.sae.guessit.database.MyDatabase;
import esprit.tn.sae.guessit.entities.Word;

public class MainActivity extends AppCompatActivity {

    Button playersBtn;
    Button quitButton;
    Button startGameButton;

    MyDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playersBtn = findViewById(R.id.playersBtn);
        quitButton = findViewById(R.id.quitButton);
        startGameButton = findViewById(R.id.newGameBtn);

        db = MyDatabase.getDatabase(this);
        // TMP IN DEV
        db.wordDAO().deleteAll();
        // END TMP
        _populateWords();

        playersBtn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, PlayersActivity.class);
            startActivity(intent);
        });

        startGameButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CreateGameActivity.class);
            startActivity(intent);
        });

        quitButton.setOnClickListener(view -> {
            this.finish();
        });
    }

    private void _populateWords() {
        ArrayList<Word> words = (ArrayList<Word>) db.wordDAO().get();
        
        if (words.size() == 0) {
            ArrayList<String> wordsStrings = new ArrayList<>();

            wordsStrings.add("Home");
            wordsStrings.add("Food");
            wordsStrings.add("School");
            wordsStrings.add("Movie");
            wordsStrings.add("Application");
            wordsStrings.add("Phone");
            wordsStrings.add("Ring");
            wordsStrings.add("Bag");
            wordsStrings.add("Computer");
            wordsStrings.add("Bird");
            wordsStrings.add("Coffee");
            wordsStrings.add("Candidate");
            wordsStrings.add("Elephant");
            wordsStrings.add("Moon");
            wordsStrings.add("Person");
            wordsStrings.add("Life");
            wordsStrings.add("Hand");
            wordsStrings.add("Eye");
            wordsStrings.add("Woman");
            wordsStrings.add("Work");
            wordsStrings.add("Company");
            wordsStrings.add("Problem");
            wordsStrings.add("Fact");
            wordsStrings.add("Number");
            wordsStrings.add("Money");
            wordsStrings.add("Tomorrow");

            for (String word: wordsStrings) {
                db.wordDAO().add(new Word(word));
            }
        }
    }
}