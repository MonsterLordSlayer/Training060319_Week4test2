package com.example.week4test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static com.example.week4test.DatabaseContract.DATABASE_NAME;
import static com.example.week4test.DatabaseContract.FIELD_DESC;
import static com.example.week4test.DatabaseContract.FIELD_ID;
import static com.example.week4test.DatabaseContract.FIELD_NAME;
import static com.example.week4test.DatabaseContract.FIELD_URL;
import static com.example.week4test.DatabaseContract.TABLE_NAME;
import static com.example.week4test.DatabaseContract.whereClauseForUpdate;
import static com.example.week4test.DatabaseContract.*;


public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DatabaseContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DatabaseContract.getCreateTableStatement());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    //insert values into database
    public void insertCoffe(Coffe coffeToInsert) {
        //create content value which holds key value pairs, key
        //being the column in the db, and value being the value
        //associated with that column
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIELD_ID, coffeToInsert.getId());
        contentValues.put(FIELD_DESC, coffeToInsert.getDesc());
        contentValues.put(FIELD_URL, coffeToInsert.getImageUrl());
        contentValues.put(FIELD_NAME, coffeToInsert.getName());

        //Need to get a writable database
        SQLiteDatabase writableDatabase = this.getWritableDatabase();

        //insert into the database
        writableDatabase.insert(TABLE_NAME, null, contentValues);
        writableDatabase.close();
    }

    public Coffe queryForOneCoffeRecord(String title) {
        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        Coffe returnCoffe = null;

        Cursor cursor = readableDatabase.rawQuery(DatabaseContract.getSelectOneCoffeItem(title),null);

        if(cursor.moveToFirst()) {
            String descFromDB = cursor.getString(cursor.getColumnIndex(FIELD_DESC));
            String urlFromDB = cursor.getString(cursor.getColumnIndex(FIELD_URL));
            String idFromDB = cursor.getString(cursor.getColumnIndex(FIELD_ID));
            String nameFromDB = cursor.getString(cursor.getColumnIndex(FIELD_NAME));
            returnCoffe = new Coffe(descFromDB,urlFromDB,idFromDB,nameFromDB);
        }
        readableDatabase.close();
        return returnCoffe;
    }
    public boolean notEmpty(){
        ArrayList<Coffe> returnCoffeList = null;
        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        Cursor cursor = readableDatabase.rawQuery(DatabaseContract.getSelectAllCoffeItems(),null);
        return cursor.moveToFirst();
    }
    public ArrayList<Coffe> queryForAllCoffeRecords() {
        ArrayList<Coffe> returnCoffeList = null;
        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        Cursor cursor = readableDatabase.rawQuery(DatabaseContract.getSelectAllCoffeItems(),null);

        if(cursor.moveToFirst()) {
            returnCoffeList = new ArrayList<>();
            do {
                Coffe returnCoffe = null;
                String descFromDB = cursor.getString(cursor.getColumnIndex(FIELD_DESC));
                String urlFromDB = cursor.getString(cursor.getColumnIndex(FIELD_URL));
                String idFromDB = cursor.getString(cursor.getColumnIndex(FIELD_ID));
                String nameFromDB = cursor.getString(cursor.getColumnIndex(FIELD_NAME));
                returnCoffe = new Coffe(descFromDB,urlFromDB,idFromDB,nameFromDB);
                returnCoffeList.add(returnCoffe);
            } while(cursor.moveToNext());
        }
        readableDatabase.close();
        return returnCoffeList;
    }

    public void updateCoffe(Coffe coffe) {
        SQLiteDatabase writableDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIELD_ID, coffe.getId());
        contentValues.put(FIELD_DESC, coffe.getDesc());
        contentValues.put(FIELD_URL, coffe.getImageUrl());
        contentValues.put(FIELD_NAME, coffe.getName());

        writableDatabase.update(TABLE_NAME, contentValues, whereClauseForUpdate(coffe.getId()), null);
        writableDatabase.close();
    }

    public void deleteCoffe(String title) {
        SQLiteDatabase writableDatabase = this.getWritableDatabase();
        writableDatabase.delete(TABLE_NAME, whereClauseForUpdate(title), null);
        writableDatabase.close();
    }
}
