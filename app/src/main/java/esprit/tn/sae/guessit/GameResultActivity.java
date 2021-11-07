package esprit.tn.sae.guessit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class GameResultActivity extends AppCompatActivity {

    Button startMenuButton;
    TextView winnerTextView;
    TextView winnerScoreTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_result);

        startMenuButton = findViewById(R.id.gameResultMainMenuButton);
        winnerTextView = findViewById(R.id.winnerTextView);
        winnerScoreTextView = findViewById(R.id.winnerScoreTextView);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String winnerName = extras.getString("winner-name");
            winnerTextView.setText(winnerName);

            int winnerScore = extras.getInt("winner-score");
            winnerScoreTextView.setText(new Integer(winnerScore).toString().trim());
        }

        startMenuButton.setOnClickListener(view -> {
            Intent intent = new Intent(GameResultActivity.this, MainActivity.class);

            startActivity(intent);
        });
    }
}