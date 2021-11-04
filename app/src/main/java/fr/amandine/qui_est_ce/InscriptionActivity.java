package fr.amandine.qui_est_ce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_inscription);
    }

    /**
     * Vérifier que les pseudos sont valides avant de lancer la partie
     * @return boolean
     */
    public boolean okToPlay(){
        //Récupérer les Widgets de la vue
        EditText edtPlayer1 = (EditText) findViewById(R.id.edtPlayer1);
        strPlayer1 = edtPlayer1.getText().toString();
        EditText edtPlayer2 = (EditText) findViewById(R.id.edtPlayer2);
        strPlayer2 = edtPlayer2.getText().toString();

        //Controler les valeurs
        //Tester si l'une des cases est vide
        if(this.strPlayer1.equals("")){
            Toast.makeText(getBaseContext(),R.string.player1NameVoid, Toast.LENGTH_LONG).show();
        }
        else if (this.strPlayer2.equals("")){
            Toast.makeText(getBaseContext(),R.string.player2NameVoid, Toast.LENGTH_LONG).show();
        }
        else if (this.strPlayer1.equals(this.strPlayer2)){
            Toast.makeText(getBaseContext(), R.string.player1EqualPlayer2, Toast.LENGTH_LONG).show();
        }
        else{
                return true;
        }
        return false;
    }
    public void playNewGame(View v){
        if(okToPlay()== true){
            Intent intent = new Intent(this, PlayerNameScreen.class );
            intent.putExtra(PLAYER_1_NAME, this.strPlayer1);
            intent.putExtra(PLAYER_2_NAME, this.strPlayer2);
            startActivity(intent);
        }
    }
    public void clicReturn(View v){
        //Toast.makeText(getBaseContext(), R.string.cancel, Toast.LENGTH_LONG).show();
        finish();
    }
}