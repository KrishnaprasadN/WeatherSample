package com.app.openweather.utils;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by Krishnaprasad.n on 7/20/2015.
 */
public class AlertDialogUtils {

    public static ProgressDialog mProgressDialog;

    /**
     * Show alert with oK.
     *
     * @param context the context
     * @param title the title
     * @param message the message
     */
    public static void showAlertWithOK(Context context, String title, String message) {
        Dialog alertDialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .create();

        alertDialog.show();
    }

    /**
     * Show alert with oK.
     *
     * @param context the context
     * @param title the title
     * @param message the message
     * @param okListener the ok listener
     */
    public static void showAlertWithOK(Context context, String title, String message, DialogInterface.OnClickListener okListener) {
        Dialog alertDialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, okListener)
                .create();

        alertDialog.show();
    }

    /**
     * Show alert with oK cancel.
     *
     * @param context the context
     * @param title the title
     * @param message the message
     * @param okListener the ok listener
     */
    public static void showAlertWithOKCancel(Context context, String title, String message, DialogInterface.OnClickListener okListener) {
        Dialog alertDialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, okListener)
                .setNegativeButton(android.R.string.cancel, null)
                .create();

        alertDialog.show();
    }

    /**
     * Show alert.
     *
     * @param context the context
     * @param title the title
     * @param message the message
     * @param isCancellable the is cancellable
     * @param positiveButtonTxt the positive button txt
     * @param negativeButtonTxt the negative button txt
     * @param positiveListener the positive listener
     * @param negativeListener the negative listener
     */
    public static void showAlert(Context context, String title, String message, boolean isCancellable,
                                 CharSequence positiveButtonTxt, CharSequence negativeButtonTxt, DialogInterface.OnClickListener positiveListener,
                                 DialogInterface.OnClickListener negativeListener) {
        Dialog alertDialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveButtonTxt, positiveListener)
                .setNegativeButton(negativeButtonTxt, negativeListener)
                .setCancelable(isCancellable)
                .create();

        alertDialog.show();
    }

    /**
     * Show progress dialog.
     *
     * @param context the context
     * @param title the title
     * @param message the message
     * @param isCancellable the is cancellable
     */
    public static void showProgressDialog(Context context, String title, String message, boolean isCancellable) {
        if (context instanceof Activity) {
            if (!((Activity) context).isFinishing()) {
                mProgressDialog = ProgressDialog.show(context, title, message, true);
                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                mProgressDialog.setCancelable(isCancellable);
            }
        }
    }

    /**
     * Is progress dialog visible.
     *
     * @return the boolean
     */
    public static boolean isProgressDialogVisible() {
        return (mProgressDialog != null);
    }

    /**
     * Dismiss progress dialog.
     */
    public static void dismissProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }

        mProgressDialog = null;
    }
}
