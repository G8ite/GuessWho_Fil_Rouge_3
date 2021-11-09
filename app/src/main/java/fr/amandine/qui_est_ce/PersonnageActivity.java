package fr.amandine.qui_est_ce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import java.text.DecimalFormat;

public class PersonnageActivity extends AppCompatActivity {

    //Données membres
    private TextView tvName = null;

    String strPlayer1 = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personnage);

        //Permet de générer un chiffre aléatoire en fonction du nombre d'images stockées
        double dNumber = Math.round(Math.random()*66);

        //Format le double en string en enlevant les nombres après la virgule
        DecimalFormat f = new DecimalFormat();
        f.setMaximumFractionDigits(0);
        String sNumber = f.format(dNumber);

        ImageView character = findViewById(R.id.perso);

        String imgName="@drawable/person"+sNumber; // j'écris le chemin pour récupérer mon image

        character.setImageResource(getResources().getIdentifier(imgName, null, getPackageName()));


        Intent intent = getIntent();
        strPlayer1 = intent.getExtras().getString(PlayerNameScreen.PLAYER_1_NAME);

        tvName = (TextView) findViewById(R.id.tName);

        tvName.setText(strPlayer1+",");
    }
    public void ready(View v){
        Intent intent = new Intent(this, PlayerBoardGame.class);
        startActivity(intent);
    }
}