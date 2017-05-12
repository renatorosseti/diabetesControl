package com.diabetes.glucodaily.main;

import com.diabetes.glucodaily.core.presenter.Presenter;
import com.diabetes.glucodaily.model.Meal;
import com.diabetes.glucodaily.service.OnMealServicePerformed;
import java.util.List;

public class MainPresenter extends Presenter<MainView> implements OnMealServicePerformed {

    public MainPresenter(MainView view) {
        super(view);
    }

    @Override
    protected void onResume() {
        view.showProgress();
        mealService.retrieveAllMeals(this);
    }

    @Override
    protected void onDestroy() {
        mealService.destroy();
        view = null;
    }

    @Override
    public void loadMeals(List<Meal> items) {
        view.hideProgress();
        view.setItems(items);
    }

    public void updateMeal(String imagePath,String posGlycemia) {

        mealService.updateMeal(imagePath,posGlycemia);
        mealService.retrieveAllMeals(this);
    }
}
