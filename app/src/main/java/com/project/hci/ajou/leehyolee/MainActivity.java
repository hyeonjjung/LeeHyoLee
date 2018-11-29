package com.project.hci.ajou.leehyolee;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_calendar:
                    //mTextMessage.setText(R.string.title_home);
                    replaceFragment(Calendar.newInstance());

                    return true;
                case R.id.navigation_timer:
                    replaceFragment(Timer.newInstance());

                    return true;
                case R.id.navigation_awards:
                    replaceFragment(Awards.newInstance());
                    //mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frame_layout, Calendar.newInstance()).commit();

        DBManager dbManager = new DBManager(getBaseContext());

        // test code for adding task to calendar
        SQLiteDatabase db = dbManager.getWritableDatabase();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd");
        SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("hh:mm aaa");

        ContentValues values = new ContentValues();
        values.put(TaskReaderContract.CalendarEntry.COLUMN_DATE, simpleDateFormat.format(new Date(System.currentTimeMillis())));
        values.put(TaskReaderContract.CalendarEntry.COLUMN_TIME, simpleTimeFormat.format(new Date(System.currentTimeMillis())));
        values.put(TaskReaderContract.CalendarEntry.COLUMN_TASK, "Testing..");

        long newRowId = db.insert(TaskReaderContract.CalendarEntry.TABLE_NAME, null, values);

    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment).commit();
    }

}
