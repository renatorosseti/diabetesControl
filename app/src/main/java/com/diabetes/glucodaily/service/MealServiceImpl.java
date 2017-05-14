package com.diabetes.glucodaily.service;

import android.os.Handler;
import com.diabetes.glucodaily.model.DaoManager;
import com.diabetes.glucodaily.model.Meal;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

public class MealServiceImpl implements MealService {

    private DaoManager mDaoManager;

    public MealServiceImpl() {
        this.mDaoManager = DaoManager.getInstance();
        this.mDaoManager.init();
    }

    @Override
    public void retrieveAllMeals(final OnMealServicePerformed listener) {
        List<Meal> meals = mDaoManager.retrieveAllMeals();
        meals.addAll(meals);
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
    public void updateMeal(String imagePath,String posGlycemia) {
        mDaoManager.updateMeal(imagePath,posGlycemia);
    }

    @Override
    public void destroy() {
        mDaoManager.destroy();
    }

    @Override
    public void retrieveHistoricMeal(OnMealServicePerformed listener, int mealTypeIndex) {
         listener.loadMeals(mDaoManager.retrieveMealListType(mealTypeIndex));
    }

    @Override
    public void setMealDaoHelper(DaoManager daoManager) {
        this.mDaoManager = daoManager;
    }

}
