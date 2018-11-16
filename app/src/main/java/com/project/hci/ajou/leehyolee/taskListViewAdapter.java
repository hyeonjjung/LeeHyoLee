package com.project.hci.ajou.leehyolee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class taskListViewAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<Task> arrayList;
    private int layout;

    public taskListViewAdapter(Context context, int layout, ArrayList<Task> arrayList) {
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.arrayList = arrayList;
        this.layout = layout;
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
        Task task = arrayList.get(position);

        TextView name = (TextView)convertView.findViewById(R.id.taskNameText);
        name.setText(task.getName());

        return convertView;
    }
}
