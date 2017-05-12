package com.diabetes.glucodaily.Helper;

import android.os.Environment;
import com.diabetes.glucodaily.core.ui.MealApplication;

public class CapturedHelper {

    public static String getPath(String imageName) {
        String path = new StringBuilder(Environment.getExternalStorageDirectory().toString()).append("/Android/data/").append(MealApplication.getContext().getPackageName()).append("/files/").append(imageName).toString();
        return path;
    }
}
