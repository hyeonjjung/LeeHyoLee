package com.project.hci.ajou.leehyolee;

import java.util.Date;

public class CalendarTask {
    private String date;
    private String time;
    private String name;

    public String getDate() {
        return date;
    }
    public String getName() {
        return name;
    }
    public String getTime() {
        return time;
    }
    public CalendarTask(String date, String time, String name) {
        this.date = date;
        this.name = name;
        this.time = time;
    }
}
