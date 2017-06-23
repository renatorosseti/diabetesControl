package com.diabetes.glucodaily.main;

import com.diabetes.glucodaily.core.view.View;
import com.diabetes.glucodaily.model.Meal;
import com.diabetes.glucodaily.model.MealHolder;

import java.util.List;

public interface MainView extends View {

    void setItems(List<MealHolder> items);

    void notifyDataSetChanged();

}
