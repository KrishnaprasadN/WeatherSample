package com.app.openweather.utils;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by Krishnaprasad.n on 7/20/2015.
 */
public class FileUtils {
    private static final String TAG = "FileUtils";
    private static final int IO_BUFFER_SIZE = 1024;

    /**
     * Create file.
     *
     * @param absPath the abs path
     * @param force   the force
     * @return the boolean
     */
    public static boolean create(String absPath) {
        if (TextUtils.isEmpty(absPath)) {
            return false;
        }

        if (exists(absPath)) {
            return true;
        }

        mkdir(getParent(absPath));

        try {
            File file = new File(absPath);
            return file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Mkdir .
     *
     * @param absPath the abs path
     * @param force   the force
     * @return the boolean
     */
    public static boolean mkdir(String absPath) {
        File file = new File(absPath);
        if (exists(absPath) && !isFolder(absPath)) {
            delete(file);
        }

        try {
            file.mkdir();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exists(file);
    }

    /**
     * Delete boolean.
     *
     * @param absPath the abs path
     * @return the boolean
     */
    public static boolean delete(String absPath) {
        if (TextUtils.isEmpty(absPath)) {
            return false;
        }

        File file = new File(absPath);
        return delete(file);
    }

    /**
     * Delete boolean.
     *
     * @param file the file
     * @return the boolean
     */
    public static boolean delete(File file) {
        if (!exists(file)) {
            return true;
        }

        if (file.isFile()) {
            return file.delete();
        }

        boolean result = true;
        File files[] = file.listFiles();
        for (int index = 0; index < files.length; index++) {
            result |= delete(files[index]);
        }
        result |= file.delete();

        return result;
    }

    /**
     * Exists boolean.
     *
     * @param absPath the abs path
     * @return the boolean
     */
    public static boolean exists(String absPath) {
        if (TextUtils.isEmpty(absPath)) {
            return false;
        }
        File file = new File(absPath);
        return exists(file);
    }

    /**
     * Exists boolean.
     *
     * @param file the file
     * @return the boolean
     */
    public static boolean exists(File file) {
        return file == null ? false : file.exists();
    }

    /**
     * Is file.
     *
     * @param absPath the abs path
     * @return the boolean
     */
    public final static boolean isFile(String absPath) {
        boolean exists = exists(absPath);
        if (!exists) {
            return false;
        }

        File file = new File(absPath);
        return isFile(file);
    }

    /**
     * Is file.
     *
     * @param file the file
     * @return the boolean
     */
    public final static boolean isFile(File file) {
        return file == null ? false : file.isFile();
    }

    /**
     * Is folder.
     *
     * @param absPath the abs path
     * @return the boolean
     */
    public final static boolean isFolder(String absPath) {
        boolean exists = exists(absPath);
        if (!exists) {
            return false;
        }

        File file = new File(absPath);
        return file.isDirectory();
    }

    /**
     * Gets parent.
     *
     * @param file the file
     * @return the parent
     */
    public final static String getParent(File file) {
        return file == null ? null : file.getParent();
    }

    /**
     * Gets parent.
     *
     * @param absPath the abs path
     * @return the parent
     */
    public final static String getParent(String absPath) {
        if (TextUtils.isEmpty(absPath)) {
            return null;
        }
        absPath = cleanPath(absPath);
        File file = new File(absPath);
        return getParent(file);
    }


    /**
     * Write boolean.
     *
     * @param absPath the abs path
     * @param text    the text
     * @return the boolean
     */
    public static final boolean write(String absPath, String text) {
        if (!create(absPath)) {
            return false;
        }

        FileOutputStream fos = null;
        PrintWriter pw = null;
        try {
            fos = new FileOutputStream(absPath);
            pw = new PrintWriter(fos);
            pw.write(text);
            pw.flush();
        } catch (Exception e) {
            Log.d(TAG, "Exception - " + e.getMessage());
        } finally {
            close(pw);
            close(fos);
        }

        return true;
    }

    /**
     * Write boolean.
     *
     * @param absPath the abs path
     * @param ips     the ips
     * @return the boolean
     */
    public static final boolean write(String absPath, InputStream ips) {
        if (!create(absPath)) {
            return false;
        }

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(absPath);
            byte buffer[] = new byte[IO_BUFFER_SIZE];
            int count = ips.read(buffer);
            for (; count != -1; ) {
                fos.write(buffer, 0, count);
                count = ips.read(buffer);
            }
            fos.flush();
        } catch (Exception e) {
            return false;
        } finally {
            close(fos);
        }

        return true;
    }

    /**
     * Read string.
     *
     * @param absPath the abs path
     * @return the string
     */
    public static final String read(String absPath) {
        String text = null;
        InputStream ips = null;
        try {
            ips = new FileInputStream(absPath);
            text = read(ips);
            ips.close();
        } catch (Exception e) {
            Log.d(TAG, "Exception - " + e.getMessage());
        }

        return text;
    }

    /**
     * Read string.
     *
     * @param inputStream the input stream
     * @return the string
     */
    public static final String read(InputStream inputStream) {
        if (inputStream != null) {
            StringBuilder sb = new StringBuilder();
            String line;

            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                close(inputStream);
            }
            return sb.toString();
        } else {
            return "";
        }
    }

    /**
     * Gets stream.
     *
     * @param absPath the abs path
     * @return the stream
     */
    public static final InputStream getStream(String absPath) {
        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(absPath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return inputStream;
    }

    /**
     * Clean path.
     *
     * @param absPath the abs path
     * @return the string
     */
    public static String cleanPath(String absPath) {
        if (TextUtils.isEmpty(absPath)) {
            return absPath;
        }
        try {
            File file = new File(absPath);
            absPath = file.getCanonicalPath();
        } catch (Exception e) {

        }
        return absPath;
    }

    private static final void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                Log.d(TAG, "IOException - " + e.getMessage());
            }
        }
    }

    /**
     * Write bytes of data to file specified
     *
     * @param buffer - Data to be written
     * @param file   - File pointer to a local storage
     * @throws IOException - Throws when File not found/connection issues/error cases
     */
    public static void writeToFile(File file, byte[] buffer) throws IOException {
        DataOutputStream fos = new DataOutputStream(new FileOutputStream(file));

        fos.write(buffer);
        fos.flush();
        fos.close();

        fos = null;
    }

    public static void writeToFile(File file, String dataContent) {
        FileWriter writer = null;

        try {
            writer = new FileWriter(file);
            writer.write(dataContent);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void appendToFile(File file, String dataContent) {
        FileWriter writer;

        try {
            writer = new FileWriter(file, true);
            writer.append(dataContent);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Read the contents of file
     *
     * @param file to be read
     * @return Contents
     */
    public static String readFromFile(File file) {
        String strLine = "";
        StringBuilder text = new StringBuilder();

        try {
            FileReader fReader = new FileReader(file);
            BufferedReader bReader = new BufferedReader(fReader);
            while ((strLine = bReader.readLine()) != null) {
                text.append(strLine);
            }
            bReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return text.toString();
    }

    private static void deleteRecursive(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory()) {
            for (File child : fileOrDirectory.listFiles()) {
                deleteRecursive(child);
            }
        }

        fileOrDirectory.delete();
    }

    public static void writeJsonArrayToFile(File file, JSONArray jsonArray) {
        FileWriter writer = null;

        try {
            writer = new FileWriter(file);
            writer.write(jsonArray.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
