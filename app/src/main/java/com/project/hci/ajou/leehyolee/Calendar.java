package com.project.hci.ajou.leehyolee;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Calendar extends Fragment {
    private static final String TAG = "Calendar";

    private ListView listView;

    private CalendarListViewAdapter calendarListViewAdapter;

    private String partQuery =  "SELECT * FROM "+TaskReaderContract.CalendarEntry.TABLE_NAME + " WHERE "+TaskReaderContract.CalendarEntry.COLUMN_DATE;
    private String selectQuery;

    private DBManager dbManager;
    private SQLiteDatabase db;
    private ArrayList<CalendarTask> arrayList;

    public static Calendar newInstance() {
        return new Calendar();
    }

    @Override
    public void onDestroy() {
        dbManager.close();
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_calendar, container, false);
        MaterialCalendarView materialCalendarView = (MaterialCalendarView) view.findViewById(R.id.calendarView);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd");
        selectQuery = partQuery + " = " + simpleDateFormat.format(new Date(System.currentTimeMillis()));


        dbManager = new DBManager(getContext());

        db = dbManager.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        arrayList = new ArrayList<>();

        if(cursor.moveToFirst()) {
            do {
                CalendarTask calendarTask = new CalendarTask(cursor.getString(1), cursor.getString(2), cursor.getString(3));
                arrayList.add(calendarTask);
            } while (cursor.moveToNext());
        }


        listView = (ListView) view.findViewById(R.id.calendarListView);


        calendarListViewAdapter = new CalendarListViewAdapter(getContext(), R.layout.calendar_task, arrayList);

        listView.setAdapter(calendarListViewAdapter);


        materialCalendarView.addDecorator(new TodayDecorator(inflater.getContext()));
        materialCalendarView.setSelectedDate(CalendarDay.today());
        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                String selectDate = String.valueOf(date.getYear()).substring(2) + String.valueOf(date.getMonth()+1)+String.valueOf(date.getDay());
                selectQuery = partQuery + " = "+selectDate;
                arrayList.clear();


                DBManager dbManager = new DBManager(getContext());
                SQLiteDatabase db = dbManager.getReadableDatabase();
                Cursor cursor = db.rawQuery(selectQuery, null);

                if(cursor.moveToFirst()) {
                    do {
                        CalendarTask calendarTask = new CalendarTask(cursor.getString(1), cursor.getString(2), cursor.getString(3));
                        arrayList.add(calendarTask);
                    } while (cursor.moveToNext());
                }

                calendarListViewAdapter.notifyDataSetChanged();
                listView.invalidateViews();
                listView.refreshDrawableState();
            }
        });
        return view;
    }

}
