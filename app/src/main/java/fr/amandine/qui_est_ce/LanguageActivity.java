package fr.amandine.qui_est_ce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

public class LanguageActivity extends AppCompatActivity {

    @Override
    /**
     * Est appelée à la création de LanguageActivity
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        //Récupérer les boutons de ma page language activity
        Button btnFr = findViewById(R.id.btnFrench);
        Button btnEn = findViewById(R.id.btnEnglish);
        Button btnSp = findViewById(R.id.btnSpanish);
        Button btnPt = findViewById(R.id.btnPortugues);

        //On commence par le bouton par le français
        //On ajouer un eventListener qui permettra de capter le clique/tap
        btnFr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //On récupère le nouveau language local
                Locale locale = new Locale("fr");
                //On le transforme en string pour le manipuler
                String strLocale = locale.toString();
                //On ajoute "fr" comme langue par défault
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                //On modifie les configuration avec notre nouvelle langue
                config.locale = locale;

                //On ajoute un fichier de préférences pour stocker notre langue favorite
                SharedPreferences myFavLanguages = getSharedPreferences(MainActivity.MY_FAY_LANGUAGES, MODE_PRIVATE);
                String language = myFavLanguages.getString("Language", "");

                //Si notre language est différent
                if (!language.equals(strLocale)){
                    //On l'ajoute à notre fichier de préférences
                    SharedPreferences.Editor edMyFavLang = myFavLanguages.edit();
                    edMyFavLang.putString("Language", strLocale).apply();

                    //On retourne à la page d'accueil en écrasant la pile d'activité
                    Intent intent = new Intent(LanguageActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                //Si c'est la même
                else{
                    //On l'indique à l'utilisateur
                    Toast.makeText(LanguageActivity.this, R.string.sameLang, Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnEn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Locale locale = new Locale("en");
                String strLocale = locale.toString();
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;

                SharedPreferences myFavLanguages = getSharedPreferences(MainActivity.MY_FAY_LANGUAGES, MODE_PRIVATE);
                String language = myFavLanguages.getString("Language", "");

                if (!language.equals(strLocale)){
                    SharedPreferences.Editor edMyFavLang = myFavLanguages.edit();
                    edMyFavLang.putString("Language", strLocale).apply();

                    Intent intent = new Intent(LanguageActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(LanguageActivity.this, R.string.sameLang, Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnSp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Locale locale = new Locale("es");
                String strLocale = locale.toString();
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;

                SharedPreferences myFavLanguages = getSharedPreferences(MainActivity.MY_FAY_LANGUAGES, MODE_PRIVATE);
                String language = myFavLanguages.getString("Language", "");

                if (!language.equals(strLocale)){
                    SharedPreferences.Editor edMyFavLang = myFavLanguages.edit();
                    edMyFavLang.putString("Language", strLocale).apply();

                    Intent intent = new Intent(LanguageActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(LanguageActivity.this, R.string.sameLang, Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnPt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Locale locale = new Locale("pt");
                String strLocale = locale.toString();
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;

                SharedPreferences myFavLanguages = getSharedPreferences(MainActivity.MY_FAY_LANGUAGES, MODE_PRIVATE);
                String language = myFavLanguages.getString("Language", "");

                if (!language.equals(strLocale)){
                    SharedPreferences.Editor edMyFavLang = myFavLanguages.edit();
                    edMyFavLang.putString("Language", strLocale).apply();

                    Intent intent = new Intent(LanguageActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(LanguageActivity.this, R.string.sameLang, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    /**
     * Permet de revenir à la page précédente
     * @param v
     */
    public void clicReturn(View v){
        //Toast.makeText(getBaseContext(), R.string.cancel, Toast.LENGTH_LONG).show();
        finish();
    }
}