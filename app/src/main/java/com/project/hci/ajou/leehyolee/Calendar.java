package com.project.hci.ajou.leehyolee;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class Calendar extends Fragment {
    private static final String TAG = "Calendar";

    private ListView listView;

    private CalendarListViewAdapter calendarListViewAdapter;

    private String partQuery =  "SELECT * FROM "+TaskReaderContract.CalendarEntry.TABLE_NAME + " WHERE "+TaskReaderContract.CalendarEntry.COLUMN_DATE;
    private String eventQuery = "SELECT * FROM "+TaskReaderContract.CalendarEntry.TABLE_NAME;
    private String selectQuery;

    private DBManager dbManager;
    private SQLiteDatabase db;
    private ArrayList<CalendarTask> arrayList;

    public static Fragment newInstance() {
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

        // task 실행된 날짜 표시하기
        ArrayList<String> eventDateList = new ArrayList<>();
        ArrayList<String> levelOneDateList = new ArrayList<>();
        ArrayList<String> levelTwoDateList = new ArrayList<>();
        ArrayList<String> levelThreeDateList = new ArrayList<>();
        ArrayList<String> levelFourDateList = new ArrayList<>();
        cursor = db.rawQuery(eventQuery, null);
        int count = 1;
        if(cursor.moveToFirst()) {
            do {
                String date = cursor.getString(1);
                if(levelOneDateList.contains(date)) {
                    levelTwoDateList.add(date);
                    levelOneDateList.remove(date);
                } else if (levelTwoDateList.contains(date)) {
                    levelThreeDateList.add(date);
                    levelTwoDateList.remove(date);
                } else if (levelThreeDateList.contains(date)) {
                    levelFourDateList.add(date);
                    levelThreeDateList.remove(date);
                } else if (levelFourDateList.contains(date)) {

                } else {
                    levelOneDateList.add(date);
                }
            } while (cursor.moveToNext());
        }




        listView = (ListView) view.findViewById(R.id.calendarListView);


        calendarListViewAdapter = new CalendarListViewAdapter(getContext(), R.layout.calendar_task, arrayList);

        listView.setAdapter(calendarListViewAdapter);


        materialCalendarView.addDecorator(new TodayDecorator(inflater.getContext()));
        materialCalendarView.setSelectedDate(CalendarDay.today());

        materialCalendarView.addDecorator(new OneDayDecorator(getContext(), levelFourDateList));
        materialCalendarView.addDecorator(new levelOneDecorator(getContext(), levelOneDateList));
        materialCalendarView.addDecorator(new levelTwoDecorator(getContext(), levelTwoDateList));
        materialCalendarView.addDecorator(new levelThreeDecorator(getContext(), levelThreeDateList));

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
