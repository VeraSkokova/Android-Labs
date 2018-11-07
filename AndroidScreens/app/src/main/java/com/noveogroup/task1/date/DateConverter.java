package com.noveogroup.task1.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class DateConverter {
    private static final DateConverter INSTANCE = new DateConverter();
    private static final String FORMAT = "dd/MM/yyyy";
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(FORMAT);

    private DateConverter() {
    }

    public static DateConverter getInstance() {
        return INSTANCE;
    }

    public String dateToString(Date date) {
        return SIMPLE_DATE_FORMAT.format(date);
    }

    public int countAge(Calendar calendar) {
        Calendar todayCalendar = Calendar.getInstance();
        int age = todayCalendar.get(Calendar.YEAR) - calendar.get(Calendar.YEAR);

        int monthsDifference = todayCalendar.get(Calendar.MONTH) - calendar.get(Calendar.MONTH);
        int daysDifference = todayCalendar.get(Calendar.DAY_OF_MONTH) - calendar.get(Calendar.DAY_OF_MONTH);

        if ((monthsDifference < 0) || ((monthsDifference == 0) && (daysDifference < 0))) {
            age--;
        }

        return age;
    }
}
