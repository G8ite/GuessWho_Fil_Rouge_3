package fr.amandine.qui_est_ce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class PlayerBoardGame extends AppCompatActivity {

    //Données membres
    int clickCount = 0;
    int idCount = 0;

    //Fonctions

    @Override
    /**
     * Fonction permettant de créer le plateau de jeu
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_board_game);

        // Récupérer le Layout qui contiendra notre tableau d'images
        LinearLayout conLinearLayout = findViewById(R.id.linearContainer);

        //Créer un tableau qui contiendra les identifiants des images déjà dans le tableau
        List<String> lNumber = new ArrayList<String>();
        //Créer un compteur pour changer de background au click

        //Une première boucle qui permet de créer les lignes de notre grille
        for (int i=0; i<6;i++){

            //Créer le layout de ligne
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setPadding(0,10,0,10);

            //Gérer ses paramètres layout_width et layout_height
            LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            //Centrer le layout
            linearLayout.setGravity(Gravity.CENTER); // Gravity.CENTER permet de récupérer le int nécessaire à la fonction setGravity

            //On ajoute le layout fraichement créé à notre layout container
            conLinearLayout.addView(linearLayout, linearParams);

            //Une seconde bouble qui permet de créer les images dans les lignes
            for(int j=0;j<3;j++){

                //On crée le layout d'image
                LinearLayout buttonLayout = new LinearLayout(this);

                // On gère sa largeur et sa hauteur
                LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(300,300);

                //Permet de générer un chiffre aléatoire en fonction du nombre d'images stockées
                double dNumber = Math.round(Math.random()*66);

                //Format le double en string en enlevant les nombres après la virgule
                DecimalFormat f = new DecimalFormat();
                f.setMaximumFractionDigits(0);
                String sNumber = f.format(dNumber);

                //Créer un booléen qui permet de vérifier si l'identifiant pris au hasard n'est pas déjà présent dans la grille
                //Si il y est déjà
                boolean verify = lNumber.contains(sNumber);
                //Je décrémente mon compteur pour qu'il y ait bien le bon nombre d'images par ligne
                if(verify){
                    j--;
                }
                else{// Si elle n'y est pas
                    lNumber.add(sNumber); // je l'ajoute à la liste
                    String imgName="@drawable/person"+sNumber; // j'écris le chemin pour récupérer mon image
                    Log.i("PlayerBoard",imgName); // permet de vérifier que ce chemin est bien écrit

                    //L'image est récupérée et placée en background sur le layout
                    buttonLayout.setBackground(getDrawable(getResources().getIdentifier(imgName, null, getPackageName())));

                    //Permettre à chaque image d'ouvrir un menu contextuel
                    registerForContextMenu(buttonLayout);
                    //Incrément le compteur id
                    idCount++;
                    //J'ajoute le compteur en id pour définir le numéro de la case
                    buttonLayout.setId(idCount);

                    //Je récupère l'id de ma case pour pouvoir vérifier
                    //int idtest = buttonLayout.getId();

                    // Le layout devient cliquable
                    buttonLayout.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {

                            if (clickCount > 0) {
                                clickCount=0;
                                buttonLayout.setBackground(getDrawable(getResources().getIdentifier(imgName, null, getPackageName())));
                            } else {
                                clickCount++;
                                buttonLayout.setBackground(getDrawable(R.drawable.card));
                                //Toast.makeText(getBaseContext(), "case n"+idTest, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    // Ajouter les nouveaux layout au layout de sa ligne
                    linearLayout.addView(buttonLayout,params2);
                }
            }

        }

        //Concernant le context menu

    }

    /**
     * Permet de créer un menu contextuel
     * @param menu
     * @param v
     * @param menuInfo
     */
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        getMenuInflater().inflate(R.menu.menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);

    }

    /**
     * Permet de gérer les items du menu contextuel
     * @param item
     * @return
     */
    public boolean onContextItemSelected(@NonNull MenuItem item){
        Toast.makeText(this,""+item.getTitle(), Toast.LENGTH_LONG).show();
        return super.onContextItemSelected(item);
    }

    public void nextTurn(View v){
        SharedPreferences countTurn = getSharedPreferences("Game_Turn", Activity.MODE_PRIVATE);
        String sCount = countTurn.getString("Tour de Jeu", "");
        int count = Integer.valueOf(sCount);
        count++;
        SharedPreferences.Editor edCountTurn = countTurn.edit();
        String newCount = Integer.toString(count);
        Log.i("Count", newCount);
        edCountTurn.putString("Tour de Jeu", newCount).apply();
        Intent intent = new Intent(this, PlayerBoardGame.class);
        startActivity(intent);
        if(count == 3){
            Intent intent1 = new Intent(this, GoalActivity.class);
            startActivity(intent1);
        }
    }
}