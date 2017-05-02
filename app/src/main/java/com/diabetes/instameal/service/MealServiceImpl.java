package com.diabetes.instameal.service;

import android.content.Context;
import android.os.Handler;
import com.diabetes.instameal.Helper.DaoManager;
import com.diabetes.instameal.model.Meal;
import java.util.Date;

public class MealServiceImpl implements MealService {

    private DaoManager mDaoManager;

    public MealServiceImpl(Context context) {
        this.mDaoManager = DaoManager.getInstance();
        this.mDaoManager.init(context);
    }

    @Override
    public void retrieveAllMeals(final OnMealServicePerformed listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                listener.loadMeals(mDaoManager.retrieveAllMeals());
            }
        }, 500);
    }

    @Override
    public void saveMeal(int preGlycemia, float dosageInsulin, String imageName) {

        Meal meal = new Meal(null,preGlycemia,0,dosageInsulin,new Date(), 0, "", imageName,"LUNCH");

        mDaoManager.addNewMeal(meal);
    }

    @Override
    public void destroy() {
        mDaoManager.destroy();
    }

    @Override
    public void retrieveHistoricMeal(OnMealServicePerformed listener, String mealType) {
         listener.loadMeals(mDaoManager.retrieveMealListType(mealType));
    }

    @Override
    public void setMealDaoHelper(DaoManager daoManager) {
        this.mDaoManager = daoManager;
    }
}
