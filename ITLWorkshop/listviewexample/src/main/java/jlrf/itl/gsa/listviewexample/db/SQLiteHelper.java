package jlrf.itl.gsa.listviewexample.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import jlrf.itl.gsa.listviewexample.model.MyContact;

/**
 * Created by joseluisrf on 5/23/15.
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "itl_database";
    private static final int DB_VERSION = 1;

    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + MyContact.TABLE_NAME +  " ( " +
                MyContact.ID + " INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL," +
                MyContact.NAME + " TEXT," +
                MyContact.MAIL + " TEXT," +
                MyContact.AGE + " INTEGER" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + MyContact.TABLE_NAME);
        onCreate(db);
    }
}
