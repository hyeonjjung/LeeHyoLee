package com.project.hci.ajou.leehyolee;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.icu.util.Calendar;
import android.icu.util.LocaleData;
import android.os.Build;
import android.renderscript.Sampler;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;


public class OneDayDecorator implements DayViewDecorator {
    private static final String TAG = "OneDayDecorator";
    private ArrayList<String> dates;
    private Context context;

    public OneDayDecorator(Context context, ArrayList<String> dates) {
        this.context = context;
        this.dates = dates;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd");
        String date = simpleDateFormat.format(day.getDate());
        return dates.contains(date);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new DotSpan(5, context.getResources().getColor(R.color.level4)));
    }
}
