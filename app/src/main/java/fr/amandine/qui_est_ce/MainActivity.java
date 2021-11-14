package fr.amandine.qui_est_ce;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    //Données membres

    public final static String MY_FAY_LANGUAGES = "My_Fav_Languages";
    public final static String PLAYERS = "Players";
    public final static String GAME_TURN = "Game_Turn";

    @Override
    /**
     * Est appelé à la création de MainActivity
     */
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        loadLocale();
        setContentView(R.layout.activity_main);

        // Effacer les joueurs pour être sur de reprendre à 0
        SharedPreferences playerPreferences = getSharedPreferences(PLAYERS, MODE_PRIVATE);
        playerPreferences.edit().remove("Joueur1").commit();
        playerPreferences.edit().remove("Joueur2").commit();
        playerPreferences.edit().remove("Joueur 1 charac").commit();
        playerPreferences.edit().remove("Joueur 2 charac").commit();

        //Supprimer la base de données des cases de jeu si elle ne l'a pas été avant
        this.deleteDatabase("Game.db");

    }

    /**
     * Permet de lancer une nouvelle partie
     * @param v
     */
    public void newGame(View v){
        //Intent qui commence la partie et envoie vers la page d'inscription des noms de joueurs
        Intent intent = new Intent(this, InscriptionActivity.class);
        startActivity(intent);
    }

    /**
     * Permet d'aller sur la page des options
     * @param v
     */
    public void optionPage(View v){
        //Intent qui envoie vers la page des options
        Intent intent = new Intent(this, OptionsActivity.class);
        startActivity(intent);
    }

    /**
     * Permet de charger la langue choisir par l'utilisateur et change la langue de l'application
     */
    public void loadLocale(){
        //Permet de récupérer la langue choisie par l'utilisateur
        SharedPreferences fav = getSharedPreferences(MY_FAY_LANGUAGES, Activity.MODE_PRIVATE);
        String language = fav.getString("Language", "");
        changeLang(language);
    }

    /**
     * Permet de changer la langue
     * @param lang
     */
    public void changeLang(String lang){
        //Si la langue choisie est la même que celle de l'application, il ne se passe rien.
        if (lang.equalsIgnoreCase("")){
            return;
        }
        //Sinon, on change la langue de l'application
        Locale myLocale = new Locale(lang);
        Locale.setDefault(myLocale);
        Configuration config = new Configuration();
        config.locale= myLocale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    }

    /**
     * Quitte l'application
     * @param v
     */
    public void clicQuit (View v){
        finish();
        System.exit(0);
    }
}