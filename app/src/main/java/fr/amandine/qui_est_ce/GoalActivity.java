package fr.amandine.qui_est_ce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GoalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);

        TextView winner = findViewById(R.id.winner);
        winner.setText("Gagnant");
    }
    public void clickQuit(View v){
        //Stocker le gagnant

        // Réinitialiser le compteur pour être sur de reprendre à 0 la prochaine fois
        SharedPreferences countPreferences = getSharedPreferences(MainActivity.GAME_TURN, MODE_PRIVATE);
        countPreferences.edit().remove("Tour de Jeu").commit();

        // Retour à l'acceuil
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void clickReplay(View v){
        // Effacer les joueurs pour être sur de reprendre à 0 la prochaine fois
        SharedPreferences playerPreferences = getSharedPreferences(MainActivity.PLAYERS, MODE_PRIVATE);
        playerPreferences.edit().remove("Joueur1").commit();
        playerPreferences.edit().remove("Joueur2").commit();
        playerPreferences.edit().remove("Joueur 1 charac").commit();
        playerPreferences.edit().remove("Joueur 2 charac").commit();

        //Stocker le gagnant

        // Réinitialiser le compteur pour être sur de reprendre à 0 la prochaine fois
        SharedPreferences countPreferences = getSharedPreferences(MainActivity.GAME_TURN, MODE_PRIVATE);
        countPreferences.edit().remove("Tour de Jeu").commit();

        //Retour à l'inscription
        Intent intent1 = new Intent(this, InscriptionActivity.class);
        startActivity(intent1);
    }
}