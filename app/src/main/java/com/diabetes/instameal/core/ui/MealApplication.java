package com.diabetes.instameal.core.ui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.widget.Toast;

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

    protected void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}
