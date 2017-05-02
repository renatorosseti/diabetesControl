package com.diabetes.instameal.service;

import com.diabetes.instameal.model.Meal;

import java.util.List;

public interface MealService {

    interface OnFinishedListener {
        void onFinished(List<Meal> items);
    }

    void findItems(OnFinishedListener listener);
}
