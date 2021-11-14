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
import android.net.nsd.NsdManager;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
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
    int longClickCount = 0;
    private DataBaseMgr dataBaseMgr;
    List<ImageCase> lImgs = new ArrayList<>();
    int lifeCount = 3;
    int joueur1button = 0;
    int joueur2button = 0;

    //Fonctions

    @Override
    /**
     * Fonction permettant de créer le plateau de jeu
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_board_game);

        //Récupérer le bouton de proposition
        Button propose = findViewById(R.id.propose);

        //Récupérer les vies des joueurs
        SharedPreferences PlayersLife = getSharedPreferences(MainActivity.PLAYERS, Activity.MODE_PRIVATE);
        String player1Life = PlayersLife.getString("Joueur 1 life", "");
        String player2Life = PlayersLife.getString("Joueur 2 life", "");

        //Récupérer les images views de vie des joueurs
        ImageView life2 = findViewById(R.id.life2);
        ImageView life3 = findViewById(R.id.life3);

        //Récupérer mes 18 cases que j'insère dans un tableau
        List<Button> lButton = new ArrayList<>();
        Button case1 = findViewById(R.id.id1);
        lButton.add(case1);
        Button case2 = findViewById(R.id.id2);
        lButton.add(case2);
        Button case3 = findViewById(R.id.id3);
        lButton.add(case3);
        Button case4 = findViewById(R.id.id4);
        lButton.add(case4);
        Button case5 = findViewById(R.id.id5);
        lButton.add(case5);
        Button case6 = findViewById(R.id.id6);
        lButton.add(case6);
        Button case7 = findViewById(R.id.id7);
        lButton.add(case7);
        Button case8 = findViewById(R.id.id8);
        lButton.add(case8);
        Button case9 = findViewById(R.id.id9);
        lButton.add(case9);
        Button case10 = findViewById(R.id.id10);
        lButton.add(case10);
        Button case11 = findViewById(R.id.id11);
        lButton.add(case11);
        Button case12 = findViewById(R.id.id12);
        lButton.add(case12);
        Button case13 = findViewById(R.id.id13);
        lButton.add(case13);
        Button case14 = findViewById(R.id.id14);
        lButton.add(case14);
        Button case15 = findViewById(R.id.id15);
        lButton.add(case15);
        Button case16 = findViewById(R.id.id16);
        lButton.add(case16);
        Button case17 = findViewById(R.id.id17);
        lButton.add(case17);
        Button case18 = findViewById(R.id.id18);
        lButton.add(case18);

        //Récupérer la liste des ImagesCases en fonction du joueur

        //Initier une instance de la BDD
        dataBaseMgr = new DataBaseMgr(this);

        //On récupère le tour de jeu
        SharedPreferences countTurn = getSharedPreferences(MainActivity.GAME_TURN, Activity.MODE_PRIVATE);
        String sTurn = countTurn.getString("Tour de Jeu", "1");
        int turn = Integer.valueOf(sTurn);
        //Log.i("Turn : ", sTurn);
        //J'inite une liste qui récupèrera les index des objets déjà utilisés
        List<Integer> lIndex = new ArrayList<>();

        //Si le tour de jeu est strictement divisible par 2, c'est au tour du joueur 2
        if(turn%2 == 0){
            //On charge les vies restantes du joueur 2
            if(player2Life.equals("2")){
                //Log.i("nombre vie J2 2 ", player2Life);

                life3.setImageResource(R.drawable.heartbroken);
            }
            else if(player2Life.equals("1")){
                //Log.i("nombre vie J2 1 ", player2Life);
                life2.setImageResource(R.drawable.heartbroken);
                life3.setImageResource(R.drawable.heartbroken);
            }
            else {
                Log.i("nombre vie J2 3 ", player2Life);
            }

            //On récupère la liste des images en fonction du joueur 2
            lImgs = dataBaseMgr.selectImg(2);
        }
        //Sinon c'est au joueur 1
        else{
            //On charge les vies restantes du joueur 1
            if(player1Life.equals("2")){
                Log.i("nombre vie J1 2 ", player1Life);
                life3.setImageResource(R.drawable.heartbroken);
            }
            else if(player1Life.equals("1")){
                Log.i("nombre vie J1 1 ", player1Life);
                life2.setImageResource(R.drawable.heartbroken);
                life3.setImageResource(R.drawable.heartbroken);
            }
            else {
                Log.i("nombre vie J1 3 ", player1Life);
            }
            //On récupère la liste des images en fonction du joueur 1
            lImgs = dataBaseMgr.selectImg(1);

        }

        //Je m'occupe de chaque case de mon plateau de jeu
        for(int k = 0; k<18; k++){
            //Récupérer un objets aléatoire dans ma liste d'objets ImageCase (index entre 1 et 18)
            int max = 18;
            int min = 1;
            Double dIndex = Math.random()*18;
            int index = dIndex.intValue();
            if(lIndex.contains(index)){
                //Log.i("index", "déjà pris");
                k--;
            }
            else {
                //Log.i("index", ""+k);
                //Log.i("index", ""+index);

                lIndex.add(index);
                //Je récupère le bouton concerné par la boucle
                Button buttonConcern = lButton.get(k);
                //Log.i("index", ""+buttonConcern.getId());
                //Je récupère l'objet correspondant à l'index aléatoire
                ImageCase img = lImgs.get(index);
                //Je récupère le nom de l'image à insérer dans le bouton
                String imgName = img.getNameImg();
                Log.i("index", imgName);
                //Pour les 2 premièrs tours, je stocke l'id des boutons
                //Puisque les 2 boutons modifiés seront ceux avec l'image correspondant
                //au joueur 1 et au joueur 2
                if(k == 0){
                    joueur1button = buttonConcern.getId();
                }
                if(k == 1){
                    joueur2button = buttonConcern.getId();
                }

                //Je crée mon chemin d'accès à l'image de mon bouton
                String path = "@drawable/"+imgName;
                //On récupère l'état de la carte
                int etat = img.getEtatImg();
                if(etat == 1){
                    //Je change le fond de mon image dynamiquement
                    buttonConcern.setBackground(getDrawable(getResources().getIdentifier(path, null, getPackageName())));
                    Log.i("index", "visible : "+etat);
                }
                else{
                    Log.i("index", "caché : "+etat);
                }
                //Ajouter un onclickListener sur chaque bouton
                buttonConcern.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        //L'image est face cachée
                        if (clickCount == 1) {
                            //le compteur de click est à 0
                            clickCount=0;
                            //Je remets l'image face visible
                            buttonConcern.setBackground(getDrawable(getResources().getIdentifier(path, null, getPackageName())));
                            //Je dois mettre à jour l'état de l'objet ImageCase correspondant
                            //Je récupère l'id de l'objet image
                            int idImg = img.getIdImg();
                            //Je change son état dans la BDD
                            dataBaseMgr.changeState(1,idImg);
                            //On change l'état de l'objet en attendant que la BDD se recharge
                            img.setEtatImg(1);
                            Log.i("index etat image : ", ""+img.getEtatImg());
                        }
                        //L'image est face visible
                        else {
                            //le compteur passe à 1
                            clickCount=1;
                            //Je mets l'image face cachée
                            buttonConcern.setBackground(getDrawable(R.drawable.card));
                            //Je dois mettre à jour l'état de l'objet ImageCase correspondant
                            //Je récupère l'id de l'objet image
                            int idImg = img.getIdImg();
                            //Je change son état dans la BDD
                            dataBaseMgr.changeState(0,idImg);
                            //On change l'état de l'objet en attendant que la BDD se recharge
                            img.setEtatImg(0);
                            Log.i("index etat face cach", ""+img.getEtatImg());
                        }
                    }
                });
                //Ajouter un onLongClickListener sur chaque bouton
                buttonConcern.setOnLongClickListener(new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        //Je récupère les préférences de tour de jeu
                        SharedPreferences selectedItems = getSharedPreferences(MainActivity.GAME_TURN, Activity.MODE_PRIVATE);
                        //Je les rends éditables
                        SharedPreferences.Editor edSelectItem = selectedItems.edit();
                        //Je change la couleur de mon bouton Proposer, sa vouleur de texte et le rends cliquable
                        propose.setBackgroundColor(getResources().getColor(R.color.green));
                        propose.setTextColor(getResources().getColor(R.color.white));
                        propose.setEnabled(true);

                        //Le bouton a déjà été cliqué longtemps
                        if(longClickCount == 1){
                            //Je réinitialise mon compteur
                            longClickCount=0;
                            //J'ajoute l'élément séléctionné à mes préférences
                            edSelectItem.putString("Selected", "").apply();
                            //Je change l'image de fond de mon bouton pour une image non grisée
                            buttonConcern.setBackground(getDrawable(getResources().getIdentifier(path, null, getPackageName())));
                        }
                        //Le bouton n'a pas été cliqué
                        else if (longClickCount == 0){
                            //Mon compteur est égal à 1
                            longClickCount=1;
                            //Je prépare le chemin d'accès de l'image en grisée
                            String path2 = path+"grey";
                            //Je stock l'image séléctionnée dans mes préférences
                            String selectedButton =  img.getNameImg();
                            edSelectItem.putString("Selected", selectedButton).apply();
                            //Je change le fond de mon bouton pour une image grisée
                            buttonConcern.setBackground(getDrawable(getResources().getIdentifier(path2, null, getPackageName())));
                        }

                        //Toast.makeText(getBaseContext(), selectedButton, Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

            }

        }

        //Fermeture de la connection avec la base de données
        dataBaseMgr.close();
    }


    /**
     * Permet de vérifier si la proposition du joueur est bonne
     */
    public void propose(View v){
        //On récupère le tour de jeu et le dernier bouton séléctionné
        SharedPreferences countTurn = getSharedPreferences(MainActivity.GAME_TURN, Activity.MODE_PRIVATE);
        String sTurn = countTurn.getString("Tour de Jeu", "1");
        String sSelectedButton = countTurn.getString("Selected","");
        int turn = Integer.valueOf(sTurn);
        //On récupère les informations sur les joueurs
        SharedPreferences joueursTurn = getSharedPreferences(MainActivity.PLAYERS, Activity.MODE_PRIVATE);
        String joueur1Name = joueursTurn.getString("Joueur1", "");
        String joueur1img = joueursTurn.getString("Joueur 1 charac", "");
        String joueur2Name = joueursTurn.getString("Joueur2", "");
        String joueur2img = joueursTurn.getString("Joueur 2 charac", "");
        //Pour modifier le nom du gagnant en fonction de la situation
        SharedPreferences.Editor edWinner = joueursTurn.edit();
        //Si c'est au tour du joueur 2
        if(turn%2==0){

            //Si les images correspondent
            if(sSelectedButton.equals(joueur1img)){
                //Je stocke le nom du vainqueur dans les données de joueurs
                edWinner.putString("Gagnant", joueur2Name).apply();
                //Je passe à la page de victoire
                Intent intent = new Intent(this, GoalActivity.class);
                startActivity(intent);

            }
            //Si elles ne correspondent pas
            else{
                //On récupère les vies restantes
                String joueur2life = joueursTurn.getString("Joueur 2 life", "");
                int iJoueur2life = Integer.valueOf(joueur2life);

                if(iJoueur2life == 1){
                    edWinner.putString("Gagnant",joueur1Name).apply();
                    Intent intent = new Intent(this, GoalActivity.class);
                    startActivity(intent);
                }
                else {
                    iJoueur2life --;
                    String sJoueur2life = Integer.toString(iJoueur2life);
                    edWinner.putString("Joueur 2 life",sJoueur2life).apply();
                    //Je passe à la page d'annonce du joueur suivant
                    Intent intent = new Intent(this, PlayerNameScreen.class);
                    startActivity(intent);
                }
            }
        }
        //Si c'est au tour du joueur 1
        else{
            Log.i("verif gagne", sSelectedButton);
            Log.i("verif gagne", joueur1img);

            if(sSelectedButton.equals(joueur2img)){
                //Je stocke le nom du vainqueur dans les données de joueurs
                edWinner.putString("Gagnant", joueur1Name).apply();
                //Je passe à la page de victoire
                Intent intent = new Intent(this, GoalActivity.class);
                startActivity(intent);

            }
            else{
                //On récupère les vies restantes
                String joueur1life = joueursTurn.getString("Joueur 1 life", "");
                int iJoueur1life = Integer.valueOf(joueur1life);

                if(iJoueur1life == 1){
                    edWinner.putString("Gagnant",joueur2Name).apply();
                    Intent intent = new Intent(this, GoalActivity.class);
                    startActivity(intent);
                }
                else {
                    iJoueur1life --;
                    String sJoueur1life = Integer.toString(iJoueur1life);
                    edWinner.putString("Joueur 1 life",sJoueur1life).apply();
                    //Je passe à la page d'annonce du joueur suivant
                    Intent intent = new Intent(this, PlayerNameScreen.class);
                    startActivity(intent);
                }
            }
        }

    }

    /**
     * Permet d'appeler l'activité qui rappelle au joueur son personnage
     * @param v
     */
    public void remember(View v){
        Intent intent = new Intent (this, Remember.class);
        startActivity(intent);
    }

    /**
     * Permet de passer au tour suivant
     * @param v
     */
    public void nextTurn(View v){
        Intent intent = new Intent(this, PlayerNameScreen.class);
        startActivity(intent);
    }

    /**
     * Permet de revenir à l'accueil
     * @param v
     */
    public void clicQuit(View v){
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}