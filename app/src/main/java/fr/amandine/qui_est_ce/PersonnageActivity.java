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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personnage);
        Log.i("blabla", "blabla");

        //Données membres
        TextView tvName = (TextView) findViewById(R.id.tName);
        DataBaseMgr dataBaseMgr;

        ImageView character = findViewById(R.id.perso);
        SharedPreferences players = getSharedPreferences(MainActivity.PLAYERS, Activity.MODE_PRIVATE);

        String strPlayer = "";

        //Récupérer le tour de jeu
        SharedPreferences countTurn = getSharedPreferences(MainActivity.GAME_TURN, Activity.MODE_PRIVATE);
        String sTurn = countTurn.getString("Tour de Jeu", "1");
        int turn = Integer.valueOf(sTurn);
        Log.i("Turn : ", sTurn);

        //Gérer le tour 1 du joueur 1
        if(turn == 1){

            //Permet de générer un chiffre aléatoire en fonction du nombre d'images stockées
            double dNumber = Math.round(Math.random() * 66);
            //Format le double en string en enlevant les nombres après la virgule
            DecimalFormat f = new DecimalFormat();
            f.setMaximumFractionDigits(0);
            String sNumber = f.format(dNumber);

            //Construire le nom de l'image du joueur 1
            strPlayer = "person"+sNumber;

            //Ajouter ce nom à mes préférences
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

            //Initier un intent pour aller à la page du plateau de jeu
            intent = new Intent(this, PlayerBoardGame.class);
        }
        //Gérer le tour 2 du joueur 2
        //Gérer les autres tours du joueur 1
        //Gérer les autres tours du joueur 2
        /*//Instanciation de la base de données
        dataBaseMgr = new DataBaseMgr(this);

        //Créer un tableau qui contiendra les numéros des images déjà dans le tableau
        List<String> lNumber = new ArrayList<String>();
        //Créer un tableau qui contient les noms d'images
        ArrayList<String> lImgName = new ArrayList<>();

        //Permet de générer un chiffre aléatoire en fonction du nombre d'images stockées
        double dNumber = Math.round(Math.random() * 66);
        //Format le double en string en enlevant les nombres après la virgule
        DecimalFormat f = new DecimalFormat();
        f.setMaximumFractionDigits(0);
        String sNumber = f.format(dNumber);

        //Créer un booléen qui permet de vérifier si l'identifiant pris au hasard n'est pas déjà présent dans la grille
        boolean verify = lNumber.contains(sNumber);//il faudra qu'il contienne pas le nombre mais le nom de l'image en entier
        //Si il y est déjà

        String strPlayer1="person"+sNumber; // j'écris le chemin pour récupérer mon image
        String path = "@drawable/"+strPlayer1;

        // Récupérer les préférences concernant les joueurs
        SharedPreferences players = getSharedPreferences(MainActivity.PLAYERS, Activity.MODE_PRIVATE);
        SharedPreferences.Editor edPlayerChar = players.edit();

        // j'inst
        //Vérifier si un joueur 1 a déjà été créé et agir en conséquences
        if(players.contains("Joueur 1 charac")){
            edPlayerChar.putString("Joueur 2 charac",strPlayer1).apply();
            // C'est l'image du joueur 2, la prochaine activité est le plateau de jeu
            //Je charge donc la base de données pour me préparer à l'afficher
            //J'insère le nom de l'image du J2 dans ma BDD pour le plateau de jeu J1
            dataBaseMgr.insertImg(strPlayer1, 1, 2);
            //Je l'ajoute à ma liste de nom d'images
            lImgName.add(strPlayer1);

            for (int i = 0; i < 16; i++) {
                //Il faut générer 16 noms d'image aléatoires à insérer dans la base de données

                //Permet de générer un chiffre aléatoire en fonction du nombre d'images stockées
                dNumber = Math.round(Math.random() * 66);
                //Format le double en string en enlevant les nombres après la virgule
                f = new DecimalFormat();
                f.setMaximumFractionDigits(0);
                sNumber = f.format(dNumber);

                if (!verify) {
                    // je l'ajoute à la liste
                    lNumber.add(sNumber);
                    Log.i("Image Joueur",sNumber);

                    // j'écris le chemin pour récupérer mon image
                    imgName = "person" + sNumber;
                    Log.i("Image Joueur",imgName);

                    // Je vérifie que le nom ne se trouve pas déjà dans ma liste
                    // est n'est pas égale à l'image de J1
                    if(!lImgName.contains(imgName) && !imgName.equals(strPlayer1)) {
                        //J'ajoute le nom des images à la liste
                        lImgName.add(imgName);
                        Log.i("Image Joueur","ça passe");
                        //J'ajoute chaque image à ma BDD à l'état 1 pour le J1 et le J2 (face visible)
                        dataBaseMgr.insertImg(imgName, 1, 1);
                        dataBaseMgr.insertImg(imgName, 1, 2);
                    }
                    else{// Si y est ou est égale à l'image de J1
                        i--;
                        Log.i("Image Joueur","ça passe pas");
                    }
                }
                else{
                    i--;
                    Log.i("Image Joueur", "nombre déjà dans la liste");
                }

            }
            //J'ai donc récupéré tous les noms images présentes dans mon plateau de jeu

            intent = new Intent(this, PlayerBoardGame.class);
        }
        else{
            //J'insère le nom de l'image du J1 dans ma BDD pour le plateau de jeu J1
            dataBaseMgr.insertImg(strPlayer1, 1, 1);
            //Je l'ajoute à ma liste de nom d'images
            edPlayerChar.putString("Joueur 1 charac",strPlayer1).apply();
            intent = new Intent(this, PlayerNameScreen.class);
        }

        //Joueur ils sont stockés dans les preferences plutot que dans SQLite
        //en cas de retour à cette page
        strPlayer1 = players.getString("Joueur1","");
        character.setImageResource(getResources().getIdentifier(path, null, getPackageName()));

        SharedPreferences player1 = getSharedPreferences(MainActivity.PLAYERS, Activity.MODE_PRIVATE);
        strPlayer1 = player1.getString("Joueur1","");
        tvName = (TextView) findViewById(R.id.tName);

        tvName.setText(strPlayer1+",");

        //Fermeture de la connection avec la base de données
        dataBaseMgr.close();*/
    }
    public void ready(View v){
        /*
        //Créer un tableau qui contiendra les numéros des images déjà dans le tableau
        List<String> lNumber = new ArrayList<String>();
        //Créer un tableau qui contient les noms d'images
        ArrayList<String> lImgName = new ArrayList<>();

        //Instanciation de la base de données
        dataBaseMgr = new DataBaseMgr(this);

            //Effacer les données de la table au cas où
            dataBaseMgr.deleteTable(DataBaseMgr.NAME_TABLE_1);// CA NE FONCTIONNE PAS
            //C'est le premier tour de jeu on crée le plateau de J1
            // Récupérer les noms des images des joueurs
            SharedPreferences players = getSharedPreferences(MainActivity.PLAYERS, Activity.MODE_PRIVATE);
            //Je récupère le nom d'image choisi pour J1
            String strPlayer1 = players.getString("Joueur 1 charac", "");
            Log.i("Image Joueur 1", strPlayer1);
            //Je l'insère dans ma BDD pour le plateau de jeu J1
            dataBaseMgr.insertImg(strPlayer1, 1, 1);
            //Je l'insère dans ma BDD pour le plateau de jeu J2
            dataBaseMgr.insertImg(strPlayer1, 1, 2);
            //Je l'ajoute à ma liste de nom d'images
            lImgName.add(strPlayer1);
            //Je récupère le nom d'image choisi pour J2
            String strPlayer2 = players.getString("Joueur 2 charac", "");
            Log.i("Image Joueur 2", strPlayer2);
            //Je l'insère dans ma BDD pour le plateau de jeu J1
            dataBaseMgr.insertImg(strPlayer2, 1,1);
            //Je l'insère dans ma BDD pour le plateau de jeu J2
            dataBaseMgr.insertImg(strPlayer2, 1,2);
            //Je l'ajoute à ma liste de nom d'images
            lImgName.add(strPlayer2);

            for (int i = 0; i < 16; i++) {
                //Il faut générer 16 noms d'image aléatoires à insérer dans la base de données

                //Permet de générer un chiffre aléatoire en fonction du nombre d'images stockées
                double dNumber = Math.round(Math.random() * 66);
                //Format le double en string en enlevant les nombres après la virgule
                DecimalFormat f = new DecimalFormat();
                f.setMaximumFractionDigits(0);
                String sNumber = f.format(dNumber);

                //Créer un booléen qui permet de vérifier si l'identifiant pris au hasard n'est pas déjà présent dans la grille
                boolean verify = lNumber.contains(sNumber);//il faudra qu'il contienne pas le nombre mais le nom de l'image en entier
                //Si il y est déjà

                String imgName = "";
                if (!verify) {
                    // je l'ajoute à la liste
                    lNumber.add(sNumber);
                    Log.i("Image Joueur",sNumber);

                    // j'écris le chemin pour récupérer mon image
                    imgName = "person" + sNumber;
                    Log.i("Image Joueur",imgName);

                    // Je vérifie que le nom ne se trouve pas déjà dans ma liste
                    if(!lImgName.contains(imgName)) {
                        //J'ajoute le nom des images à une liste
                        lImgName.add(imgName);
                        Log.i("Image Joueur","ça passe");
                        //J'ajoute chaque image à ma BDD à l'état 1 pour le J1 et le J2 (face visible)
                        dataBaseMgr.insertImg(imgName, 1, 1);
                        dataBaseMgr.insertImg(imgName, 1, 2);
                    }
                    else{// Si elle y est pas
                        i--;
                        Log.i("Image Joueur","ça passe pas");

                    }
                }
                else{
                    i--;
                    Log.i("Image Joueur", "nombre déjà dans la liste");
                }

            }*/
            //J'ai donc récupéré tous les noms images présentes dans mon plateau de jeu
        //Je peux passer à l'activité suivante
        startActivity(intent);
    }
}