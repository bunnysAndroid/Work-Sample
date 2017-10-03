package com.raj.task.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dhiraj on 28/9/17.
 */

public class DateUtil {
    public static final String DATE_FORMAT = "dd/MM/yyyy";

    public static String getDisplayDate(long millis) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        String dateString = formatter.format(new Date(millis));
        return dateString;
    }

    public static long getDateInMillis(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        Date convertedDate;
        try {
            convertedDate = formatter.parse(date);
            return convertedDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
