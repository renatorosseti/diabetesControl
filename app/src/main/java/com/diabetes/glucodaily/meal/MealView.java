package com.diabetes.glucodaily.meal;

import com.diabetes.glucodaily.core.view.View;
import com.diabetes.glucodaily.model.Meal;
import java.util.List;

public interface MealView extends View {

    void showMealItems(List<Meal> meals);

    void showErrorMessage();

    void closeActivity();
}
