package com.diabetes.glucodaily.service;

import android.os.Handler;
import com.diabetes.glucodaily.model.DataManager;
import com.diabetes.glucodaily.model.Meal;
import java.util.Date;
import java.util.List;

public class MealServiceImpl implements MealService {

    private DataManager mDataManager;

    public MealServiceImpl() {
        this.mDataManager = DataManager.getInstance();
        this.mDataManager.init();
    }

    @Override
    public void retrieveAllMeals(final OnMealServicePerformed listener) {
        List<Meal> meals = mDataManager.retrieveAllMeals();
        meals.addAll(meals);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                listener.loadMeals(mDataManager.retrieveAllMeals());
            }
        }, 500);
    }

    @Override
    public void saveMeal(Integer preGlycemia, float dosageInsulin, String imageName, Integer type) {
        Meal meal = new Meal(null,preGlycemia,0,dosageInsulin,new Date(), 0, "", imageName, type);
        mDataManager.addNewMeal(meal);
    }

    @Override
    public void updateMeal(String imagePath,Integer posGlycemia) {
        mDataManager.updateMeal(imagePath,posGlycemia);
    }

    @Override
    public void removeMeal(Meal meal) {
        mDataManager.removeMeal(meal);
    }

    @Override
    public void destroy() {
        mDataManager.destroy();
    }

    @Override
    public void retrieveHistoricMeal(OnMealServicePerformed listener, Integer mealTypeIndex) {
         listener.loadMeals(mDataManager.retrieveMealListType(mealTypeIndex));
    }

    @Override
    public void setMealDaoHelper(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

}
