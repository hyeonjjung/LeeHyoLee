package com.project.hci.ajou.leehyolee;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

public class Calendar extends Fragment {
    String TAG = "Calendar";
    TextView textView;
    public static Calendar newInstance() {
        return new Calendar();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_calendar, container, false);
        MaterialCalendarView materialCalendarView = (MaterialCalendarView) view.findViewById(R.id.calendarView);
        //materialCalendarView.addDecorator(new OneDayDecorator());
        materialCalendarView.addDecorator(new TodayDecorator(inflater.getContext()));
        materialCalendarView.setSelectedDate(CalendarDay.today());
        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                Log.d(TAG, "date is "+date.toString());
            }
        });
        return view;
    }
}
