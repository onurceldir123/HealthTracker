package com.example.healthtracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Health.db";
    public static final String TABLE_NAME = "health_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Tension";
    public static final String COL_3 = "BloodSugar";
    public static final String COL_4 = "HeartRate";
    public static final String COL_5 = "Weight";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_String = "CREATE TABLE " + TABLE_NAME + "(" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_2 + " INTEGER," + COL_3 + " INTEGER," + COL_4 +" INTEGER," + COL_5 + " INTEGER" +")";
        db.execSQL(SQL_String);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(int tension, int blood_sugar, int heart_rate, int weight ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,tension);
        contentValues.put(COL_3,blood_sugar);
        contentValues.put(COL_4,heart_rate);
        contentValues.put(COL_5,weight);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    public Cursor getAlldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE_NAME ,null);
        return res;
    }
}
