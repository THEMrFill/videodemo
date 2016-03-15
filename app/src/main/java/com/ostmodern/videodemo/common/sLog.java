package com.ostmodern.videodemo.common;

import android.util.Log;

/**
 * Created by philip.arnold on 14/03/2016.
 */

public class sLog {
    private static final int MAX_LENGTH = 1000;
    private static final boolean runLogging = true;

    public static void w(String tag, String message) {
        if (runLogging) {
            if (message.length() <= MAX_LENGTH) {
                Log.w(tag, message);
            } else {
                String outText = "";
                String mess = message;
                while (true) {
                    if (mess.length() <= MAX_LENGTH) {
                        Log.w(tag, mess);
                        break;
                    } else {
                        outText = mess.substring(0, MAX_LENGTH);
                        mess = mess.substring(MAX_LENGTH);
                        Log.w(tag, outText);
                    }
                }
            }
        }
    }
    public static void w(String tag, String message, Throwable tr) {
        if (runLogging) {
            Log.w(tag, message, tr);
        }
    }
    public static void d(String tag, String message) {
        if (runLogging) {
            Log.d(tag, message);
        }
    }
    public static void e(String tag, String message) {
        if (runLogging) {
            Log.e(tag, message);
        }
    }
    public static void e(String tag, String message, Throwable tr) {
        if (runLogging) {
            Log.e(tag, message, tr);
        }
    }
    public static void i(String tag, String message) {
        if (runLogging) {
            Log.i(tag, message);
        }
    }
}
