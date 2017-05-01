package com.diabetes.instameal.main;

import com.diabetes.instameal.model.Meal;

import java.util.List;

public interface MainView {

    void showProgress();

    void hideProgress();

    void setItems(List<Meal> items);

    void showMessage(String message);
}
