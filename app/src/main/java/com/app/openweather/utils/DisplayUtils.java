package com.app.openweather.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by Krishnaprasad.n on 7/20/2015.
 */
public class DisplayUtils {

    private static int HONEYCOMB = 11;

    /**
     * Gets screen size.
     *
     * @param context the context
     * @return the screen size
     */
    public static final Point getScreenSize(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);

        int widthPixels = metrics.widthPixels;
        int heightPixels = metrics.heightPixels;

        // includes window decorations (status bar bar/menu bar)
        if (Build.VERSION.SDK_INT >= 14 && Build.VERSION.SDK_INT < 17) {
            try {
                widthPixels = (Integer) Display.class.getMethod("getRawWidth")
                        .invoke(display);
                heightPixels = (Integer) Display.class
                        .getMethod("getRawHeight").invoke(display);
            } catch (Exception ignored) {
            }
        }
        // includes window decorations (status bar bar/menu bar)
        else if (Build.VERSION.SDK_INT >= 17) {
            try {
                Point realSize = new Point();
                Display.class.getMethod("getRealSize", Point.class).invoke(
                        display, realSize);
                widthPixels = realSize.x;
                heightPixels = realSize.y;
            } catch (Exception ignored) {
            }
        }

        return new Point(widthPixels, heightPixels);
    }

    /**
     * Gets screen density.
     *
     * @param context the context
     * @return the screen density
     */
    public static final float getScreenDensity(Context context) {
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        float screenDensity = dm.density;
        return screenDensity;
    }

    /**
     * Gets screen density dpi.
     *
     * @param context the context
     * @return the screen density dpi
     */
    public static int getScreenDensityDpi(Context context) {
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        int screenDensityDpi = dm.densityDpi;
        return screenDensityDpi;
    }

    /**
     * Gets device height.
     *
     * @param context the context
     * @return the device height
     */
    public static int getDeviceHeight(Context context) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay()
                .getMetrics(displaymetrics);
        return displaymetrics.heightPixels;
    }

    /**
     * use for getting device width
     *
     * @param context
     * @return width of your device
     */
    public static int getDeviceWidth(Context context) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay()
                .getMetrics(displaymetrics);
        return displaymetrics.widthPixels;
    }

    /**
     * get the version of the application
     *
     * @param context
     * @return version code.
     */
    public static int getAppVersionCode(Context context) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return packageInfo.versionCode;
    }

    /**
     * Returns whether the device is Tablet or Phone based on the screen resolution
     *
     * @param context
     * @return
     */
    public static boolean isTablet(Context context) {
        if ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE) {
            return true;
        } else if ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE) {
            return true;
        }

        return false;
    }

    /**
     * Returns whether current build version is >= 3.0
     *
     * @return
     */
    public static boolean isHoneyCombAndAbove() {
        if (Build.VERSION.SDK_INT >= HONEYCOMB) {
            return true;
        }
        return false;
    }

    /**
     * Is xlarge device.
     *
     * @param context the context
     * @return the boolean
     */
    public static boolean isXlargeDevice(Context context) {
        if ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE) {
            return true;
        } else {
            return false;
        }
    }

    public int getScreenOrientation(Context context){
        return context.getResources().getConfiguration().orientation;
    }
}
