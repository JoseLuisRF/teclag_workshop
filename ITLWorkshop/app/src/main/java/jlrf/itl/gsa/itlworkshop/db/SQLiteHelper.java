package jlrf.itl.gsa.itlworkshop.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by joseluisrf on 5/23/15.
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    private static  final String DB_NAME = "itl_database";
    private static  final int DB_VERSION = 1;
    public SQLiteHelper(Context context) {

        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE mycontacts (" +
                " mycontact_id INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL," +
                " photo TEXT," +
                " thumbnail_photo TEXT," +
                " contact_name TEXT," +
                " brief_message TEXT," +
                " phone_number TEXT " +

                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS mycontacts");
        onCreate(db);
    }
}
