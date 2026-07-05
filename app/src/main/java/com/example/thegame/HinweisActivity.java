package com.example.thegame;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HinweisActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hinweis);

        Button button = findViewById(R.id.btnback);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(HinweisActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}