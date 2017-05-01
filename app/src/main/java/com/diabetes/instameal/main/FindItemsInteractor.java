package com.diabetes.instameal.main;

import com.diabetes.instameal.model.Meal;

import java.util.List;

public interface FindItemsInteractor {

    interface OnFinishedListener {
        void onFinished(List<Meal> items);
    }

    void findItems(OnFinishedListener listener);
}
