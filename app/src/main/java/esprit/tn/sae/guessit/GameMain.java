package esprit.tn.sae.guessit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class GameMain extends AppCompatActivity {

    Button startMenuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_main);

        startMenuButton = findViewById(R.id.startMenuButton);

        startMenuButton.setOnClickListener(view -> {
            finish();
        });
    }
}