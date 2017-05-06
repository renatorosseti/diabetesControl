package com.diabetes.instameal.model;


import android.graphics.Bitmap;

public class MealRecycler {
    private Meal meal;
    private Bitmap bitmap;

    public MealRecycler(Meal meal, Bitmap bitmap) {
        this.meal = meal;
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public Meal getMeal() {
        return meal;
    }
}
