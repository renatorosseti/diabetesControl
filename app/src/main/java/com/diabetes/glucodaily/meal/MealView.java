package com.diabetes.glucodaily.meal;

import com.diabetes.glucodaily.core.view.View;
import com.diabetes.glucodaily.model.Meal;
import com.diabetes.glucodaily.model.MealHolder;

import java.util.List;

public interface MealView extends View {

    void showMealItems(List<MealHolder> meals);

    void showErrorMessage();

    void navigateToMainFeed();
}
