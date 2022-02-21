package com.debalina0610.diceroller2players;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class DiceRoller extends AppCompatActivity {

    private ImageView dice_p1, dice_p2, lives_p1, lives_p2;
    public static final String PL1 = "Player1", PL2 = "Player2";
    private TextView p1, p2;
    private String player1, player2;
    private Button home, exit;

    Random random;

    private int lives1 = 6, lives2 = 6;
    private int rollp1, rollp2;

    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice_roller);

        random = new Random();

        animation = AnimationUtils.loadAnimation(this, R.anim.rolling);

        dice_p1 = findViewById(R.id.dice_player1);
        dice_p2 = findViewById(R.id.dice_player2);
        lives_p1 = findViewById(R.id.lives_player1);
        lives_p2 = findViewById(R.id.lives_player2);
        home = findViewById(R.id.home);
        exit = findViewById(R.id.exit);

        p1 = findViewById(R.id.player1);
        p2 = findViewById(R.id.player2);

        Intent intent = getIntent();
        player1 = intent.getStringExtra(PL1);
        p1.setText(player1);

        player2 = intent.getStringExtra(PL2);
        p2.setText(player2);

        diceimage(lives1, lives_p1);
        diceimage(lives2, lives_p2);

        dice_p1.setOnClickListener((View) -> {
            rollp1 = random.nextInt(6) + 1;
            diceimage(rollp1, dice_p1);
            dice_p1.startAnimation(animation);

            if(rollp2 != 0){
                p1.setText(player1 );
                p2.setText(player2 );

                if(rollp1 > rollp2) {
                    lives2--;
                    diceimage(lives2, lives_p2);
                }
                if(rollp2 > rollp1) {
                    lives1--;
                    diceimage(lives1, lives_p1);
                }

                endgame();

                rollp1 = 0;
                rollp2 = 0;

                dice_p1.setEnabled(true);
                dice_p2.setEnabled(true);
            }
            else {
                p1.setText(player1 );
                dice_p1.setEnabled(false);
            }
        });

        dice_p2.setOnClickListener((View) -> {
            rollp2 = random.nextInt(6) + 1;
            diceimage(rollp2, dice_p2);
            dice_p2.startAnimation(animation);

            if(rollp1 != 0){
                p1.setText(player1);
                p2.setText(player2);

                if(rollp1 > rollp2) {
                    lives2--;
                    diceimage(lives2, lives_p2);
                }
                if(rollp2 > rollp1) {
                    lives1--;
                    diceimage(lives1, lives_p1);
                }

                rollp1 = 0;
                rollp2 = 0;

                endgame();

                dice_p1.setEnabled(true);
                dice_p2.setEnabled(true);
            }
            else {
                p2.setText(player2);
                dice_p2.setEnabled(false);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homepage();
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exit_game();
            }
        });
    }

    public void homepage(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void diceimage(int dice, ImageView image){
        switch (dice){
            case 1:
                image.setImageResource(R.drawable.dice1);
                break;
            case 2:
                image.setImageResource(R.drawable.dice2);
                break;
            case 3:
                image.setImageResource(R.drawable.dice3);
                break;
            case 4:
                image.setImageResource(R.drawable.dice4);
                break;
            case 5:
                image.setImageResource(R.drawable.dice5);
                break;
            case 6:
                image.setImageResource(R.drawable.dice6);
                break;
            default:
                image.setImageResource(R.drawable.dice0);
        }
    }

    private void endgame(){
        if(lives1 == 0 || lives2 == 0){
            String winner = "";

            if(lives1 != 0){
                winner = "Game Over!  Winner " + player1;
            }

            if(lives2 != 0){
                winner = "Game Over!  Winner " + player2;
            }

            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setCancelable(false);
            alert.setMessage(winner);
            alert.setPositiveButton("Restart Game", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    restart();
                }
            });
            AlertDialog alertDialog = alert.create();
            alertDialog.show();
        }
    }

    public void restart(){
        lives1 = 6;
        lives2 = 6;
        p1.setText(player1);
        p2.setText(player2);
        diceimage(lives1, lives_p1);
        diceimage(lives2, lives_p2);
        diceimage(6, dice_p1);
        diceimage(6, dice_p2);
    }

    private void exit_game(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setCancelable(false);
        alert.setMessage("Exit Game !!");
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        AlertDialog alertDialog = alert.create();
        alertDialog.show();
    }
}
