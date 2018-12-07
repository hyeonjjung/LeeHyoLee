package com.project.hci.ajou.leehyolee;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Timer extends Fragment {
    public static Timer newInstance() {
        return new Timer();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timer, container, false);
        ListView listView = (ListView) view.findViewById(R.id.taskListView);
        ArrayList<Task> arrayList = new ArrayList<>();
        final Task autoTask = new Task("AUTO");
        arrayList.add(autoTask);

        taskListViewAdapter adapter = new taskListViewAdapter(this.getContext(), R.layout.task, arrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(inflater.getContext(), TimerActivity.class);
                intent.putExtra("todoName",autoTask.getName());
                startActivity(intent);
            }
        });
        return view;
    }
}
