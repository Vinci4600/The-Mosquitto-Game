package com.example.thegame;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.SharedPreferences;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tvHighscoreMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //Highscore verknüpfen
        tvHighscoreMain = findViewById(R.id.tvHighscoreMain);

        //Speicher Laden

        SharedPreferences prefs = getSharedPreferences("game", MODE_PRIVATE);

        //Highscore holen
        int highscore = prefs.getInt("highscore", 0);

        //Anzeigen
        tvHighscoreMain.setText("Highscore: " + highscore);

        Button button = findViewById(R.id.btnplay);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            startActivity(intent);
        });
        Button button1 = findViewById(R.id.btnhin);
        button1.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, HinweisActivity.class);
            startActivity(intent);
        });
    }
}