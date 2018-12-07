package com.project.hci.ajou.leehyolee;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Awards extends Fragment {
    private static final String TAG = "Awards";


    private String partQuery =  "SELECT * FROM "+TaskReaderContract.CalendarEntry.TABLE_NAME;
    private DBManager dbManager;
    private SQLiteDatabase db;
    private ArrayList<CalendarTask> arrayList;

    public static Awards newInstance() {
        return new Awards();
    }
    @Override
    public void onDestroy() {
        dbManager.close();
        super.onDestroy();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_awards, container, false);

        dbManager = new DBManager(getContext());

        db = dbManager.getReadableDatabase();


        Cursor cursor = db.rawQuery(partQuery, null);
        int count = cursor.getCount();
        if(count < 10) {
            ImageView imageView= (ImageView) view.findViewById(R.id.imageView);
            imageView.setImageResource(R.drawable.bronze);
        }
        else if(count >= 10 && count < 20){
            ImageView imageView= (ImageView) view.findViewById(R.id.imageView2);
            imageView.setImageResource(R.drawable.icon_award);
            ImageView imageView2= (ImageView) view.findViewById(R.id.imageView);
            imageView2.setImageResource(R.drawable.silver);
        }
        else if(count >= 20 && count < 30){
            ImageView imageView= (ImageView) view.findViewById(R.id.imageView3);
            imageView.setImageResource(R.drawable.icon_award);
            ImageView imageView3= (ImageView) view.findViewById(R.id.imageView2);
            imageView3.setImageResource(R.drawable.icon_award);
            ImageView imageView2= (ImageView) view.findViewById(R.id.imageView);
            imageView2.setImageResource(R.drawable.gold);
        }
        else if(count >= 30 && count < 40){
            ImageView imageView= (ImageView) view.findViewById(R.id.imageView3);
            imageView.setImageResource(R.drawable.icon_award);
            ImageView imageView3= (ImageView) view.findViewById(R.id.imageView2);
            imageView3.setImageResource(R.drawable.icon_award);
            ImageView imageView4= (ImageView) view.findViewById(R.id.imageView4);
            imageView4.setImageResource(R.drawable.icon_award);
            ImageView imageView2= (ImageView) view.findViewById(R.id.imageView);
            imageView2.setImageResource(R.drawable.platinum);
        }
        else{
            ImageView imageView= (ImageView) view.findViewById(R.id.imageView3);
            imageView.setImageResource(R.drawable.icon_award);
            ImageView imageView3= (ImageView) view.findViewById(R.id.imageView2);
            imageView3.setImageResource(R.drawable.icon_award);
            ImageView imageView4= (ImageView) view.findViewById(R.id.imageView4);
            imageView4.setImageResource(R.drawable.icon_award);
            ImageView imageView5= (ImageView) view.findViewById(R.id.imageView5);
            imageView5.setImageResource(R.drawable.icon_award);
            ImageView imageView2= (ImageView) view.findViewById(R.id.imageView);
            imageView2.setImageResource(R.drawable.platinum);
        }

        return view;
    }
}
