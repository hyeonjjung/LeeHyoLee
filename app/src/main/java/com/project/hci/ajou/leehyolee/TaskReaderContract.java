package com.project.hci.ajou.leehyolee;

import android.provider.BaseColumns;

public final class TaskReaderContract {
    private TaskReaderContract() {}

    public static class CalendarEntry implements BaseColumns {
        public static final String TABLE_NAME = "calendar";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_TIME = "time";
        public static final String COLUMN_TASK = "task";
    }
}
