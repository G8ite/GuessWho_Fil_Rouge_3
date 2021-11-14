package fr.amandine.qui_est_ce;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Remember extends AppCompatActivity {

    @Override
    /**
     * Est appelée lors de la création de RememberActivity
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remember);

        //Récupérer le tour de jeu
        SharedPreferences countTurn = getSharedPreferences(MainActivity.GAME_TURN, Activity.MODE_PRIVATE);
        String sTurn = countTurn.getString("Tour de Jeu", "1");
        int turn = Integer.valueOf(sTurn);

        //Récupérer les noms des joueurs
        SharedPreferences players = getSharedPreferences(MainActivity.PLAYERS, Activity.MODE_PRIVATE);

        //Récupérer les images des joueurs
        //Joueur 1
        String strImgPlayer1 = players.getString("Joueur 1 charac","");

        //Joueur2
        String strImgPlayer2 = players.getString("Joueur 2 charac","");

        //Récupérer l'imageview qui affiche l'image du joueur concerné
        ImageView imgRemember = findViewById(R.id.persoRemember);


        if(turn%2 == 0){
            //Construire le chemin d'accès à cette image
            String path = "@drawable/"+strImgPlayer2;
            imgRemember.setImageResource(getResources().getIdentifier(path, null, getPackageName()));
        }
        else{
            //Construire le chemin d'accès à cette image
            String path = "@drawable/"+strImgPlayer1;
            imgRemember.setImageResource(getResources().getIdentifier(path, null, getPackageName()));
        }
    }

    public void okRemember(View v){
        Intent intent = new Intent(this, PlayerBoardGame.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}