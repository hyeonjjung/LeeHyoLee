package com.project.hci.ajou.leehyolee;

import android.content.Context;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class levelTwoDecorator implements DayViewDecorator {
    private ArrayList<String> dates;
    private Context context;

    public levelTwoDecorator(Context context, ArrayList<String> dates) {
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
        view.addSpan(new DotSpan(7, context.getResources().getColor(R.color.level2)));

    }
}
