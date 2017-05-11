package com.diabetes.instameal.main;

import com.diabetes.instameal.core.presenter.Presenter;
import com.diabetes.instameal.model.Meal;
import com.diabetes.instameal.service.OnMealServicePerformed;
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
    }
}
