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
        String strSql = "CREATE TABLE "+NAME_TABLE_1+ " ( " +
                "  idImg INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "  nameImg TEXT NOT NULL, " +
                "  etatImg INTEGER NOT NULL, " +
                " joueurImg INTEGER NOT NULL "+
                " )";

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
        String strSql = "INSERT INTO " + NAME_TABLE_1+ "(nameImg , etatImg, joueurImg) VALUES"
                +"( \""+str+"\","+ etat +","+ joueur + " );";
        this.getWritableDatabase().execSQL(strSql);

        //Log.i(TAG, "insertImg ok");
    }


    public List<ImageCase> selectImg(int joueur){
        List<ImageCase> imgs = new ArrayList<>();

        String strSql = "SELECT * FROM "+NAME_TABLE_1+" WHERE joueurImg  = "+ joueur;
        Cursor c = this.getReadableDatabase().rawQuery(strSql, null);
        c.moveToFirst();
        while(!c.isAfterLast()){
           ImageCase img = new ImageCase(c.getInt(0),c.getString(1), c.getInt(2), c.getInt(3));
           imgs.add(img);
           c.moveToNext();
        }
        c.close();
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
        String strSql = "UPDATE "+NAME_TABLE_1+" SET etatImg = "+etat+" WHERE idImg ="+id+";";
        this.getWritableDatabase().execSQL(strSql);

        //Log.i(TAG, "changeStat ok");
    }

}
