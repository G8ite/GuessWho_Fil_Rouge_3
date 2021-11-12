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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.amandine.qui_est_ce.Class.ImageCase;

public class PlayerBoardGame extends AppCompatActivity {

    //Données membres
    int clickCount = 0;
    int idCount = 0;
    private DataBaseMgr dataBaseMgr;

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

        // On récupère la liste des noms des id, noms d'image, etat, joueur en fonction du joueur
        //On récupère le tour de jeu
        SharedPreferences countTurn = getSharedPreferences(MainActivity.GAME_TURN, Activity.MODE_PRIVATE);
        String sTurn = countTurn.getString("Tour de Jeu", "1");
        int turn = Integer.valueOf(sTurn);
        Log.i("Turn : ", sTurn);

        //Je crée une liste qui accueillera mes toString
        List<String> lImgs = new ArrayList<>();

        //Initier une instance de la BDD
        dataBaseMgr = new DataBaseMgr(this);

        //Si le tour de jeu est strictement divisible par 2, c'est au tour du joueur 2
        if(turn%2 == 0){
            //On récupère la liste des images en fonction du joueur 2
            List<ImageCase> imgs = dataBaseMgr.selectImg(2);
            for(ImageCase img: imgs){
                lImgs.add(img.toString());
                Log.i("Contenu BDD J2", img.toString());
            }
        }
        //Sinon c'est au joueur 1
        else{
            //On récupère la liste des images en fonction du joueur 1
            List<ImageCase> imgs = dataBaseMgr.selectImg(1);
            for(ImageCase img: imgs){
                lImgs.add(img.toString());
                Log.i("Contenu BDD J1", img.toString());
            }
        }

        dataBaseMgr.close();
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
            //Boucle qui va créer et insérer les images dans ma base de données

            //Une seconde boucle qui permet de créer les images dans les lignes
            for(int j=0;j<3;j++){

                //On crée le layout d'image
                LinearLayout buttonLayout = new LinearLayout(this);

                // On gère sa largeur et sa hauteur
                LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(300,300);
                String background = "person1";
                String path = "@drawable/"+background;

                //L'image est récupérée et placée en background sur le layout
                buttonLayout.setBackground(getDrawable(getResources().getIdentifier(path, null, getPackageName())));

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
                                buttonLayout.setBackground(getDrawable(getResources().getIdentifier(path, null, getPackageName())));
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
            //Fermeture de la connection avec la base de données
            //dataBaseMgr.close();

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
        //Log.i("Count", newCount);
        //Permet d'arrêter la partie sans gagnants

        Intent intent = new Intent();
        if(count == 6){
            intent = new Intent(this, GoalActivity.class);

        }
        else{
            intent = new Intent(this, PlayerNameScreen.class);
        }
        startActivity(intent);
    }

}