package fr.amandine.qui_est_ce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

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
    public void clicQuit (View v){
        Toast.makeText(getBaseContext(), R.string.exit, Toast.LENGTH_LONG).show();
        System.exit(0);
    }
}