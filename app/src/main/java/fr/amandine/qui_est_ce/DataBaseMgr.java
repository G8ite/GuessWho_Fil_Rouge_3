package fr.amandine.qui_est_ce;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import fr.amandine.qui_est_ce.Class.ImageCase;

public class DataBaseMgr extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Game.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TAG = "Database";
    public static final String NAME_TABLE_1 = "gameboard";

    public DataBaseMgr(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    /**
     * Permet de créer une table gameboard dans la basse de données Game.db
     */
    public void onCreate(SQLiteDatabase db){
        //requête SQL créant à table gameBoard
        String strSql = "CREATE TABLE "+NAME_TABLE_1+ " ( " +
                "  idImg INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "  nameImg TEXT NOT NULL, " +
                "  etatImg INTEGER NOT NULL, " +
                " joueurImg INTEGER NOT NULL "+
                " )";

        //On execute la requête
        db.execSQL(strSql);
        //Log.i(TAG, "oncreate ok");
    }

    /**
     * Permet de la mise à jour de la base de données
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String strSql = "DROP TABLE gameboard";
        db.execSQL(strSql);
        this.onCreate(db);
        //Log.i(TAG, "onupgrade ok");
    }

    /**
     * Permet d'insérer une nouvelle case dans la base de données en donnant son image et son état
     * @param str
     * @param etat
     * @param joueur
     */
    public void insertImg(String str, int etat, int joueur){

        //Requête permettant d'ajouter une image à la BDD
        String strSql = "INSERT INTO " + NAME_TABLE_1+ "(nameImg , etatImg, joueurImg) VALUES"
                +"( \""+str+"\","+ etat +","+ joueur + " );";

        //On rend la BDD éditable et on execute la requête
        this.getWritableDatabase().execSQL(strSql);
        //Log.i(TAG, "insertImg ok");
    }


    /**
     * Permet de récupérer une liste d'ImageCase présente dans la BDD en fonction du joueur
     * @param joueur
     * @return
     */
    public List<ImageCase> selectImg(int joueur){
        //On initie une liste
        List<ImageCase> imgs = new ArrayList<>();

        //On prépare la requête
        String strSql = "SELECT * FROM "+NAME_TABLE_1+" WHERE joueurImg  = "+ joueur;
        //On initie le curceur, on rend la BDD lisible et on parcours chaque ligne
        Cursor c = this.getReadableDatabase().rawQuery(strSql, null);
        //On commence par la première ligne
        c.moveToFirst();
        //Tant qu'on est pas après la dernière ligne disponible
        while(!c.isAfterLast()){
            //On crée un nouvel objet ImageCase avec les données récupérées dans la BDD
           ImageCase img = new ImageCase(c.getInt(0),c.getString(1),
                   c.getInt(2), c.getInt(3));
           //On ajoute cet objet à notre liste d'objets
           imgs.add(img);
           //On passe à la ligne suivante
           c.moveToNext();
        }
        //On ferme notre curseur
        c.close();
        //On retourne notre liste d'objets
        return imgs;
    }

    /**
     * Supprimer la table dont le nom est passé en paramètre
     * @param str
     */
    public void deleteTable(String str){
        String strSql = "DELETE FROM "+str;
    }

    /**
     * Fonction permettant de changer l'état d'une case lorsqu'elle est cliquée en fonction de son id
     * @param etat
     * @param id
     */
    public void changeState(int etat, int id){
        //On prépare la requête sql
        String strSql = "UPDATE "+NAME_TABLE_1+" SET etatImg = "+etat+" WHERE idImg ="+id+";";

        //On rend la BDD éditable et on excute la requête
        this.getWritableDatabase().execSQL(strSql);

        //Log.i(TAG, "changeStat ok");
    }

}
