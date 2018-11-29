package com.project.hci.ajou.leehyolee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CalendarListViewAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<CalendarTask> arrayList;
    private int layout;

    private SimpleDateFormat simpleDateFormat;

    public CalendarListViewAdapter(Context context, int layout, ArrayList<CalendarTask> arrayList) {
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.arrayList = arrayList;
        this.layout = layout;

        simpleDateFormat = new SimpleDateFormat("d MMM / hh:mm aaa");
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = inflater.inflate(layout, parent, false);
        }
        CalendarTask calendarTask = arrayList.get(position);


        TextView dateTextView = (TextView) convertView.findViewById(R.id.calendarDayTextView);
        dateTextView.setText(calendarTask.getDate()+" / "+calendarTask.getTime());
        TextView nameTextView = (TextView) convertView.findViewById(R.id.calendarTaskTextView);
        nameTextView.setText(calendarTask.getName());

        return convertView;
    }
}
