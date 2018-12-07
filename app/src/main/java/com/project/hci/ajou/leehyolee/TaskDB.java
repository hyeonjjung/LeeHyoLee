package com.project.hci.ajou.leehyolee;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class TaskDB extends SQLiteOpenHelper{

    private static final String TABLE_NAME = "TaskList";

    public TaskDB (Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME +" (id INTEGER PRIMARY KEY AUTOINCREMENT, Task TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insert(String Task)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Task", Task);
        long result = db.insert(TABLE_NAME, null, values);
        return result;
    }

    public void update(int id, String Task){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_NAME + " SET Task= '" + Task + "' WHERE id='" + id + "';");
    }

    public void delete(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE id='" + id + "';");
    }

    public ArrayList getResult() {

        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Task> result = new ArrayList<>();


        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        while (cursor.moveToNext()) {
            Task autoTask = new Task(cursor.getInt(0),cursor.getString(1));
            result.add(autoTask);
        }

        return result;
    }

}
