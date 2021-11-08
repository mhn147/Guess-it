package esprit.tn.sae.guessit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;

public class CreateGameActivity extends AppCompatActivity {

    Button mainMenuBtn;
    Button startBtn;

    EditText wordsNumberInput;
    EditText roundsPerWordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game);

        mainMenuBtn = findViewById(R.id.mainBtn);
        wordsNumberInput = findViewById(R.id.wordsNumberInput);
        roundsPerWordInput = findViewById(R.id.roundsPerWordInput);

        mainMenuBtn.setOnClickListener(view -> {
            finish();
        });

        startBtn = findViewById(R.id.startBtn);

        startBtn.setOnClickListener(view -> {

            String roundsPerWord = roundsPerWordInput.getText().toString();
            String wordsCount = wordsNumberInput.getText().toString();

            if (roundsPerWord.isEmpty() || wordsCount.isEmpty()) return;

            Intent intent = new Intent(CreateGameActivity.this, GameMainActivity.class);

            intent.putExtra("wordsCount", wordsNumberInput.getText().toString());
            intent.putExtra("roundsPerWord", roundsPerWordInput.getText().toString());

            startActivity(intent);
        });
    }

}