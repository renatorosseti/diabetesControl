package com.diabetes.instameal.meal;

import com.diabetes.instameal.core.view.View;
import com.diabetes.instameal.model.Meal;
import java.io.File;
import java.util.List;

public interface MealView extends View {

    void showProgress();

    void hideProgress();

    void showMealCaptured(File file);

    void showMealItems(List<Meal> meals);
}
