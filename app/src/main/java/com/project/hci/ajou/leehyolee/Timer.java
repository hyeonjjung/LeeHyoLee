package com.project.hci.ajou.leehyolee;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class Timer extends Fragment {
    private View view;
    private ListView listView;
    private TaskDB taskDB;
    private ArrayList<Task> arrayList;
    private Task autoTask;
    private taskListViewAdapter adapter;
    private FloatingActionButton addButton;
    private AutoTask auto = new AutoTask();
    private Intent intent;
    private String autoTaskName;

    public static Timer newInstance() {
        return new Timer();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_timer, container, false);
        addButton = (FloatingActionButton) view.findViewById(R.id.floatingActionButton4);
        listView = (ListView) view.findViewById(R.id.taskListView);

        taskDB = new TaskDB(getContext());
        arrayList = taskDB.getResult();
        autoTask = new Task(0,"AUTO");
        arrayList.add(0,autoTask);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAlert();
            }
        });

        adapter = new taskListViewAdapter(this.getContext(), R.layout.task, arrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(inflater.getContext(), TimerActivity.class);
                Task task = (Task)parent.getAdapter().getItem(position);

                if (position == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("AUTO Task");
                    autoTaskName = auto.getTask();
                    builder.setMessage("Are you okay about \""+autoTaskName+"\"?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            intent.putExtra("task", autoTaskName);
                            startActivity(intent);
                        }
                    });
                    builder.setNegativeButton("No", null);
                    builder.show();
                }

                else {
                    intent.putExtra("task", task.getName());
                    startActivity(intent);
                }



            }
        });
        registerForContextMenu(listView);
        return view;
    }
    void createAlert()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext(), R.style.AlertDialogTheme);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.create_task, null);
        builder.setView(view);
        final EditText userInput = (EditText) view.findViewById(R.id.userInput);
        builder.setTitle("New Task");
        builder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String task = userInput.getText().toString();
                        long result = taskDB.insert( task );
                        Task newTask = new Task((int)result, task);
                        arrayList.add(newTask);
                    }
                });
        builder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.show();
    }
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        Task task = (Task)adapter.getItem(info.position);
        if(task.getId()==0 ) return;
        getActivity().getMenuInflater().inflate(R.menu.longclickmenu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int index= info.position;
        switch( item.getItemId() ){
            case R.id.edit:
                modifyAlert(index);
                return true;
            case R.id.delete:
                Task task = (Task)adapter.getItem(index);
                taskDB.delete(task.getId());
                arrayList.remove(index);
                adapter.notifyDataSetChanged();
                return true;
        }
        return false;
    };
    void modifyAlert(final int index)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext(), R.style.AlertDialogTheme);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.create_task, null);
        builder.setView(view);
        final EditText userInput = (EditText) view.findViewById(R.id.userInput);
        builder.setTitle("Change Task");
        builder.setPositiveButton("ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String text = userInput.getText().toString();
                        Task task = (Task)adapter.getItem(index);
                        taskDB.update( task.getId(), text );
                        Task newTask = new Task(task.getId(), text);
                        arrayList.remove(index);
                        arrayList.add(index, newTask);
                        adapter.notifyDataSetChanged();
                    }
                });
        builder.setNegativeButton("no",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.show();
    }
}
