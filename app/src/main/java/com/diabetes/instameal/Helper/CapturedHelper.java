package com.diabetes.instameal.Helper;

import android.os.Environment;
import com.diabetes.instameal.core.ui.MealApplication;
import java.io.File;

public class CapturedHelper {

    public static String getPath(String imageName) {
        String path = new StringBuilder(Environment.getExternalStorageDirectory().toString()).append("/Android/data/").append(MealApplication.getContext().getPackageName()).append("/files/").append(imageName).toString();
        return path;
    }
}
