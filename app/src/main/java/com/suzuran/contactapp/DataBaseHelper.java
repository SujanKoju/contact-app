package com.suzuran.contactapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "contacts_db";
    public static final String TABLE_NAME = "contacts_table";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String PHONE = "phone";

    private SQLiteDatabase sqLiteDatabase;

    public static final String CREATE_TABLE = "CREATE TABLE  " + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY, " + NAME + " TEXT," + PHONE + " TEXT);";


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addContact(Contact contact) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.NAME, contact.name);
        contentValues.put(DataBaseHelper.PHONE, contact.phone);
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(DataBaseHelper.TABLE_NAME, null, contentValues);
    }

    public List<Contact> getAllContacts() {
        String selectAllContactsQuery = "SELECT * FROM " + TABLE_NAME;
        sqLiteDatabase = this.getReadableDatabase();
        List<Contact> contacts = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(selectAllContactsQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                String phone = cursor.getString(2);
                contacts.add(new Contact(id, name, phone));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contacts;
    }

    public void updateContact(Contact contact) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.NAME, contact.name);
        contentValues.put(DataBaseHelper.PHONE, contact.phone);
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.update(DataBaseHelper.TABLE_NAME, contentValues, ID + "=?", new String[]{String.valueOf(contact.getId())});
    }

    public void deleteContact(int id) {
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(DataBaseHelper.TABLE_NAME, ID + "=?", new String[]{String.valueOf(id)});
    }
}
