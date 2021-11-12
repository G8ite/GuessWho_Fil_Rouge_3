package fr.amandine.qui_est_ce;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseMgr extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Game.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TAG = "Database";
    private static final String NAME_TABLE_1 = "gameboard";

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
                "  etatImg INTEGER NOT NULL " +
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
     */
    public void insertImg(String str, int etat){
        String strSql = "INSERT INTO " + NAME_TABLE_1+ "(nameImg , etatImg) VALUES"
                +"( \""+str+"\","+ etat + " );";
        this.getWritableDatabase().execSQL(strSql);

        //Log.i(TAG, "insertImg ok");
    }

    /*public String selectImg(int id){
        String strSql = "SELECT * FROM "+NAME_TABLE_1+" WHERE idImg = "+id;
        String result = this.getWritableDatabase().execSQL(strSql);
    }*/
    /**
     * Fonction permettant de changer l'état d'une case lorsqu'elle est cliquée en fonction de son nom
     * @param etat
     * @param str
     */
    public void changeStat(int etat, String str){
        String strSql = "UPDATE "+NAME_TABLE_1+" SET etatImg = "+etat+" WHERE nameImge = \""+str+"\"";
        this.getWritableDatabase().execSQL(strSql);

        //Log.i(TAG, "changeStat ok");
    }

}
