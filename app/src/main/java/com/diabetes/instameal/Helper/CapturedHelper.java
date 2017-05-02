package com.diabetes.instameal.Helper;


import android.content.Context;
import android.os.Environment;
import android.util.Log;

public class CapturedHelper {

    public static String getPath(Context context, String imageName) {
        String path = Environment.getExternalStorageDirectory().toString()+"/Android/data/"+context.getPackageName()+"/files/"+imageName;
        Log.d("Files", "Path: " + path);
        return path;
    }
}
