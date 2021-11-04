package fr.amandine.qui_est_ce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LanguageActivity extends AppCompatActivity {

    // Données membres
    Button btnFr = (Button) findViewById(R.id.btnFrench);
    Button btnEn = (Button) findViewById(R.id.btnEnglish);
    Button btnSp = (Button) findViewById(R.id.btnSpanish);
    Button btnPt = (Button) findViewById(R.id.btnPortugues);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
    }
    public void clicReturn(View v){
        //Toast.makeText(getBaseContext(), R.string.cancel, Toast.LENGTH_LONG).show();
        finish();
    }
}