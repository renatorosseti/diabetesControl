package com.diabetes.instameal.service;

import android.os.Handler;

import com.diabetes.instameal.Helper.DaoManager;
import com.diabetes.instameal.model.Meal;
import java.util.List;

public class MealServiceImpl implements MealService {

    @Override
    public void findItems(final OnFinishedListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                listener.onFinished(retrieveMeals());
            }
        }, 500);
    }

    private List<Meal> retrieveMeals() {
        return DaoManager.getInstance().retrieveAllMeals();
    }
}
