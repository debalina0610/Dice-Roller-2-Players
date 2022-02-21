package com.debalina0610.diceroller2players;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PlayerNames extends AppCompatActivity {

    private Button start;
    private EditText player1;
    private EditText player2;
    private String p1,p2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_names);

        start = findViewById(R.id.start);
        player1 = findViewById(R.id.name1);
        player2 = findViewById(R.id.name2);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startgame();
            }
        });
    }

    public void startgame(){
        p1 = player1.getText().toString();
        Intent intent = new Intent(this, DiceRoller.class);
        intent.putExtra(DiceRoller.PL1,p1);

        p2 = player2.getText().toString();
        intent.putExtra(DiceRoller.PL2,p2);
        startActivity(intent);

    }
}