package com.project.hci.ajou.leehyolee;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimerActivity extends AppCompatActivity {

    /*http://liveonthekeyboard.tistory.com/129*/

    private static final long START_TIME_IN_MILLIS = 60000; //1분 -> 나중에 여기다가 설정하면 될듯.
    private TextView mTextViewCountDown;
    private TextView todoNameView;
    private Button mButtonStart;
    private ProgressBar mProgressBar;
    private CountDownTimer mCountDownTimer;
    private CountDownTimer pCountDownTimer;
    private boolean mTimerRunning;
    private long mTimerLeftInMillis = START_TIME_IN_MILLIS; //xml에서 -10
    int value =590;
    //long now = System.currentTimeMillis();
    ContentValues values = new ContentValues();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd");
    SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("hh:mm aaa");
    String todoName = "";
/*    // 현재시간을 date 변수에 저장한다.
    Date date = new Date(now);
    // 시간을 나타냇 포맷을 정한다 ( yyyy/MM/dd 같은 형태로 변형 가능 )
    SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    // nowDate 변수에 값을 저장한다.
    String formatDate = sdfNow.format(date);
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        mTextViewCountDown = findViewById(R.id.text_view_countdown);
        mButtonStart = findViewById(R.id.button_start);
        mProgressBar = findViewById(R.id.myProgressBar);
        todoNameView =  findViewById(R.id.title_of_todo);
        Intent intent = new Intent(this.getIntent());
        todoName = intent.getStringExtra("todoName") + "!";
        mButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mTimerRunning) {
                    todoNameView.setText(todoName);
                    todoNameView.setVisibility(View.VISIBLE);
                    mButtonStart.setVisibility(View.GONE);
                    mTextViewCountDown.setVisibility(View.VISIBLE);

                    startProgressBar();
                    startTimer();

                }
            }
        });


    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimerLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                mTimerLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                show();
            }
        }.start();

        mTimerRunning = true;
        //mButtonStart.setText("");
    }
    public void startProgressBar(){
        pCountDownTimer = new CountDownTimer(mTimerLeftInMillis,100) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimerLeftInMillis = millisUntilFinished;
                if(value>0) {
                    mProgressBar.setProgress(--value);
                }

            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimerLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimerLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        mTextViewCountDown.setText(timeLeftFormatted);


    }
    /*new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"예를 선택했습니다.",Toast.LENGTH_LONG).show();
                    }
                });*/
    private void show(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Time is over");
        builder.setMessage("Finished?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                values.put(TaskReaderContract.CalendarEntry.COLUMN_DATE, simpleDateFormat.format(new Date(System.currentTimeMillis())));
                values.put(TaskReaderContract.CalendarEntry.COLUMN_TIME, simpleTimeFormat.format(new Date(System.currentTimeMillis())));
                values.put(TaskReaderContract.CalendarEntry.COLUMN_TASK, todoName);
                DBManager dbManager = new DBManager(getBaseContext());
                SQLiteDatabase db = dbManager.getWritableDatabase();
                long newRowId = db.insert(TaskReaderContract.CalendarEntry.TABLE_NAME, null, values);
                Toast.makeText(TimerActivity.this, todoName, Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No",null);
        builder.show();
    }
}
