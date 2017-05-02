package com.diabetes.instameal.main;

import android.content.Context;

import com.diabetes.instameal.Helper.DaoManager;
import com.diabetes.instameal.core.presenter.Presenter;
import com.diabetes.instameal.model.Meal;
import com.diabetes.instameal.service.MealService;
import com.diabetes.instameal.service.MealServiceImpl;
import com.diabetes.instameal.service.OnMealServicePerformed;

import java.util.List;

/**
 * Created by Renato Rosseti on 27/04/17.
 */

public class MainPresenter extends Presenter<MainView> implements OnMealServicePerformed {

    public MainPresenter(MainView view, Context context) {
        super(view, context);
    }

    @Override
    protected void onResume() {
        view.showProgress();
        mealService.retrieveAllMeals(this);
    }

    @Override
    protected void onDestroy() {
        mealService.destroy();
        this.view = null;
    }

    @Override
    public void loadMeals(List<Meal> items) {
        view.hideProgress();
        view.setItems(items);
    }
}
