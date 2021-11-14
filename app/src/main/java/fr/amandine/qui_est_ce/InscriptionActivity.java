package fr.amandine.qui_est_ce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class InscriptionActivity extends AppCompatActivity {
    // Contantes
    public final static String PLAYER_1_NAME = "Player1Name";
    public final static String PLAYER_2_NAME = "Player2Name";
    //Données membres
    private String strPlayer1 = null;
    private String strPlayer2 = null;

    /**
     * Est appelée à la création de InscriptionActivity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_inscription);

        // On réinitialise le compteur de tour de jeu en l'envoyant dans les préférences
        //Je récupère cette préférence
        SharedPreferences countTurn = getSharedPreferences(MainActivity.GAME_TURN, MODE_PRIVATE);
        //Je la rends éditable
        SharedPreferences.Editor edCountTurn = countTurn.edit();
        //Tour de jeu est à 0
        edCountTurn.putString("Tour de Jeu", "0").apply();

    }

    /**
     * Vérifier que les pseudos sont valides avant de lancer la partie
     * @return boolean
     */
    public boolean okToPlay(){
        //Récupérer les Widgets de la vue
        EditText edtPlayer1 = (EditText) findViewById(R.id.edtPlayer1);
        //Récupérer les données inscrites dans le premier editText
        strPlayer1 = edtPlayer1.getText().toString();
        EditText edtPlayer2 = (EditText) findViewById(R.id.edtPlayer2);
        //Récupérer les données inscrites dans le premier editText
        strPlayer2 = edtPlayer2.getText().toString();

        //Controler les valeurs
        //Tester si l'une des cases est vide
        if(this.strPlayer1.equals("")){
            Toast.makeText(getBaseContext(),R.string.player1NameVoid, Toast.LENGTH_LONG).show();
        }
        else if (this.strPlayer2.equals("")){
            Toast.makeText(getBaseContext(),R.string.player2NameVoid, Toast.LENGTH_LONG).show();
        }
        //Si les 2 noms entrée sont différents
        else if (this.strPlayer1.equals(this.strPlayer2)){
            Toast.makeText(getBaseContext(), R.string.player1EqualPlayer2, Toast.LENGTH_LONG).show();
        }
        else{
                return true;
        }
        return false;
    }

    /**
     * Permet de lancer la partie et de stocker les noms de joueurs dans les préférences
     * @param v
     */
    public void playNewGame(View v){
        //Si les pseudos sont valides
        if(okToPlay()== true){
            //Je crée un intent pour passer à l'activité suivante
            Intent intent = new Intent(this, PlayerNameScreen.class );
            //Je crée les préférences de joueurs
            SharedPreferences playerOne = getSharedPreferences(MainActivity.PLAYERS, MODE_PRIVATE);
            //Je les rends éditables
            SharedPreferences.Editor edPlayerOne = playerOne.edit();
            //J'ajoute le pseudo du joueur 1
            edPlayerOne.putString("Joueur1", strPlayer1).apply();
            //Jajoute le pseudo du joueur 2
            edPlayerOne.putString("Joueur2", strPlayer2).apply();

            //Je lance l'intent
            startActivity(intent);
        }
    }

    /**
     * Permet de retourner à l'accueil
     * @param v
     */
    public void clicReturn(View v){
        //Toast.makeText(getBaseContext(), R.string.cancel, Toast.LENGTH_LONG).show();
        finish();
    }
}