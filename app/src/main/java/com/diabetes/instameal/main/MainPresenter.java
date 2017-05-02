package com.diabetes.instameal.main;

import android.content.Context;

import com.diabetes.instameal.Helper.DaoManager;
import com.diabetes.instameal.core.presenter.Presenter;
import com.diabetes.instameal.model.Meal;
import com.diabetes.instameal.service.MealService;

import java.util.List;

/**
 * Created by Renato Rosseti on 27/04/17.
 */

public class MainPresenter extends Presenter<MainView> implements MealService.OnFinishedListener {


    private DaoManager mealDaoHelper;

    private MealService mealService;

    private Context mContext;

    public MainPresenter(MainView view, MealService mealService) {
        super(view);
        this.mealService = mealService;
    }

    public void onResume(Context context) {
        this.mContext = context;
        mealDaoHelper = DaoManager.getInstance();
        mealDaoHelper.init(context);
        view.showProgress();
        mealService.findItems(this);
    }

    public void onDestroy() {
        mealDaoHelper.destroy();
        this.view = null;
    }


    public void setMealDaoHelper(DaoManager mealDaoHelper) {
        this.mealDaoHelper = mealDaoHelper;
    }

    @Override
    public void onFinished(List<Meal> items) {
        view.hideProgress();
        view.setItems(items);
    }
}
