package fr.amandine.qui_est_ce;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class PersonnageActivity extends AppCompatActivity {

    public Intent intent = null;
    //Fonctions
    @Override
    /**
     * est appelée à la création de PersonnageActivity
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personnage);

        //Données membres
        //On récupère le textview dans lequel on affichera le nom du joueur
        //dont l'image est associée
        TextView tvName = (TextView) findViewById(R.id.tName);
        //On instancie DataBaseMgr pour manipuler la BDD -> la table des imageCase
        DataBaseMgr dataBaseMgr;

        //On récupère l'imageView qui contiendra l'image choisie pour le joueur
        ImageView character = findViewById(R.id.perso);
        //On récupère les données des joueurs stockées dans les préférences
        SharedPreferences players = getSharedPreferences(MainActivity.PLAYERS, Activity.MODE_PRIVATE);

        //On initie une String qui contiendra le nom du joueur
        String strPlayer = "";

        //Récupérer les préférences concernant la partie
        SharedPreferences countTurn = getSharedPreferences(MainActivity.GAME_TURN, Activity.MODE_PRIVATE);
        //On récupère le tour de jeu
        String sTurn = countTurn.getString("Tour de Jeu", "1");
        //On le transforme en entier pour l'incrémenter
        int turn = Integer.valueOf(sTurn);

        //Log.i("Turn : ", sTurn);

        //Gérer le tour 1 du joueur 1
        //C'est le premier tour de jeu
        if(turn == 1){
            //Permet de générer un chiffre aléatoire en fonction du nombre d'images stockées
            double dNumber = Math.round(Math.random() * 66);

            //Format le double en string en enlevant les nombres après la virgule
            DecimalFormat f = new DecimalFormat();
            f.setMaximumFractionDigits(0);
            String sNumber = f.format(dNumber);

            //Construire le nom de l'image du joueur 1
            strPlayer = "person"+sNumber;

            //Ajouter ce nom d'image aux préférences du joueur
            SharedPreferences.Editor edPlayerOne = players.edit();
            edPlayerOne.putString("Joueur 1 charac", strPlayer).apply();

            //Afficher son nom à l'écran
            String strPlayer1 = players.getString("Joueur1","");
            tvName.setText(strPlayer1+",");

            //Afficher son image à l'écran
            String path = "@drawable/"+strPlayer;
            character.setImageResource(getResources().getIdentifier(path, null, getPackageName()));

            //Initier un intent pour retourner à l'annonce du tour des joueurs
            intent = new Intent(this, PlayerNameScreen.class);

        }
        //Gérer le tour 1 du joueur 2 = générer les cases des boardGame
        //C'est le deuxième tour sur cette page
        else if(turn == 2) {
            //Créer un tableau qui contient les noms d'images
            ArrayList<String> lImgName = new ArrayList<>();
            //Initier une instance de la BDD
            dataBaseMgr = new DataBaseMgr(this);
            //Récupérer le nom de l'image du joueur 1
            String strPlayerOne = players.getString("Joueur 1 charac", "");
            //L'ajouter à la liste de noms d'images
            lImgName.add(strPlayerOne);
            //L'ajouter à la BDD pour le joueur 1
            dataBaseMgr.insertImg(strPlayerOne, 1, 1);
            //L'ajouter à la BDD pour le joueur 2
            dataBaseMgr.insertImg(strPlayerOne, 1, 2);
            //Initier un nom pour l'image du joueur 2 à strPlayer1
            String strPlayerTwo = strPlayerOne;
            //Tant que ce nom est identique à strPlay1
            while (strPlayerTwo == strPlayerOne) {
                //Générer un chiffre aléatoire
                double dNumber = Math.round(Math.random() * 66);
                //Construire le nom du joueur 2
                //Format le double en string en enlevant les nombres après la virgule
                DecimalFormat f = new DecimalFormat();
                f.setMaximumFractionDigits(0);
                String sNumber = f.format(dNumber);

                //Construire le nom de l'image du joueur 1
                strPlayerTwo = "person" + sNumber;
            }
            //Une fois qu'il est différent
            //Ajouter le nom à la BDD pour le joueur 2
            dataBaseMgr.insertImg(strPlayerTwo, 1, 2);
            //Ajouter le nom à la BDD pour le joueur 1
            dataBaseMgr.insertImg(strPlayerTwo, 1, 1);
            //Ajouter le nom à la liste des noms d'image
            lImgName.add(strPlayerTwo);
            //Stocker le nom de l'image du joueur 2
            SharedPreferences.Editor edPlayerTwo = players.edit();
            edPlayerTwo.putString("Joueur 2 charac", strPlayerTwo).apply();
            // Faire une boucle qui générera les 16 autres images
            for (int i = 0; i < 16; i++) {
                //Générer un chiffre aléatoire
                double dNumber = Math.round(Math.random() * 66);
                //Le transformer en chaine de caractères
                DecimalFormat f = new DecimalFormat();
                f.setMaximumFractionDigits(0);
                String sNumber = f.format(dNumber);
                //Le formater en nom d'image
                strPlayer = "person" + sNumber;
                //S'il n'est pas déjà dans la liste de nom
                if (!lImgName.contains(strPlayer)) {
                    //L'ajouter à la BDD avec joueur 1
                    dataBaseMgr.insertImg(strPlayer, 1, 1);
                    //L'ajouter à la  BDD avec joueur 2
                    dataBaseMgr.insertImg(strPlayer, 1, 2);
                    //L'ajouter à la liste des noms d'image
                    lImgName.add(strPlayer);
                }
                //sinon
                else {
                    //On décrémente pour avoir le bon nombre d'images
                    i--;
                }
            }
            //Fermer la connexion à la BDD
            dataBaseMgr.close();

            //Afficher le nom du joueur 2 à l'écran
            String strPlayer2 = players.getString("Joueur2","");
            tvName.setText(strPlayer2+", ");

            //Afficher l'image du joueur 2 à l'écran
            String path = "@drawable/"+strPlayerTwo;
            character.setImageResource(getResources().getIdentifier(path, null, getPackageName()));

            //Mettre dans les préférences les vies des 2 joueurs
            SharedPreferences.Editor edPlayersLife = players.edit();
            edPlayersLife.putString("Joueur 1 life", "3").apply();
            edPlayersLife.putString("Joueur 2 life", "3").apply();

            //Initier un intent pour aller à la page du plateau de jeu
            intent = new Intent(this, PlayerNameScreen.class);
        }

    }

    /**
     * Permet de passer à l'activité suivante
     * @param v
     */
    public void ready(View v){
        //Je peux passer à l'activité suivante
        startActivity(intent);
    }

    /**
     * Permet de retourner à l'acceuil et d'écraser la pile d'activités
     * @param v
     */
    public void clicQuit(View v){
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}