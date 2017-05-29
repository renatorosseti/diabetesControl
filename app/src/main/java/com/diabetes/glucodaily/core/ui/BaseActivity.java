package com.diabetes.glucodaily.core.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.widget.Toast;

public abstract class BaseActivity extends AppCompatActivity {

    private static Context mContext;

    private static Activity activity;

    public static final Boolean VERTICAL = false;

    public static final Boolean HORIZONTAL = true;

    protected void init() {
        if(mContext == null) {
            mContext = getApplicationContext();
            activity = this;
        }
    }

    public static Context getContext() {
       return mContext;
    }

    protected void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public static int getDisplayParam(Boolean param) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return param == HORIZONTAL ? size.x : size.y;
    }

}
