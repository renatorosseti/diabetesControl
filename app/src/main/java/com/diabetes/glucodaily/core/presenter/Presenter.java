package com.diabetes.glucodaily.core.presenter;

import com.diabetes.glucodaily.core.view.View;
import com.diabetes.glucodaily.service.MealService;
import com.diabetes.glucodaily.service.MealServiceImpl;

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
