package com.app.openweather.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Krishnaprasad.n on 7/20/2015.
 */
public class CommonUtils {

    /**
     * Show toast.
     *
     * @param context the context
     * @param text the text
     * @param duration the duration
     */
    public static void showToast(Context context, CharSequence text, int duration) {
        Toast.makeText(context, text, duration).show();
    }

    /**
     * Is gPS enabled.
     *
     * @param context the context
     * @return the boolean
     */
    public static boolean isGPSEnabled(Context context) {
        final LocationManager locationManager = (LocationManager) context.getSystemService( Context.LOCATION_SERVICE );
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER );
    }

    /**
     * Gets iMEI.
     *
     * @param context the context
     * @return the iMEI
     */
    public static String getIMEI(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }

    /**
     * Gets uID.
     *
     * @param context the context
     * @return the uID
     */
    public static String getUID(Context context) {
        return getIMEI(context);
    }

    /**
     * Show no gps dialog.
     *
     * @param context the context
     */
    public static void showNoGpsDialog(final Context context) {
        AlertDialogUtils.showAlert(context, "GPS Settings", "GPS is not enabled, do you want to enable it?", false, "Settings", "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        context.startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                }, null);

    }

    /**
     * Show iME.
     *
     * @param context the context
     */
    public static void showKeyboard(Context context) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * Hide iME.
     *
     * @param context the context
     * @param view the view
     */
    public static void hideKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

    }

    public static boolean isEmailIdValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if (matcher.matches())
            return true;
        else
            return false;
    }

    public static String getCurrentDate(String dateFormat) {
        return new SimpleDateFormat(dateFormat).format(new Date().getTime());
    }

    public static boolean isSDCardAvailable(Context mContext) {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);

    }
}
