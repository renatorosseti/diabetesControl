package com.diabetes.glucodaily.Helper;

import android.os.Environment;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.diabetes.glucodaily.R;
import com.diabetes.glucodaily.core.ui.BaseActivity;
import java.util.Calendar;
import java.util.Date;

public class DataHelper {

    public final static int BREAKFAST = 0;

    public final static int MORNING_SNACK = 1;

    public final static int LUNCH = 2;

    public final static int AFTERNOON_COFFEE = 3;

    public final static int DINNER = 4;

    public final static int NIGHT_SNACK = 5;

    public static String getPath(String imageName) {
        String path = new StringBuilder(Environment.getExternalStorageDirectory().toString()).append("/Android/data/").append(BaseActivity.getContext().getPackageName()).append("/files/").append(imageName).toString();
        return path;
    }

    public static String getMealTypeByIndex() {
        return null;
    }

    public static String removeBreakLine(String text) {
        return text.replace("\n","");
    }

    public static int getMealTypeRecommendation(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.get(Calendar.HOUR_OF_DAY);
        Log.i("Time", "Time: "+calendar.get(Calendar.HOUR_OF_DAY));
        if(calendar.get(Calendar.HOUR_OF_DAY) >= 5 && calendar.get(Calendar.HOUR_OF_DAY) < 10) {
            return BREAKFAST;
        } else if (calendar.get(Calendar.HOUR_OF_DAY) >= 10 && calendar.get(Calendar.HOUR_OF_DAY) < 11) {
            return MORNING_SNACK;
        } else if (calendar.get(Calendar.HOUR_OF_DAY) >= 11 && calendar.get(Calendar.HOUR_OF_DAY) < 14) {
            return LUNCH;
        } else if (calendar.get(Calendar.HOUR_OF_DAY) >= 14 && calendar.get(Calendar.HOUR_OF_DAY) < 19) {
            return AFTERNOON_COFFEE;
        } else if (calendar.get(Calendar.HOUR_OF_DAY) >= 19 && calendar.get(Calendar.HOUR_OF_DAY) <= 23) {
            return DINNER;
        } else {
            return NIGHT_SNACK;
        }
    }
}
