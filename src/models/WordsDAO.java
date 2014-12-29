package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class WordsDAO {
	private SQLiteDatabase db;
	private WordsHelper dbHelper;
	private String[] allColumns = { WordsHelper.COLUMN_ID, WordsHelper.COLUMN_WORD, WordsHelper.COLUMN_MEANING, WordsHelper.COLUMN_LANGUAGE };
	
	public WordsDAO(Context context) {
		this.dbHelper = new WordsHelper(context);
	}
	
	public void open() throws SQLException {
		this.db = dbHelper.getWritableDatabase();
	}
	
	public void close() {
		this.db.close();
	}
	
	public Word addWord(String word) {
		ContentValues wordValues = new ContentValues();
		wordValues.put(WordsHelper.COLUMN_WORD, word);
		long insertId = this.db.insert(WordsHelper.TABLE_NAME, null, wordValues);
		Word wordObj = new Word();
		wordObj.setWord(word);
		return wordObj;
	}
	
	public Word addWord(String word, String meaning) {
		ContentValues values = new ContentValues();
		values.put(WordsHelper.COLUMN_WORD, word);
		values.put(WordsHelper.COLUMN_MEANING, meaning);		
		
		long insertId = this.db.insert(WordsHelper.TABLE_NAME, null, values);
		Word wordObj = new Word();
		wordObj.setWord(word);
		return wordObj;
	}
	
	public Word addWord(String word, String meaning, String langCode) {
		ContentValues values = new ContentValues();
		values.put(WordsHelper.COLUMN_WORD, word);
		values.put(WordsHelper.COLUMN_MEANING, meaning);
		values.put(WordsHelper.COLUMN_LANGUAGE, langCode);
		
		long insertId = this.db.insert(WordsHelper.TABLE_NAME, null, values);
		Word wordObj = new Word();
		wordObj.setWord(word);
		return wordObj;
	}
	
	public void deleteWord(Word word) {
		long id = word.getId();
		System.out.println("Deleted " + word.getWord() + " with ID " + word.getId());
		db.delete(dbHelper.TABLE_NAME, dbHelper.COLUMN_ID + " = " + id, null);
	}
	
	public List<Word> getAllWords() {
		List<Word> allWords = new ArrayList<Word>();
		
		Cursor cursor = db.query(dbHelper.TABLE_NAME, allColumns, null, null, null, null, null);
		
		cursor.moveToFirst();
		while(!cursor.isAfterLast()) {
			Word word = cursorToWord(cursor);
			allWords.add(word);
			cursor.moveToNext();
		}
		
		cursor.close();
		return allWords;
	}
	
	private Word cursorToWord(Cursor cursor) {
		long id = cursor.getLong(0);
		String word = cursor.getString(1);
		String meaning = cursor.getString(2);
		String language = cursor.getString(3);
		Word newWord = new Word();
		newWord.setId(id);
		newWord.setWord(word);
		newWord.setMeaning(meaning);
		newWord.setLanguage(language);
		return newWord;
	}
	
}
