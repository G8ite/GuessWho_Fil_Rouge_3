package fr.amandine.qui_est_ce;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
    /**
     * Est appelée à la création de PlayerNameScreenActivity
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_name_screen);

        //Récupérer les textview dans lesquel insérer les noms de joueurs
        tvName1 = (TextView) findViewById(R.id.tName1);
        tvName2 = (TextView) findViewById(R.id.tName2);

        // Récupérer le tour de jeu
        SharedPreferences countTurn = getSharedPreferences(MainActivity.GAME_TURN, Activity.MODE_PRIVATE);
        String sTurn = countTurn.getString("Tour de Jeu", "1");
        int turn = Integer.valueOf(sTurn);
        turn++;
        String sNewTurn = Integer.toString(turn);
        SharedPreferences.Editor edCountTurn = countTurn.edit();
        edCountTurn.putString("Tour de Jeu", sNewTurn).apply();


        Log.i("TurnName : ", sNewTurn );

        //Récupérer les noms des joueurs
        SharedPreferences players = getSharedPreferences(MainActivity.PLAYERS, Activity.MODE_PRIVATE);

        //Joueur 1
        strPlayer1 = players.getString("Joueur1","");

        //Joueur2
        strPlayer2 = players.getString("Joueur2","");

        //Afficher les noms des joueur en fonction du tour de jeu
        //Si le tour est strictement divisible par 2, c'est au tour du joueur 2
        if(turn%2 != 0){
            tvName1.setText(strPlayer1+",");
            tvName2.setText(strPlayer2+",");
            //Log.i("Joueur 1 affichage", strPlayer1);
        }
        //Sinon c'est au tour du joueur 1
        else{
            Log.i("Joueur 1 affichage", strPlayer2);
            tvName1.setText(strPlayer2+",");
            tvName2.setText(strPlayer1+",");
        }

    }

    /**
     * Permet de lancer l'activité suivante
     * @param v
     */
    public void ready(View v){
        //Récupérer le tour de jeu
        SharedPreferences countTurn = getSharedPreferences(MainActivity.GAME_TURN, Activity.MODE_PRIVATE);
        String sTurn = countTurn.getString("Tour de Jeu", "1");
        int turn = Integer.valueOf(sTurn);

        // si le tour de jeu est inférieur à 3, on charge les pages de personnages
        if(turn < 3){
            Intent intent = new Intent(this, PersonnageActivity.class);
            startActivity(intent);
        }
        //Sinon, on charge le plateau de jeu
        else{
            Intent intent = new Intent(this, PlayerBoardGame.class);
            startActivity(intent);
        }

    }

    /**
     * Permet de retourner à l'accueil
     * @param v
     */
    public void clicQuit(View v){
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}