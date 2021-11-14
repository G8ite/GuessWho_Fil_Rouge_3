package fr.amandine.qui_est_ce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class OptionsActivity extends AppCompatActivity {

    @Override
    /**
     * Est appelé au moment de la création de OptionsActivity
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
    }

    /**
     * Permet d'ouvrir l'activité pour changer le langage
     * @param v
     */
    public void changeLanguage(View v){
        Intent intent = new Intent(this, LanguageActivity.class);
        startActivity(intent);
    }

    /**
     * Permet de retourner à MainActivity, l'accueil
     * @param v
     */
    public void returnMain(View v){
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}