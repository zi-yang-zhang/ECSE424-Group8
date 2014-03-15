package com.example.rehand;

import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ScoreDatabaseHelper extends SQLiteOpenHelper{
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "ScoreDB";
    

    public ScoreDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);  
    }

	@Override
	public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        String CREATE_SCORE_TABLE = "CREATE TABLE scores ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " + 
                "benchmark REAL, "+
                "currentProgress REAL, "+
                "currentScore REAL, "+
                "previousScore REAL, "+
                "beforePreviousScore REAL, )";
 
        // create books table
        db.execSQL(CREATE_SCORE_TABLE);
	}
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS scores");
 
        // create fresh books table
        this.onCreate(db);
    }
    
    /**
     * CRUD operations (create "add", read "get", update, delete) book + get all scores + delete all scores
     */
 
    // Books table name
    private static final String TABLE_SCORES = "scores";
 
    // Books Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_BENCHMARK = "benchmark";
    private static final String KEY_CURRENTPROGRESS = "currentProgress";
    private static final String KEY_CURRENTSCORE = "currentScore";
    private static final String KEY_PREVIOUSSCORE = "previousScore";
    private static final String KEY_BEFOREPREVIOUSSCORE = "beforePreviousScore";
    private static final String[] COLUMNS = {KEY_ID,KEY_BENCHMARK,KEY_CURRENTPROGRESS,KEY_CURRENTSCORE,KEY_PREVIOUSSCORE,KEY_BEFOREPREVIOUSSCORE};
    public void addResult(ExerciseResult result){
        //for logging
		//Log.d("addBook", book.toString()); 
		
		// 1. get reference to writable DB
		SQLiteDatabase db = this.getWritableDatabase();
		
		// 2. create ContentValues to add key "column"/value
		ContentValues values = new ContentValues();
		values.put(KEY_BENCHMARK, result.getBenchmark()); // get benchmark
		values.put(KEY_CURRENTPROGRESS, result.getCurrentProgress());
		values.put(KEY_CURRENTSCORE, result.getCurrentScore());
		values.put(KEY_PREVIOUSSCORE, result.getPreviousScore());
		values.put(KEY_BEFOREPREVIOUSSCORE, result.getBeforePreviousScore());

		// 3. insert
		db.insert(TABLE_SCORES, // table
		        null, //nullColumnHack
		        values); // key/value -> keys = column names/ values = column values
		
		// 4. close
		db.close(); 
		}
    public ExerciseResult getResult(int id){
    	 
        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();
     
        // 2. build query
        Cursor cursor = 
                db.query(TABLE_SCORES, // a. table
                COLUMNS, // b. column names
                " id = ?", // c. selections 
                new String[] { String.valueOf(id) }, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
     
        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();
     
        // 4. build book object
        ExerciseResult result = new ExerciseResult();
        result.setId(Integer.parseInt(cursor.getString(0)));
        result.setBenchmark(Double.parseDouble(cursor.getString(1)));
        result.setCurrentProgress(Double.parseDouble(cursor.getString(2)));
        result.setCurrentScore(Double.parseDouble(cursor.getString(3)));
        result.setPreviousScore(Double.parseDouble(cursor.getString(4)));
        result.setBeforePreviousScore(Double.parseDouble(cursor.getString(5)));
     
        //log 
    //Log.d("getBook("+id+")", book.toString());
     
        // 5. return book
        return result;
    }
    public List<ExerciseResult> getAllResults() {
        List<ExerciseResult> results = new LinkedList<ExerciseResult>();
  
        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_SCORES;
  
        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
  
        // 3. go over each row, build book and add it to list
        ExerciseResult result = null;
        if (cursor.moveToFirst()) {
            do {
            	result = new ExerciseResult();
                result.setId(Integer.parseInt(cursor.getString(0)));
                result.setBenchmark(Double.parseDouble(cursor.getString(1)));
                result.setCurrentProgress(Double.parseDouble(cursor.getString(2)));
                result.setCurrentScore(Double.parseDouble(cursor.getString(3)));
                result.setPreviousScore(Double.parseDouble(cursor.getString(4)));
                result.setBeforePreviousScore(Double.parseDouble(cursor.getString(5)));
             
  
                // Add book to books
                results.add(result);
            } while (cursor.moveToNext());
        }
  
        //Log.d("getAllBooks()", books.toString());
  
        // return books
        return results;
    }
    public int updateResult(ExerciseResult result) {
    	 
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
     
        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
		values.put(KEY_BENCHMARK, result.getBenchmark()); // get benchmark
		values.put(KEY_CURRENTPROGRESS, result.getCurrentProgress());
		values.put(KEY_CURRENTSCORE, result.getCurrentScore());
		values.put(KEY_PREVIOUSSCORE, result.getPreviousScore());
		values.put(KEY_BEFOREPREVIOUSSCORE, result.getBeforePreviousScore());

     
        // 3. updating row
        int i = db.update(TABLE_SCORES, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(result.getId()) }); //selection args
     
        // 4. close
        db.close();
     
        return i;
     
    }
    public void deleteResult(ExerciseResult result) {
    	 
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
 
        // 2. delete
        db.delete(TABLE_SCORES, //table name
                KEY_ID+" = ?",  // selections
                new String[] { String.valueOf(result.getId()) }); //selections args
 
        // 3. close
        db.close();
 
        //log
    //Log.d("deleteBook", book.toString());
 
    }
    

}
