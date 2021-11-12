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
    public void onCreate(SQLiteDatabase db){
        String strSql = "CREATE TABLE "+NAME_TABLE_1+ " ( " +
                "  idImg int(3) AUTO_INCREMENT, " +
                "  nameImg varchar(20) NOT NULL, " +
                "  etatImg int(1) NOT NULL, " +
                "  PRIMARY KEY (`idImg`) )";

        db.execSQL(strSql);

        //Log.i(TAG, "oncreate ok");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String strSql = "DROP TABLE gameboard";
        db.execSQL(strSql);
        this.onCreate(db);

        //Log.i(TAG, "onupgrade ok");
    }

    public void insertImg(String str, int etat){
        String strSql = "INSERT INTO " + NAME_TABLE_1+ "(nameImg , etatImg) VALUES"
                +"( "+str+","+ etat + " );";
        this.getWritableDatabase().execSQL(strSql);

        //Log.i(TAG, "insertImg ok");
    }
}
