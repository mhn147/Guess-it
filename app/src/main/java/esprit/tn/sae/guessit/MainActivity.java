package esprit.tn.sae.guessit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button playersBtn;
    Button quitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playersBtn = findViewById(R.id.playersBtn);
        quitButton = findViewById(R.id.quitButton);

        playersBtn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, PlayersActivity.class);
            startActivity(intent);
        });

        quitButton.setOnClickListener(view -> {
            this.finish();
        });
    }
}