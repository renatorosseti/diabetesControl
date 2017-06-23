package com.diabetes.glucodaily.main;

import com.diabetes.glucodaily.core.presenter.Presenter;
import com.diabetes.glucodaily.model.Meal;
import com.diabetes.glucodaily.model.MealHolder;
import com.diabetes.glucodaily.service.OnMealServicePerformed;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter extends Presenter<MainView> implements OnMealServicePerformed {

    public MainPresenter(MainView view) {
        super(view);
    }

    @Override
    protected void onResume() {
        mealService.retrieveAllMeals(this);
    }

    @Override
    protected void onDestroy() {
        mealService.destroy();
        view = null;
    }

    @Override
    public void loadMeals(List<Meal> items) {
        List<MealHolder> mealHolderList = new ArrayList<>();
        for (Meal meal : items) {
            mealHolderList.add(new MealHolder(meal));
        }
        view.setItems(mealHolderList);
    }

    public void updateMeal(String imagePath,Integer posGlycemia) {
        mealService.updateMeal(imagePath,posGlycemia);
        mealService.retrieveAllMeals(this);
    }

    public void removeMeal(List<Meal> meals) {
        for (Meal meal : meals) {
            mealService.removeMeal(meal);
        }
        mealService.retrieveAllMeals(this);
    }
}
