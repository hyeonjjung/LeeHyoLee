package com.project.hci.ajou.leehyolee;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.text.style.ForegroundColorSpan;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

public class TodayDecorator implements DayViewDecorator {
    private CalendarDay date;
    private Context context;

    public TodayDecorator(Context context) {
        this.date = CalendarDay.today();
        this.context = context;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return date != null && day.equals(date);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new ForegroundColorSpan(Color.BLACK));
        view.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.ic_radio_button_unchecked_black_24dp));
    }
}
