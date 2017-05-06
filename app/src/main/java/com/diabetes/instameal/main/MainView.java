package com.diabetes.instameal.main;

import com.diabetes.instameal.core.view.View;
import com.diabetes.instameal.model.Meal;
import java.util.List;

public interface MainView extends View {

    void showProgress();

    void hideProgress();

    void setItems(List<Meal> items);
}
