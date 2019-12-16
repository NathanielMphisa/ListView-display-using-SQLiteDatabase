package com.ey.capturedata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    private DatabaseHelper dbhelper;
    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    // passing context
    // creating a db

    public DBManager open() throws SQLException
    {
        dbhelper = new DatabaseHelper(context);
        database = dbhelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbhelper.close();

    }

    public boolean insert(String itemName,int cost,String currency){
        database = dbhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.ITEM_NAME,itemName);
        contentValues.put(DatabaseHelper.ITEM_COST,cost);
        contentValues.put(DatabaseHelper.ITEM_CURRENCY,currency);


        try{
            database.insert(DatabaseHelper.TABLE_NAME,null,contentValues);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    public Cursor fetch(){
        String[] columns = new String[] {DatabaseHelper.ID,DatabaseHelper.ITEM_NAME,DatabaseHelper.ITEM_COST,DatabaseHelper.ITEM_CURRENCY};

        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME,columns,null,null,null,null,null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String item, int cost, String currency){
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.ITEM_CURRENCY,currency);
        contentValue.put(DatabaseHelper.ITEM_COST,cost);
        contentValue.put(DatabaseHelper.ITEM_NAME,item);
        int i = database.update(DatabaseHelper.TABLE_NAME,contentValue,DatabaseHelper.ID + " = " + _id,null);
        return i;
    }

    public void delete(long _id){
        database.delete(DatabaseHelper.TABLE_NAME,DatabaseHelper.ID + " = " + _id,null);
    }

}

