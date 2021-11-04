package fr.amandine.qui_est_ce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class OptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
    }
    public void changeLanguage(View v){
        Intent intent = new Intent(this, LanguageActivity.class);
        startActivity(intent);
    }
    public void clicReturn(View v){
        //Toast.makeText(getBaseContext(), R.string.cancel, Toast.LENGTH_LONG).show();
        finish();
    }
}