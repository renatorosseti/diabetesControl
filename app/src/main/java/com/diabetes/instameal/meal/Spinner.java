package com.diabetes.instameal.meal;


import android.content.Context;
import android.view.View;
import android.widget.AdapterView;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

public class Spinner extends MaterialBetterSpinner {
    protected int selectedPosition = -1;

    public Spinner(Context context) {
        super(context);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        super.onItemClick(adapterView, view, i, l);
        this.selectedPosition = i;
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }
}
