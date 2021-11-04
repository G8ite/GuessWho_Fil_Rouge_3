package fr.amandine.qui_est_ce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class PlayerNameScreen extends AppCompatActivity {

    //Données membres
    int count = 1;
    private TextView tvName1 = null;
    private TextView tvName2 = null;
    private String strPlayer1 = "";
    private String strPlayer2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_name_screen);

        //Récupérer les données de l'Intent
        Intent intent = getIntent();
        strPlayer1 = intent.getExtras().getString(InscriptionActivity.PLAYER_1_NAME);
        strPlayer2 = intent.getExtras().getString(InscriptionActivity.PLAYER_2_NAME);

        tvName1 = (TextView) findViewById(R.id.tName1);
        tvName2 = (TextView) findViewById(R.id.tName2);

        tvName1.setText(strPlayer1+",");
        tvName2.setText(strPlayer2+",");
    }

}