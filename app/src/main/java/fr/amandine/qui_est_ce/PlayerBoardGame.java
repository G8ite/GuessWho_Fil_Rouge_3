package fr.amandine.qui_est_ce;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class PlayerBoardGame extends AppCompatActivity {

    int clickCount = 0;
    @Override
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
                    // Le layout devient cliquable
                    buttonLayout.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            if (clickCount == 0) {
                                buttonLayout.setBackground(getDrawable(R.drawable.card));
                                clickCount++;
                            } else {
                                buttonLayout.setBackground(getDrawable(getResources().getIdentifier(imgName, null, getPackageName())));
                                clickCount--;
                            }
                        }
                    });
                    // Ajouter les nouveaux layout au layout de sa ligne
                    linearLayout.addView(buttonLayout,params2);
                }
            }

        }

    }
}