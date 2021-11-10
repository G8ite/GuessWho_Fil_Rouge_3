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

    // Données membres



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        Button btnFr = findViewById(R.id.btnFrench);
        Button btnEn = findViewById(R.id.btnEnglish);
        Button btnSp = findViewById(R.id.btnSpanish);
        Button btnPt = findViewById(R.id.btnPortugues);

        btnFr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Locale locale = new Locale("fr");
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
    public void clicReturn(View v){
        //Toast.makeText(getBaseContext(), R.string.cancel, Toast.LENGTH_LONG).show();
        finish();
    }
}