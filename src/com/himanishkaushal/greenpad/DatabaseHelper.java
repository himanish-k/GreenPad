package com.himanishkaushal.greenpad;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseHelper extends SQLiteOpenHelper {
	
	// naming convention for table/column names:
	// TABLE/COLUMN_WHATEVER_ITS_NAME_IS
	public static final String TABLE_ENTRIES = "entries", 
							   COLUMN_ID = "_id",
							   COLUMN_YEAR = "year",
							   COLUMN_MONTH = "month",
							   COLUMN_DAY = "day",
							   COLUMN_PAYEE = "payee",
							   COLUMN_PAYMENT = "payment";
	
	public static final String COMMA = " , ";
	
	private static final String DATABASE_NAME = "entries.db";
	private static final int DATABASE_VERSION = 2;
	
	// statements:
	private static final String STATEMENT_CREATE = "create table " + TABLE_ENTRIES
								+ "(" 
								+ COLUMN_ID + " integer primary key autoincrement" + COMMA
								+ COLUMN_YEAR + " integer not null" + COMMA
								+ COLUMN_MONTH + " integer not null" + COMMA
								+ COLUMN_DAY + " integer not null" + COMMA
								+ COLUMN_PAYEE + " text not null" + COMMA
								+ COLUMN_PAYMENT + " text not null"
								+ ");";

	public DatabaseHelper(Context context) {
		
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public void onCreate(SQLiteDatabase db) {
		db.execSQL(STATEMENT_CREATE);
		
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(DatabaseHelper.class.getName(),
		        "Upgrading database from version " + oldVersion + " to "
		            + newVersion + ", which will destroy all old data");
		    db.execSQL("DROP TABLE IF EXISTS " + TABLE_ENTRIES);
		    onCreate(db);
	}

}
