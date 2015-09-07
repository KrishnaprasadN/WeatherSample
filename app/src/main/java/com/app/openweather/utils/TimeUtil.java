package com.app.openweather.utils;

import android.text.TextUtils;
import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Krishnaprasad.n on 7/20/2015.
 */
public class TimeUtil {
    public static final String TAG = "TimeUtil";
    public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String UTC_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    public static final String FILE_FORMAT = "yyyy_MM_dd_HH_mm_ss_SSS";

    /**
     * Format long.
     *
     * @param time the time
     * @param format the format
     * @return the long
     */
    public static long format(String time, String format) {
        if (TextUtils.isEmpty(time)) {
            return 0;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

        long modified = 0;
        try {
            Date date = simpleDateFormat.parse(time);
            modified = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return modified;
    }

    /**
     * Format string.
     *
     * @param time the time
     * @return the string
     */
    public static String format(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_FORMAT);
        return simpleDateFormat.format(new Date(time));
    }

    /**
     * Format string.
     *
     * @param time the time
     * @param format the format
     * @return the string
     */
    public static String format(long time, String format) {
        if (TextUtils.isEmpty(format)) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(new Date(time));
        }

        return null;
    }

    /**
     * Format string.
     *
     * @param date the date
     * @param format the format
     * @return the string
     */
    public static String format(Date date, String format) {
        if (TextUtils.isEmpty(format) || date == null) {
            return null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * Format string.
     *
     * @param timeStr the time str
     * @param srcFormat the src format
     * @param dstFormat the dst format
     * @return the string
     */
    public static String format(String timeStr, String srcFormat,
                                String dstFormat) {
        long time = format(timeStr, srcFormat);
        String result = format(time, dstFormat);
        return result;
    }

    /**
     * Utc to local.
     *
     * @param utcTime the utc time
     * @return the string
     */
    public static String utcToLocal(String utcTime) {
        String localTime = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(UTC_FORMAT);
            Date date = simpleDateFormat.parse(utcTime);
            simpleDateFormat.applyPattern(DEFAULT_FORMAT);
            localTime = simpleDateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return localTime;
    }

    /**
     * Current Time in milliseconds
     * @return current time in milliseconds
     */
    public static long getCurrentTime() {

        return System.currentTimeMillis();
    }

    /**
     * Convert to minute from time in milliseconds
     * @param timeInMiliSec - Time in milliseconds
     * @return minute
     */
    public static int convertToMins(long milliSec) {
        int min = (int) (milliSec / (1000 * 60));

        return min;
    }

    /**
     * Returns Calendar object respective to today morning 00:00Hrs
     * @return
     */
    public static Calendar getTodayCalendar() {
        Calendar date = Calendar.getInstance();

        date.setTime(new Date());
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 1);
        date.set(Calendar.MILLISECOND, 0);

        return date;
    }

    public static long getCurrentTimeInTicks() {
        long currentTimeInTicks = getCurrentTime() * 10000;
        currentTimeInTicks += 621355968000000000L; //based on Jan 1st 1970, the Unix epic

        return currentTimeInTicks;
    }

    /**
     * Convert long time in ticks to date string
     * @param dateTime - time in ticks
     * @return - Date string in dd MMM yyyy format
     * @throws ParseException
     */
    public static String getDateStringFrmTicks(long dateTime) throws ParseException {
        // Rebase to Jan 1st 1970, the Unix epic
        dateTime -= 621355968000000000L;

        long millis = dateTime / 10000;
        return DateFormat.format("dd MMM, yyyy", millis).toString();
    }
}
