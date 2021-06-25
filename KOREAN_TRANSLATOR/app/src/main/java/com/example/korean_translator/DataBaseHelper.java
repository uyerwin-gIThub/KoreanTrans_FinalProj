package com.example.korean_translator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    public  static  final String DATABASE_NAME = "Translator.db";
    public  static  final int VERSION = 1;

    public static final String DATABASE_TABLE_NAME = "LANGUAGE_DATA";
    public static final String ENGLISH_TERM = "ENGLISH";
    public static final String HANGUL_TERM = "HANGUL";
    public static final String ROMANIZED_TERM = "ROMANIZED";
    public static  final String CREATE_TABLE = "CREATE TABLE "+ DATABASE_TABLE_NAME +"(" + ENGLISH_TERM + " TEXT PRIMARY KEY," + HANGUL_TERM + " TEXT," + ROMANIZED_TERM + " TEXT);";

    public DataBaseHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_NAME);
    }

    public void add(DataBaseDatas EHR,SQLiteDatabase db){
        ContentValues CV = new ContentValues();
        CV.put(ENGLISH_TERM, EHR.getEnglishWord());
        CV.put(HANGUL_TERM, EHR.getHangulWord());
        CV.put(ROMANIZED_TERM, EHR.getRomanizedWord());
        db.insert(DATABASE_TABLE_NAME, null, CV);
    }

    public Cursor displayData(String search, SQLiteDatabase db){
        String[] columns = {HANGUL_TERM, ROMANIZED_TERM};
        String selections = ENGLISH_TERM + "=?";
        String[] selection_args = {search};
        Cursor cursor = db.query(DATABASE_TABLE_NAME,columns,selections,selection_args,null,null,null,null);
        return cursor;
     }

}