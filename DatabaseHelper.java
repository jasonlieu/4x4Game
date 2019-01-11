package com.example.jason.a4x4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by JASON on 1/9/2019.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "score.db";


    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);


        //onUpgrade(this.getWritableDatabase(), 1, 1);      //reset db
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        //Create table- scoreTable
        //Pkey - ID , int
        //column1 - score , int
        db.execSQL("CREATE TABLE scoreTable (ID INTEGER PRIMARY KEY AUTOINCREMENT, SCORE LONG)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS scoreTable");
        onCreate(db);
    }
    public boolean insert(long score){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("SCORE", score);
        long result = db.insert("scoreTable", null, contentValues);
        if(result != -1){
            return true;
        }
        return false;
    }
    public Cursor getTopScores(){ //return top 10 scores
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor score = db.rawQuery("SELECT SCORE FROM scoreTable LIMIT 10", null);
        return score;
    }
}
