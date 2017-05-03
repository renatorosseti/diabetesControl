package com.diabetes.instameal.Helper;


import android.content.Context;
import android.os.Environment;

public class CapturedHelper {

    public static String getPath(Context context, String imageName) {
        String path = new StringBuilder(Environment.getExternalStorageDirectory().toString()).append("/Android/data/").append(context.getPackageName()).append("/files/").append(imageName).toString();
//        Log.d("Files", "Path: " + path);
        return path;
    }
}
