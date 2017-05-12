package com.diabetes.glucodaily.Helper;

import android.os.Environment;
import com.diabetes.glucodaily.core.ui.MealApplication;

import java.util.Calendar;
import java.util.Date;

public class DataHelper {

    private final static int BREAKFAST = 0;
    private final static int MORNING_SNACK = 1;
    private final static int LUNCH = 2;
    private final static int AFTERNOON_SNACK = 3;
    private final static int AFTERNOON_COFFEE = 4;
    private final static int DINNER = 5;
    private final static int NIGHT_SNACK = 6;

    public static String getPath(String imageName) {
        String path = new StringBuilder(Environment.getExternalStorageDirectory().toString()).append("/Android/data/").append(MealApplication.getContext().getPackageName()).append("/files/").append(imageName).toString();
        return path;
    }

    public static String removeBreakLine(String text) {
        return text.replace("\n","");
    }

    public static int getMealTypeRecommendation(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.get(Calendar.HOUR_OF_DAY);
        if(calendar.get(Calendar.HOUR_OF_DAY) > 5 && calendar.get(Calendar.HOUR_OF_DAY) <= 9) {
            return BREAKFAST;
        } else if (calendar.get(Calendar.HOUR_OF_DAY) > 9 && calendar.get(Calendar.HOUR_OF_DAY) <= 11) {
            return MORNING_SNACK;
        } else if (calendar.get(Calendar.HOUR_OF_DAY) > 11 && calendar.get(Calendar.HOUR_OF_DAY) <= 13) {
            return LUNCH;
        } else if (calendar.get(Calendar.HOUR_OF_DAY) > 13 && calendar.get(Calendar.HOUR_OF_DAY) <= 15) {
            return AFTERNOON_SNACK;
        } else if (calendar.get(Calendar.HOUR_OF_DAY) > 15 && calendar.get(Calendar.HOUR_OF_DAY) <= 19) {
            return AFTERNOON_COFFEE;
        } else if (calendar.get(Calendar.HOUR_OF_DAY) > 19 && calendar.get(Calendar.HOUR_OF_DAY) <= 22) {
            return DINNER;
        } else {
            return NIGHT_SNACK;
        }
    }
}
