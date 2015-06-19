package jlrf.itl.gsa.itlworkshop.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import jlrf.itl.gsa.itlworkshop.model.MyContact;


/**
 * Created by joseluisrf on 5/23/15.
 */
public class MyContactsCmd {
    SQLiteHelper dbHelper;

    public MyContactsCmd(Context context){
        dbHelper = new SQLiteHelper(context);
    }
    public long insert(MyContact contact) throws Exception {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(MyContact.NAME, contact.getName());
        contentValues.put(MyContact.BRIEF_MESSAGE, contact.getBrief_message());
        contentValues.put(MyContact.PHONE_NUMBER, contact.getPhone_number());
        contentValues.put(MyContact.PHOTO, contact.getPhoto());
        contentValues.put(MyContact.THUMBNAIL_PHOTO, contact.getThumbnail_photo());

        return db.insert(MyContact.TABLE_NAME, null, contentValues);

    }

    public List<MyContact> getContacts() throws Exception{
        List<MyContact> lstContacts = new ArrayList<MyContact>();
        SQLiteDatabase db =  dbHelper.getReadableDatabase();
        Cursor cursor = db.query(MyContact.TABLE_NAME,
                                null, null, null, null, null, null);
        while(cursor.moveToNext()){
            MyContact c = new MyContact();
            c.setBrief_message(cursor.getString(
                    cursor.getColumnIndex(MyContact.BRIEF_MESSAGE)));
            c.setName(cursor.getString(cursor.getColumnIndex(
                    MyContact.NAME
            )));
            c.setPhone_number(cursor.getString(cursor.getColumnIndex(
                    MyContact.PHONE_NUMBER
            )));
            c.setPhoto(cursor.getString(cursor.getColumnIndex(
                    MyContact.PHOTO
            )));
            c.setThumbnail_photo(cursor.getString(cursor.getColumnIndex(
                    MyContact.THUMBNAIL_PHOTO
            )));
            lstContacts.add(c);
        }
        return lstContacts;

    }

}
