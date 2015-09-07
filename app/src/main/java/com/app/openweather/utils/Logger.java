package com.app.openweather.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

public class Logger {
    private static final int LOG_LEVEL = android.util.Log.VERBOSE;
    private static final String TAG_PREFIX = "appName";

    //Testing purpose log file
    private static final String LOG_FILE_NAME = "log.txt";

    private static Context mContext;


    /**
     * Initialises the context for Log. Expected to be called once per application creation
     *
     * @param context
     */
    public static void setContext(Context context) {
        mContext = context;
    }


    /**
     * Writes the logs to the Log.txt file
     *
     * @param msg
     */
    private static synchronized void writeToLogFile(String msg) {
        if (mContext != null) {
            FileUtils.appendToFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + LOG_FILE_NAME), msg);
        }
    }

    /**
     * Log Verbose messages
     *
     * @param tag
     * @param msg
     * @return
     */
    public static synchronized int v(String tag, String msg) {
        msg = "\n" + msg;
        writeToLogFile(msg);
        return LOG_LEVEL <= android.util.Log.VERBOSE ? android.util.Log.v(TAG_PREFIX + tag, msg) : 0;
    }

    /**
     * Log debug related statements
     *
     * @param tag
     * @param msg
     * @return
     */
    public static synchronized int d(String tag, String msg) {
        msg = "\n" + msg;
        writeToLogFile(msg);
        return LOG_LEVEL <= android.util.Log.DEBUG ? android.util.Log.d(TAG_PREFIX + tag, msg) : 0;
    }

    /**
     * Log information
     *
     * @param tag
     * @param msg
     * @return
     */
    public static synchronized int i(String tag, String msg) {
        msg = "\n" + msg;
        writeToLogFile(msg);
        return LOG_LEVEL <= android.util.Log.INFO ? android.util.Log.i(TAG_PREFIX + tag, msg) : 0;
    }

    /**
     * Log warning comments with messages
     *
     * @param tag
     * @param msg
     * @return
     */
    public static synchronized int w(String tag, String msg) {
        msg = "\n" + msg;
        writeToLogFile(msg);
        return LOG_LEVEL <= android.util.Log.WARN ? android.util.Log.w(TAG_PREFIX + tag, msg) : 0;
    }

    /**
     * Log warning
     *
     * @param tag
     * @param tr
     * @return
     */
    public static synchronized int w(String tag, Throwable tr) {
        writeToLogFile(tr.getMessage());
        return LOG_LEVEL <= android.util.Log.WARN ? android.util.Log.w(TAG_PREFIX + tag, tr) : 0;
    }

    /**
     * Log error messages
     *
     * @param tag
     * @param msg
     * @return
     */
    public static synchronized int e(String tag, String msg) {
        msg = "\n" + msg;
        writeToLogFile(msg);
        return LOG_LEVEL <= android.util.Log.ERROR ? android.util.Log.e(TAG_PREFIX + tag, msg) : 0;
    }
}
