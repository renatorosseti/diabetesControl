package com.diabetes.instameal.meal;

import com.diabetes.instameal.core.view.View;
import com.diabetes.instameal.model.Meal;
import java.util.List;

public interface MealView extends View {

    void showMealItems(List<Meal> meals);

    void showErrorMessage();

    void closeActivity();
}
