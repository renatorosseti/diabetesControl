package com.diabetes.instameal.meal;

import com.diabetes.instameal.Helper.CapturedHelper;
import com.diabetes.instameal.core.presenter.Presenter;
import com.diabetes.instameal.model.Meal;
import com.diabetes.instameal.service.OnMealServicePerformed;
import java.io.File;
import java.util.List;

public class MealPresenter extends Presenter<MealView> implements OnCapturePerformed, OnMealServicePerformed {

    private int mealDetailsStep = 0;

    private File file;

    public MealPresenter(MealView view) {
        super(view);
    }

    @Override
    protected void onResume() {
    }

    @Override
    protected void onDestroy() {
        this.view = null;
    }

    public void retrieveHistoricMeal(String mealType) {
        mealService.retrieveHistoricMeal(this,mealType);
    }

    public void saveMeal(int preGlycemia, float dosageInsulin) {
        mealService.saveMeal(preGlycemia,dosageInsulin, CapturedHelper.getPath(file.getName()));
    }

    @Override
    public void loadCapturedFile(File file) {
        this.file = file;
        view.showMealCaptured(file);
    }

    public void incrementMealDetailsStep() {
        this.mealDetailsStep++;
    }

    public Boolean areMealDetailsCompleted() {
        return mealDetailsStep == 0;
    }

    public Boolean isMealProcessFinished() {
        return mealDetailsStep == 1;
    }

    @Override
    public void loadMeals(List<Meal> items) {
        view.showMealItems(items);
    }
}
