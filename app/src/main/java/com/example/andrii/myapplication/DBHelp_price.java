package com.example.andrii.myapplication;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelp_price extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Wednesday.db";
    public static final String TABLE_NAME = "price_new";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "PHOTO";
    public static final String COL_4 = "SIZE";
    public static final String COL_5 = "PRICE";
    public static final String COL_6 = "COUNT";
    public static final String COL_7 = "PIZZERIA";
    public DBHelp_price(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,PHOTO TEXT,SIZE TEXT,PRICE TEXT,COUNT INTEGER,PIZZERIA)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String name, String surname, String marks, String price, Integer i,String pizzeria) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);
        contentValues.put(COL_5,price);
        contentValues.put(COL_6,i);
        contentValues.put(COL_7,pizzeria);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    public boolean updateData(String name, String surname, String marks, String price, Integer i,String pizzeria ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);
        contentValues.put(COL_5,price);
        contentValues.put(COL_6,i);
        contentValues.put(COL_7,pizzeria);
        long result = db.update(TABLE_NAME, contentValues,"NAME=? AND SIZE=? AND PIZZERIA=?",new String[]{name,marks,pizzeria});
        if(result == -1)
            return false;
        else
            return true;



    }
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
    public void removeAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
    public boolean deleteData(String name,String size,String pizzeria){
        SQLiteDatabase db = this.getWritableDatabase();
        long resula = db.delete(TABLE_NAME, "NAME=? AND SIZE=? AND PIZZERIA=?",new String[]{name,size,pizzeria});
        if (resula == -1){
            return false;
        }else {
            return true;
        }
    }
}
