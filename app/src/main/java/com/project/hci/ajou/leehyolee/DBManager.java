package com.project.hci.ajou.leehyolee;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DBManager extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "TaskReader.db";

    private static final String CALENDER_CREATE_ENTRIES = "CREATE TABLE CALENDAR ( "+TaskReaderContract.CalendarEntry._ID +" INTEGER PRIMARY KEY, "+TaskReaderContract.CalendarEntry.COLUMN_DATE +" TEXT, "+TaskReaderContract.CalendarEntry.COLUMN_TIME +" TEXT, "+TaskReaderContract.CalendarEntry.COLUMN_TASK +" TEXT)";
    private static final String CALENDAR_DELETE_ENTRIES = "DROP TABLE IF EXISTS "+TaskReaderContract.CalendarEntry.TABLE_NAME;

    public DBManager (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //DB가 존재하지 않을때 딱 한번 실행됨
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CALENDER_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(CALENDAR_DELETE_ENTRIES);
        onCreate(db);
    }

}
