package fr.amandine.qui_est_ce;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import java.text.DecimalFormat;

public class PersonnageActivity extends AppCompatActivity {

    //Données membres
    private TextView tvName = null;
    public Intent intent = null;

    String strPlayer1 = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personnage);

        String sNumber = randomNumber();

        ImageView character = findViewById(R.id.perso);

        String imgName="@drawable/person"+sNumber; // j'écris le chemin pour récupérer mon image

        // Récupérer les préférences concernant les joueurs
        SharedPreferences players = getSharedPreferences(MainActivity.PLAYERS, Activity.MODE_PRIVATE);
        SharedPreferences.Editor edPlayerChar = players.edit();
        //Vérifier si un joueur 1 a déjà été créé et agir en conséquences
        if(players.contains("Joueur 1 charac")){

            edPlayerChar.putString("Joueur 2 charac",imgName).apply();
            intent = new Intent(this, PlayerBoardGame.class);
        }
        else{
            edPlayerChar.putString("Joueur 1 charac",imgName).apply();
            intent = new Intent(this, PersonnageActivity.class);
        }

        //Joueur 1
        strPlayer1 = players.getString("Joueur1","");
        character.setImageResource(getResources().getIdentifier(imgName, null, getPackageName()));

        SharedPreferences player1 = getSharedPreferences(MainActivity.PLAYERS, Activity.MODE_PRIVATE);
        strPlayer1 = player1.getString("Joueur1","");
        tvName = (TextView) findViewById(R.id.tName);

        tvName.setText(strPlayer1+",");
    }
    public void ready(View v){
        startActivity(intent);
    }
    public String randomNumber() {
        //Permet de générer un chiffre aléatoire en fonction du nombre d'images stockées
        double dNumber = Math.round(Math.random()*66);

        //Format le double en string en enlevant les nombres après la virgule
        DecimalFormat f = new DecimalFormat();
        f.setMaximumFractionDigits(0);
        String sNumber = f.format(dNumber);

        return sNumber;
    }
}