package com.diabetes.instameal.main;

import android.os.Handler;

import com.diabetes.instameal.model.Meal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FindItemsInteractorImpl implements FindItemsInteractor {
    @Override
    public void findItems(final OnFinishedListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                listener.onFinished(retrieveMeals());
            }
        }, 2000);
    }

    private List<Meal> retrieveMeals() {
        ArrayList<Meal> meals = new ArrayList<>();

        meals.add(new Meal(1L, 66, 145, (float) 5.0, new Date(), 0,"", "test 1", "Dinner"));
        meals.add(new Meal(2L, 76, 125, (float) 5.0, new Date(), 0,"", "test 2", "Dinner"));
        meals.add(new Meal(3L, 86, 135, (float) 5.0, new Date(), 0,"", "test 3", "Lunch"));
        meals.add(new Meal(4L, 96, 155, (float) 5.0, new Date(), 0,"", "test 4", "Breakfast"));
        meals.add(new Meal(5L, 106, 165, (float) 5.0, new Date(), 0,"", "test 5", "Lunch"));
        return meals;
    }
}
