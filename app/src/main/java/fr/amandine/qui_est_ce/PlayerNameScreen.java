package fr.amandine.qui_est_ce;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class PlayerNameScreen extends AppCompatActivity {

    //Données membres
    int count = 1;
    public final static String PLAYER_1_NAME = "Player1Name";
    private TextView tvName1 = null;
    private TextView tvName2 = null;
    private String strPlayer1 = "";
    private String strPlayer2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_name_screen);

        // Récupérer le tour de jeu
        SharedPreferences countTurn = getSharedPreferences(MainActivity.GAME_TURN, Activity.MODE_PRIVATE);
        String sTurn = countTurn.getString("Tour de Jeu", "1");
        int turn = Integer.valueOf(sTurn);

        //Récupérer les textview dans lesquel insérer les noms de joueurs
        tvName1 = (TextView) findViewById(R.id.tName1);
        tvName2 = (TextView) findViewById(R.id.tName2);

        //Récupérer les noms des joueurs
        SharedPreferences players = getSharedPreferences(MainActivity.PLAYERS, Activity.MODE_PRIVATE);

        //Joueur 1
        strPlayer1 = players.getString("Joueur1","");

        //Joueur2
        strPlayer2 = players.getString("Joueur2","");

        //Afficher les noms des joueur en fonction du tour de jeu
        if(turn%2 != 0){
            tvName1.setText(strPlayer1+",");
            tvName2.setText(strPlayer2+",");
        }
        else{
            tvName1.setText(strPlayer2+",");
            tvName2.setText(strPlayer1+",");
        }

    }
    public void ready(View v){
        Intent intent = new Intent(this, PersonnageActivity.class);
        intent.putExtra(strPlayer1, this.strPlayer1);
        startActivity(intent);
    }

}