package com.diabetes.glucodaily.service;

import com.diabetes.glucodaily.model.DaoManager;

public interface MealService {

    void retrieveAllMeals(OnMealServicePerformed listener);

    void destroy();

    void setMealDaoHelper(DaoManager daoManager);

    void saveMeal(String preGlycemia, float dosageInsulin, String imageName, String type);

    void retrieveHistoricMeal(OnMealServicePerformed listener, int mealTypeIndex);

    void updateMeal(String imagePath,String posGlycemia);


}
