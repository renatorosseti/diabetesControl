package com.diabetes.instameal.service;

import com.diabetes.instameal.model.DaoManager;

public interface MealService {

    void retrieveAllMeals(OnMealServicePerformed listener);

    void destroy();

    void setMealDaoHelper(DaoManager daoManager);

    void saveMeal(int preGlycemia, float dosageInsulin, String imageName);

    void retrieveHistoricMeal(OnMealServicePerformed listener, String mealType);
}
