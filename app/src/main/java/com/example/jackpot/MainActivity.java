package com.example.jackpot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {

    EditText number;
    ImageView image;
    Random rand = new Random();
    Button button;
    int count = 0, num, score = 110, status = 0;
    int randomnumber  = rand.nextInt(100) + 1;

    public void check(View view) {
        count++;
        score -= 10;

        number = findViewById(R.id.number);
        image = findViewById(R.id.image);
        button = findViewById(R.id.submit);
        String value = number.getText().toString();

        if(value.equals("") && status == 0) {
            Toast.makeText(this, "Invalid Input!", Toast.LENGTH_SHORT).show();
            return;
        }

        else if(status != 1){
            num = parseInt(value);
        }


        if(status == 1) {
            status = 0;
            randomnumber = rand.nextInt(100) + 1;
            count = 0;
            score = 110;
            image.setImageResource(R.drawable.questionmark);
            button.setText("Guess");
            return;
        }

        else if(num > randomnumber) {
            image.setImageResource(R.drawable.aimlower);
            Toast.makeText(this, "Aim Lower", Toast.LENGTH_SHORT).show();
        }

        else if(num < randomnumber) {
            Toast.makeText(this, "Aim Higher", Toast.LENGTH_SHORT).show();
            image.setImageResource(R.drawable.aimhigher);
        }

        else {
            Toast.makeText(this, "Your Score: " + score, Toast.LENGTH_LONG).show();
            image.setImageResource(R.drawable.correct);
            button.setText("New Game");
            status = 1;
        }

        if(score < 0 && status == 0) {
            image.setImageResource(R.drawable.wrong);
            Toast.makeText(this, "Better luck next time!", Toast.LENGTH_LONG).show();
            button.setText("New Game");
            status = 1;
        }

        number.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
