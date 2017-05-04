package com.diabetes.instameal.core.ui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

public abstract class MealApplication extends AppCompatActivity {

    private static Context mContext;

    protected void init() {
        if(mContext == null) {
            mContext = getApplicationContext();
        }
    }

    public static Context getContext() {
       return mContext;
    }
}
