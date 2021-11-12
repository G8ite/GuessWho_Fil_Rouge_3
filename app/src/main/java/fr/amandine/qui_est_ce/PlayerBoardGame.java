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

        /*
        //Créer un tableau qui contiendra les numéros des images déjà dans le tableau
        List<String> lNumber = new ArrayList<String>();
        //Créer un tableau qui contient les noms d'images
        ArrayList<String> lImgName = new ArrayList<>();

        //Instanciation de la base de données
        dataBaseMgr = new DataBaseMgr(this);

        //Test de le fonction insert
        //dataBaseMgr.insertImg("blibli", 1);

        // Récupérer le tour de jeu
        SharedPreferences countTurn = getSharedPreferences(MainActivity.GAME_TURN, Activity.MODE_PRIVATE);
        String sTurn = countTurn.getString("Tour de Jeu", "1");
        int turn = Integer.valueOf(sTurn);

        //C'est un tour de chiffre impair différent de 1 alors on charge le plateau de J1
        Log.i("Image Joueur ", "ca continue");

        //Vérifier le tour de jeu pour savoir s'il faut créer ou charger le plateau de jeu
        //et s'il faut le faire pour le joueur 1 ou pour le joueur 2
        //Initier une liste qui contiendra les noms des images, l'état et le joueur
        List<String> imgName = new ArrayList<String>();

        Log.i("Img Joueur - t1", sTurn);

        if(turn == 3) {
            Log.i("Img Joueur - t2", sTurn);
            List<ImageCase> imgs = dataBaseMgr.selectImg(1);
            for (ImageCase img : imgs ){
                Log.i("Img Joueur", img.toString());
                imgName.add(img.toString());
            }
            Log.i("Img Joueur", "tour 1 j 1");
        }
        else if(turn == 4){
            Log.i("Img Joueur - t3", sTurn);
            /*List<ImageCase> imgs = dataBaseMgr.selectImg(2);
            for (ImageCase img : imgs ){
                Log.i("Img Joueur", img.toString());
                imgName.add(img.toString());
            }
            Log.i("Img Joueur", "tour 1 j 2");
        }
        else{
            Log.i("Img Joueur", "DAK");

        }*/
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
        if(count == 3){
            intent = new Intent(this, GoalActivity.class);

        }
        else{
            intent = new Intent(this, PlayerNameScreen.class);
        }
        startActivity(intent);
    }

}