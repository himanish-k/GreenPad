package com.himanishkaushal.greenpad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class EntrySource {
	
	private SQLiteDatabase database;
	private DatabaseHelper helper;
	private String[] allColumns = { DatabaseHelper.COLUMN_ID,
								    DatabaseHelper.COLUMN_YEAR,
								    DatabaseHelper.COLUMN_MONTH,
								    DatabaseHelper.COLUMN_DAY,
								    DatabaseHelper.COLUMN_PAYEE,
								    DatabaseHelper.COLUMN_PAYMENT 
								    };
	
	private String[] dateColumns = { DatabaseHelper.COLUMN_ID,
									 DatabaseHelper.COLUMN_YEAR,
									 DatabaseHelper.COLUMN_MONTH,
									 DatabaseHelper.COLUMN_DAY 
									 };
	
	private String[] payeePaymentColumns = { DatabaseHelper.COLUMN_ID,
											 DatabaseHelper.COLUMN_PAYEE,
											 DatabaseHelper.COLUMN_PAYMENT
										     };
	
	
	private String orderByDate = DatabaseHelper.COLUMN_YEAR + 
			 " DESC" +
			 DatabaseHelper.COMMA + 
			 DatabaseHelper.COLUMN_MONTH + 
			 " DESC" +
			 DatabaseHelper.COMMA + 
			 DatabaseHelper.COLUMN_DAY + 
			 " DESC";
	

	public EntrySource(Context context) {
		helper = new DatabaseHelper(context);
	}
	
	public void open() {
		database = helper.getWritableDatabase();
	}
	
	public void close() {
		helper.close();
	}
	
	public long insertEntry(int year, int month, int day, String payee, double payment) {
		
		ContentValues newEntry = new ContentValues();
		newEntry.put(DatabaseHelper.COLUMN_YEAR, year);
		newEntry.put(DatabaseHelper.COLUMN_MONTH, month);
		newEntry.put(DatabaseHelper.COLUMN_DAY, day);
		newEntry.put(DatabaseHelper.COLUMN_PAYEE, payee);
		newEntry.put(DatabaseHelper.COLUMN_PAYMENT, payment);
		
		return database.insert(DatabaseHelper.TABLE_ENTRIES, null, newEntry);
	}
	
	public void insertFewInitialEntries() {
		
		insertEntry(2014, 2, 3, "yo", 20);
	}
	
	public boolean deleteEntry(int id) {
		String where = DatabaseHelper.COLUMN_ID + "=" + id;
		int deleted = database.delete(DatabaseHelper.TABLE_ENTRIES, where, null);
		return deleted > 0;
	}
	
	public boolean deleteAllEntries() {
		
		int deleted = database.delete(DatabaseHelper.TABLE_ENTRIES, null, null);
		return deleted > 0;
	}
	
	public Cursor fetchAllEntryDates() {
		
		// removes duplicate dates
		String groupByDate = DatabaseHelper.COLUMN_YEAR +
						 DatabaseHelper.COMMA +
						 DatabaseHelper.COLUMN_MONTH +
						 DatabaseHelper.COMMA +
						 DatabaseHelper.COLUMN_DAY;
		Cursor cursor = database.query(DatabaseHelper.TABLE_ENTRIES, dateColumns, 
									   null, null, groupByDate, null, orderByDate, null);
		
		return returnCursor(cursor);
	}
	
	public Cursor fetchAllEntries() {
		
		Cursor cursor = database.query(DatabaseHelper.TABLE_ENTRIES, allColumns, null, null, null, null, null);
		
		return returnCursor(cursor);
	}
	
	public Cursor fetchAllEntriesByDate() {
		
		Cursor cursor = database.query(DatabaseHelper.TABLE_ENTRIES, allColumns, null, null, null, null, orderByDate);
		
		return returnCursor(cursor);
	}
	
	public Cursor fetchEntriesForDate(int year, int month, int day) {
		
		String where = DatabaseHelper.COLUMN_YEAR + "=" + year +
					   " and " +
					   DatabaseHelper.COLUMN_MONTH + "=" + month +
					   " and " +
					   DatabaseHelper.COLUMN_DAY + "=" + day; 
		
		Log.d("", where);
		String orderBy = DatabaseHelper.COLUMN_PAYEE;
		Cursor cursor = database.query(DatabaseHelper.TABLE_ENTRIES, allColumns, where, null, null, null, orderBy);
		
		return returnCursor(cursor);
	}
	
	public Cursor fetchAllEntriesForMonth(int month) {
		
		String selection = DatabaseHelper.COLUMN_MONTH + "=" + month;
		Cursor cursor = database.query(DatabaseHelper.TABLE_ENTRIES, allColumns, selection, null, null, null, null);
		
		return returnCursor(cursor);
	}
	
	private Cursor returnCursor(Cursor cursor) {
		
		if(cursor != null) {
			cursor.moveToFirst();
			Log.d("", "it is not null");
		} else {
			Log.d("", "it is null");
		}
		
		return cursor;
	}

}
