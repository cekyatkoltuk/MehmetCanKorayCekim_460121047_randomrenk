package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.SeekBar;
import android.widget.Button;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.Random;
import android.view.View;
import android.os.Handler;
import android.graphics.Color;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekBar1, seekBar2;
    private Button generateButton;
    private ConstraintLayout constraintLayout;
    private Random random;
    private TextView randomNumberTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar1 = findViewById(R.id.seekBar1);
        seekBar2 = findViewById(R.id.seekBar2);
        generateButton = findViewById(R.id.generateButton);
        constraintLayout = findViewById(R.id.constraintLayout);
        randomNumberTextView = findViewById(R.id.randomNumberTextView);
        random = new Random();

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int min = Math.min(seekBar1.getProgress(), seekBar2.getProgress());
                int max = Math.max(seekBar1.getProgress(), seekBar2.getProgress());
                int randomNumber = random.nextInt(max - min + 1) + min;

                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtra("randomNumber", randomNumber);
                startActivity(intent);
                changeBackgroundColor(randomNumber);
            }
        });


    }

    private void changeBackgroundColor(final int seconds) {
        final Handler handler = new Handler();
        final int[] colors = new int[seconds];
        for (int i = 0; i < seconds; i++) {
            colors[i] = getRandomColor();
        }

        final int[] count = {0};
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                constraintLayout.setBackgroundColor(colors[count[0] % seconds]);

                count[0]++;
                if (count[0] < seconds) {
                    handler.postDelayed(this, 2000);
                }
            }
        }, 0);
    }

    private int getRandomColor() {
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }
}