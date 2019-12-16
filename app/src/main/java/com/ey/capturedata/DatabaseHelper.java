package com.ey.capturedata;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseHelper  extends SQLiteOpenHelper {

    // DEFINING SCHEMA
    public static final String DB_NAME = "Expenses";
    public static final String TABLE_NAME = "expensesTable";
    public static final String ID = "_id";
    public static final String ITEM_NAME = "item_name";
    public static final String ITEM_COST = "item_cost";
    public static final String ITEM_CURRENCY = "item_currency";

    static final int DB_VERSION = 1;

    private static final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
            + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ITEM_NAME + " TEXT NOT NULL, " + ITEM_COST + " INTEGER NOT NULL, "
            + ITEM_CURRENCY + " TEXT NOT NULL)";

    public DatabaseHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_QUERY);
        Log.i("Database","Table in Database Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
