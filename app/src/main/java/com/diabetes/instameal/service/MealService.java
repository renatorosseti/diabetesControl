package com.diabetes.instameal.service;

import com.diabetes.instameal.model.DaoManager;

public interface MealService {

    void retrieveAllMeals(OnMealServicePerformed listener);

    void destroy();

    void setMealDaoHelper(DaoManager daoManager);

    void saveMeal(String preGlycemia, float dosageInsulin, String imageName, String type);

    void retrieveHistoricMeal(OnMealServicePerformed listener, String mealType);
}
