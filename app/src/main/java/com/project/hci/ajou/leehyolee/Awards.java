package com.project.hci.ajou.leehyolee;

import android.support.v4.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Awards extends Fragment {
    private static final String TAG = "Awards";


    private String partQuery =  "SELECT * FROM "+TaskReaderContract.CalendarEntry.TABLE_NAME;
    private DBManager dbManager;
    private SQLiteDatabase db;
    private ArrayList<CalendarTask> arrayList;
    private ImageView myRankImageView;

    private View view;
    private int count = 0;

    private ImageView award1ImageView;
    private ImageView award2ImageView;
    private ImageView award3ImageView;
    private ImageView award4ImageView;
    private ImageView award5ImageView;
    private ImageView award6ImageView;
    private ImageView award7ImageView;
    private ImageView award8ImageView;
    private ImageView award9ImageView;


    private TextView award1TextView;





    public static android.support.v4.app.Fragment newInstance() {
        return new Awards();
    }
    @Override
    public void onDestroy() {
        dbManager.close();
        super.onDestroy();
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_awards, container, false);
        myRankImageView = (ImageView) view.findViewById(R.id.myRankImageView);

        award1ImageView = (ImageView) view.findViewById(R.id.award1ImageView);
        award2ImageView = (ImageView) view.findViewById(R.id.award2ImageView);
        award3ImageView = (ImageView) view.findViewById(R.id.award3ImageView);
        award4ImageView = (ImageView) view.findViewById(R.id.award4ImageView);
        award5ImageView = (ImageView) view.findViewById(R.id.award5ImageView);
        award6ImageView = (ImageView) view.findViewById(R.id.award6ImageView);
        award7ImageView = (ImageView) view.findViewById(R.id.award7ImageView);
        award8ImageView = (ImageView) view.findViewById(R.id.award8ImageView);
        award9ImageView = (ImageView) view.findViewById(R.id.award9ImageView);

        award1TextView = (TextView) view.findViewById(R.id.award1TextView);

        dbManager = new DBManager(getContext());

        db = dbManager.getReadableDatabase();
        myRankImageView.setOnClickListener(mClickListener);
        award1ImageView.setOnClickListener(mClickListener);
        award2ImageView.setOnClickListener(mClickListener);
        award3ImageView.setOnClickListener(mClickListener);
        award4ImageView.setOnClickListener(mClickListener);
        award5ImageView.setOnClickListener(mClickListener);
        award6ImageView.setOnClickListener(mClickListener);
        award7ImageView.setOnClickListener(mClickListener);
        award8ImageView.setOnClickListener(mClickListener);
        award9ImageView.setOnClickListener(mClickListener);



        Cursor cursor = db.rawQuery(partQuery, null);


        count = cursor.getCount();
        if(count > 0 ) {
            award1ImageView.setImageResource(R.drawable.award3_1);
        }
        if(count < 10) {
            myRankImageView.setImageResource(R.drawable.bronze);
        }
        else if(count >= 10 && count < 20){
            myRankImageView.setImageResource(R.drawable.silver);
            award5ImageView.setImageResource(R.drawable.award5_1);
        }
        else if(count >= 20 && count < 30){
            //ImageView imageView= (ImageView) view.findViewById(R.id.imageView3);
            //imageView.setImageResource(R.drawable.gaward);
            //ImageView imageView3= (ImageView) view.findViewById(R.id.imageView2);
            //imageView3.setImageResource(R.drawable.gaward);
            myRankImageView.setImageResource(R.drawable.gold);
        }
        else if(count >= 30 && count < 40){
            //ImageView imageView= (ImageView) view.findViewById(R.id.imageView3);
            //imageView.setImageResource(R.drawable.gaward);
            //ImageView imageView3= (ImageView) view.findViewById(R.id.imageView2);
            //imageView3.setImageResource(R.drawable.gaward);
            //ImageView imageView4= (ImageView) view.findViewById(R.id.imageView4);
            //imageView4.setImageResource(R.drawable.gaward);
            myRankImageView.setImageResource(R.drawable.platinum);
        }
        else{
            //ImageView imageView= (ImageView) view.findViewById(R.id.imageView3);
            //imageView.setImageResource(R.drawable.gaward);
            //ImageView imageView3= (ImageView) view.findViewById(R.id.imageView2);
           //imageView3.setImageResource(R.drawable.gaward);
            //ImageView imageView4= (ImageView) view.findViewById(R.id.imageView4);
            //imageView4.setImageResource(R.drawable.gaward);
            //ImageView imageView5= (ImageView) view.findViewById(R.id.imageView5);
            //imageView5.setImageResource(R.drawable.gaward);
            myRankImageView.setImageResource(R.drawable.platinum);
        }

        return view;
    }
    ImageView.OnClickListener mClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View toastView = layoutInflater.inflate(R.layout.toast_design, (ViewGroup) view.findViewById(R.id.toast_custom));
            TextView textView = (TextView) toastView.findViewById(R.id.toastTextView);
            String toastText = "";
            switch (v.getId()) {
                case R.id.myRankImageView:
                    if(count < 10) {
                        toastText = "Number of tasks completed is less than 10";
                    } else if (count >= 10 && count < 20) {
                        toastText = "Number of tasks completed exceeded 10";
                    } else if (count >= 20 && count < 30) {
                        toastText = "Number of tasks completed exceeded 20";
                    } else if (count >= 30 && count < 40) {
                        toastText = "Number of tasks completed exceeded 30";
                    }
                    break;
                case R.id.award1ImageView:
                    toastText = "A journey of a thousand miles begins with a single step.";
                    break;
                case R.id.award8ImageView:
                    toastText = "Better late than never.";
                    break;
                case R.id.award5ImageView:
                    toastText = "Habit is a second nature.";
                    break;
                case R.id.award3ImageView:
                    toastText = "He laughs best who laughs last";
                    break;


            }
            textView.setText(toastText);
            Toast toast = new Toast(getContext());
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.setView(toastView);
            toast.show();

        }
    };
}
