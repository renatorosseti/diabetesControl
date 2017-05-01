package com.diabetes.instameal.meal;

import com.diabetes.instameal.core.view.View;

import java.io.File;
import java.util.List;

public interface MealView extends View {

    void showProgress();

    void hideProgress();

    void setItems(List<String> items);

    void showDialog(String message);

    void showMealCaptured(File file);
}
