package com.diabetes.instameal.service;

import android.os.Handler;
import com.diabetes.instameal.model.DaoManager;
import com.diabetes.instameal.model.Meal;
import java.util.Date;

public class MealServiceImpl implements MealService {

    private DaoManager mDaoManager;

    public MealServiceImpl() {
        this.mDaoManager = DaoManager.getInstance();
        this.mDaoManager.init();
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
    public void saveMeal(String preGlycemia, float dosageInsulin, String imageName, String type) {

        Meal meal = new Meal(null,preGlycemia,"",dosageInsulin,new Date(), 0, "", imageName, type);

        mDaoManager.addNewMeal(meal);
    }

    @Override
    public void updateMeal(Meal meal) {
        mDaoManager.updateMeal(meal);
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
