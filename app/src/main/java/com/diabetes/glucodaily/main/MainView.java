package com.diabetes.glucodaily.main;

import com.diabetes.glucodaily.core.view.View;
import com.diabetes.glucodaily.model.Meal;
import java.util.List;

public interface MainView extends View {

    void setItems(List<Meal> items);

    void notifyDataSetChanged();

}
