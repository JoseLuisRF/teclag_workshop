package jlrf.itl.gsa.listviewexample.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import jlrf.itl.gsa.listviewexample.model.MyContact;


/**
 * Created by joseluisrf on 5/23/15.
 */
public class MyContactsCmd {
    private static  final String TAG = MyContactsCmd.class.getSimpleName();

    private SQLiteHelper dbHelper;

    public MyContactsCmd(Context context){
        dbHelper = new SQLiteHelper(context);
    }

    public long insert(MyContact contact) throws Exception {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyContact.NAME, contact.getName());
        contentValues.put(MyContact.MAIL, contact.getMail());
        contentValues.put(MyContact.AGE, contact.getAge());
        long r = db.insert(MyContact.TABLE_NAME, null, contentValues);
        Log.i(TAG, "insert result::" + r);
        return r;
    }

    public List<MyContact> getContacts() throws Exception{
        List<MyContact> lstContacts = new ArrayList<MyContact>();
        SQLiteDatabase db =  dbHelper.getReadableDatabase();
        Cursor cursor = db.query(MyContact.TABLE_NAME,
                                null, null, null, null, null, null);
        while(cursor.moveToNext()){
            MyContact c = new MyContact();

            c.setName(cursor.getString(
                    cursor.getColumnIndex(MyContact.NAME)));

            c.setAge(cursor.getInt(cursor.getColumnIndex(
                    MyContact.AGE
            )));
            c.setMail(cursor.getString(cursor.getColumnIndex(
                    MyContact.MAIL
            )));

            lstContacts.add(c);
        }
        Log.i(TAG, "lstContacts.size()::" + lstContacts.size());
        return lstContacts;

    }

}
