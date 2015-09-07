package com.app.weather.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.Looper;
import android.os.StatFs;
import android.util.Log;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Locale;

public class CrashHandler implements UncaughtExceptionHandler {

    private Context mContext;

    private static final String MESSAGE_TYPE = "message/rfc822";
    private static final String MESSAGE_SUBJECT = "New Crashed is reported";
    private static final String EMAIL_ID = "coderzheaven@gmail.com"; //Change this to your email id, to which you need report

    private static final boolean SHOW_REPORT_OPTION = false; // If you want the report to be emailed, set this to true

    public CrashHandler(Context ctx) {
        mContext = ctx;
    }

    private StatFs getStatFs() {
        File path = Environment.getDataDirectory();
        return new StatFs(path.getPath());
    }

    private long getAvailableInternalMemorySize(StatFs stat) {
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return availableBlocks * blockSize;
    }

    private long getTotalInternalMemorySize(StatFs stat) {
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return totalBlocks * blockSize;
    }

    private void addInformation(StringBuilder message) {
        message.append("Locale: ").append(Locale.getDefault()).append('\n');
        try {
            PackageManager pm = mContext.getPackageManager();
            PackageInfo pi;
            pi = pm.getPackageInfo(mContext.getPackageName(), 0);
            message.append("Version: ").append(pi.versionName).append('\n');
            message.append("Package: ").append(pi.packageName).append('\n');
        } catch (Exception e) {
            Log.e("CustomExceptionHandler", "Error", e);
            message.append("Could not get Version information for ").append(
                    mContext.getPackageName());
        }

        message.append("Phone Model: ").append(android.os.Build.MODEL)
                .append('\n');
        message.append("Android Version: ")
                .append(android.os.Build.VERSION.RELEASE).append('\n');
        message.append("Board: ").append(android.os.Build.BOARD).append('\n');
        message.append("Brand: ").append(android.os.Build.BRAND).append('\n');
        message.append("Device: ").append(android.os.Build.DEVICE).append('\n');
        message.append("Host: ").append(android.os.Build.HOST).append('\n');
        message.append("ID: ").append(android.os.Build.ID).append('\n');
        message.append("Model: ").append(android.os.Build.MODEL).append('\n');
        message.append("Type: ").append(android.os.Build.TYPE).append('\n');
        StatFs stat = getStatFs();
        message.append("Available Internal memory: ")
                .append(getAvailableInternalMemorySize(stat)).append('\n');
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        try {
            StringBuilder report = new StringBuilder();
            report.append("Information :").append('\n');
            addInformation(report);
            report.append('\n').append('\n');
            report.append("Stack:\n");

            final Writer result = new StringWriter();
            final PrintWriter printWriter = new PrintWriter(result);

            e.printStackTrace(printWriter);

            report.append(result.toString());

            printWriter.close();

            report.append('\n');
            report.append("**** End of current Report ***");

            sendErrorMail(report);

        } catch (Throwable ignore) {
            Log.d("CrashHandler", "ignoring the exception");
        }
    }

    public void sendErrorMail(final StringBuilder errorContent) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();

                builder.create();
                builder.setNegativeButton("OK", getOKListener());

                if (SHOW_REPORT_OPTION) {
                    builder.setPositiveButton("Report", getReportListener(errorContent));
                }

                builder.setMessage("Oops, looks like something is wrong here. Give it a try again in a few minutes. ");
                builder.show();

                Looper.loop();
            }
        }.start();
    }

    private DialogInterface.OnClickListener getReportListener(final StringBuilder errorContent) {
        DialogInterface.OnClickListener reportListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mContext.startActivity(getReportIntent(errorContent));
                System.exit(0);
            }
        };

        return reportListener;
    }

    private DialogInterface.OnClickListener getOKListener() {
        DialogInterface.OnClickListener reportListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.exit(0);
            }
        };

        return reportListener;
    }
    private Intent getReportIntent(final StringBuilder errorContent) {
        StringBuilder body = new StringBuilder("Error Content: \n").append(errorContent).append('\n');

        Intent sendEmailIntent = new Intent(Intent.ACTION_SEND);

        sendEmailIntent.setType(MESSAGE_TYPE);
        sendEmailIntent.putExtra(Intent.EXTRA_TEXT, body.toString());
        sendEmailIntent.putExtra(Intent.EXTRA_SUBJECT, MESSAGE_SUBJECT);
        sendEmailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{EMAIL_ID});

        return sendEmailIntent;
    }

}