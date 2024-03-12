package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.os.Handler;
import android.graphics.Color;
import java.util.Random;

public class ResultActivity extends AppCompatActivity {

    private TextView resultTextView;
    private ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultTextView = findViewById(R.id.resultTextView);
        constraintLayout = findViewById(R.id.constraintLayout);

        int randomNumber = getIntent().getIntExtra("randomNumber", 0);
        resultTextView.setText(String.valueOf(randomNumber));
        changeBackgroundColor(randomNumber);
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
