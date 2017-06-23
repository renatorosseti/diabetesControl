package com.diabetes.glucodaily.service;

import com.diabetes.glucodaily.model.DataManager;
import com.diabetes.glucodaily.model.Meal;

public interface MealService {

    void retrieveAllMeals(OnMealServicePerformed listener);

    void destroy();

    void setMealDaoHelper(DataManager dataManager);

    void saveMeal(Integer preGlycemia, float dosageInsulin, String imageName, String type);

    void retrieveHistoricMeal(OnMealServicePerformed listener, int mealTypeIndex);

    void updateMeal(String imagePath,Integer posGlycemia);

    void removeMeal(Meal meal);


}
