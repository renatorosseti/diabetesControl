package com.diabetes.instameal.core.presenter;

import com.diabetes.instameal.core.view.View;
import com.diabetes.instameal.service.MealService;
import com.diabetes.instameal.service.MealServiceImpl;

public abstract class Presenter<V extends View> {

    protected V view;

    protected MealService mealService;

    public Presenter(V view){
        this.view = view;
        this.mealService = new MealServiceImpl();
    }

    protected abstract void onDestroy();

    protected abstract void onResume();


}
