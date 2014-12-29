package com.example.vocabbook.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class WordsHelper extends SQLiteOpenHelper {
	
	public static final String TABLE_NAME = "Words";
	public static final String COLUMN_ID = "Id";
	public static final String COLUMN_WORD = "Word";
	public static final String COLUMN_MEANING = "Meaning";
	public static final String COLUMN_LANGUAGE = "Language";
	
	private static final String DATABASE_NAME = "words.db";
	private static final int DATABASE_VERSION = 1;
	
	// Database creation sql statement
	private static final String DATABASE_CREATE = "create table "
	      + TABLE_NAME + "(" + 
	      COLUMN_ID + " integer primary key AUTOINCREMENT," + 
	      COLUMN_WORD + " text not null," +
	      COLUMN_MEANING + " text," +
	      COLUMN_LANGUAGE +  " text" +
	      				");";

	public WordsHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
	
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.w(WordsHelper.class.getName(), DATABASE_CREATE);
		db.execSQL(DATABASE_CREATE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(WordsHelper.class.getName(),
		        "Upgrading database from version " + oldVersion + " to "
		            + newVersion + ", which will destroy all old data");
		    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		    onCreate(db);
	}

}
