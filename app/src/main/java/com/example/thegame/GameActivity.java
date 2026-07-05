package com.example.thegame;
import android.media.MediaPlayer;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;

public class GameActivity extends AppCompatActivity {
    //Schritt 5
    TextView txtPoints;
    FrameLayout gameArea;
    int points = 0;
    int highscore = 0;

    private ImageView wasp;
    SharedPreferences prefs;
    private MediaPlayer Mosquiutto ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Mosquiutto = MediaPlayer.create(this, R.raw.mosqiutto);
        //Schritt 5
        txtPoints = findViewById(R.id.txtPoints);
        gameArea = findViewById(R.id.gameArea);

        prefs = getSharedPreferences("game", MODE_PRIVATE);

        highscore = prefs.getInt("highscore", 0);

        gameArea = findViewById(R.id.gameArea);

        createMosquito();
        createWasp();



        // Schritt 7 Erstelle eine Methode Mosquito


    }
//Gengenr wespe
private void createWasp() {

    wasp = new ImageView(this);
    wasp.setImageResource(R.drawable.wasp);

    FrameLayout.LayoutParams params =
            new FrameLayout.LayoutParams(350, 350);

    wasp.setLayoutParams(params);

    wasp.setVisibility(View.GONE);

    gameArea.addView(wasp);

    gameArea.post(() -> setRandomPosition(wasp));

    wasp.setOnClickListener(v -> {

        points--;

        if (points < 0) {
           points = 0;
       }

        txtPoints.setText("Points: " + points);
        updateHighscore();



        setRandomPosition(wasp);
    });
}

    private void updateWaspVisibility() {
        if (wasp == null) return;

        if (points >= 10) {
            wasp.setVisibility(View.VISIBLE);
        } else {
            wasp.setVisibility(View.GONE);
        }
    }
    private void updateHighscore() {

        if (points > highscore) {
            highscore = points;

            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("highscore", highscore);
            editor.apply();
        }
    }
    private void createMosquito() {

           //Image View Schritt 8
            ImageView mosquito = new ImageView(this);
            //Mücken bild erzeugen
            mosquito.setImageResource(R.drawable.mosquito);

            FrameLayout.LayoutParams params =
                    new FrameLayout.LayoutParams(350, 350);

            mosquito.setLayoutParams(params);
            //Wieder zu Gamearea hinzufügen
            gameArea.addView(mosquito);

            gameArea.post(() -> setRandomPosition(mosquito));

            //Neuer event Listener
            mosquito.setOnClickListener(v -> {

                // Punkte Erhöhen
                points++;
                //Funktion immer + 1 Punkt
                txtPoints.setText("Points: " + points);
                updateHighscore();

                updateWaspVisibility();
                Mosquiutto.start();
                Mosquiutto.seekTo(0);


                //Zufällig
                setRandomPosition(mosquito);





            });
            Button button = findViewById(R.id.btnback);
            button.setOnClickListener(v -> {
                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                startActivity(intent);
            });



        }
        //Zufalls Generator
        private void setRandomPosition(ImageView mosquito) {

        int maxX = gameArea.getWidth() - mosquito.getWidth();
        int maxY = gameArea.getHeight() - mosquito.getHeight();

        int x = (int) (Math.random() * maxX);
        int y = (int) (Math.random() * maxY);

        mosquito.setX(x);
        mosquito.setY(y);
    }



}
