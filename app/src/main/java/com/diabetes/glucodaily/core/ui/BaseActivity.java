package com.diabetes.glucodaily.core.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.MenuItem;
import android.widget.Toast;

import com.diabetes.glucodaily.R;

public abstract class BaseActivity extends AppCompatActivity {

    private static Context mContext;

    private static Activity activity;

    private ProgressDialog progressDialog;

    protected MenuItem mGarbageItem;

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

    public void setVisibleGarbageItem(boolean visible) {
        mGarbageItem.setVisible(visible);
        getSupportActionBar().setDisplayHomeAsUpEnabled(visible);
    }

    public Boolean isGarbageItemVisible() {
        return mGarbageItem.isVisible();
    }

    public void showProgress() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
        }
        progressDialog.setMessage(getString(R.string.loading));
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
    }

    protected void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
