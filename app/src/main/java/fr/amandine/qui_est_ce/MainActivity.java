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

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    //Données membres

    public final static String MY_FAY_LANGUAGES = "My_fav_Languages";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        loadLocale();
        setContentView(R.layout.activity_main);
    }
    public void newGame(View v){
        Intent intent = new Intent(this, InscriptionActivity.class);
        startActivity(intent);
    }
    public void optionPage(View v){
        Intent intent = new Intent(this, OptionsActivity.class);
        startActivity(intent);
    }
    public void loadLocale(){
        SharedPreferences fav = getSharedPreferences(MY_FAY_LANGUAGES, Activity.MODE_PRIVATE);
        String language = fav.getString("Language", "");
        changeLang(language);
    }

    public void changeLang(String lang){
        if (lang.equalsIgnoreCase("")){
            return;
        }
        Locale myLocale = new Locale(lang);
        Locale.setDefault(myLocale);
        Configuration config = new Configuration();
        config.locale= myLocale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    }

    public void clicQuit (View v){
        Toast.makeText(getBaseContext(), R.string.exit, Toast.LENGTH_LONG).show();
        System.exit(0);
    }
}