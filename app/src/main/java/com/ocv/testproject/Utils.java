package com.ocv.testproject;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lancelittle on 9/25/17.
 */

public class Utils {

    public static String getDateFromSecs(String date_sec) {
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMMM d, yyyy HH:mm");
        String dateString = formatter.format(new Date(Long.parseLong(date_sec) * 1000L));
        return dateString;
    }
}
