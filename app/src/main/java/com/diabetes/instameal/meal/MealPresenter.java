package com.diabetes.instameal.meal;

import android.content.Context;
import com.diabetes.instameal.Helper.DaoManager;
import com.diabetes.instameal.core.presenter.Presenter;
import com.diabetes.instameal.model.Meal;
import java.io.File;
import java.util.Date;
import java.util.List;

public class MealPresenter extends Presenter<MealView> implements OnCapturePerformed {

    private int mealDetailsStep = 0;

    private File file;

    public MealPresenter(MealView view, Context context) {
        super(view,context);
    }

    @Override
    protected void onResume() {
    }

    @Override
    protected void onDestroy() {
        this.view = null;
    }

    public List<Meal> retrieveHistoricMeal(String mealType) {
        return mealService.retrieveMealListType(mealType);
    }

    public void saveMeal(int preGlycemia, float dosageInsulin) {
        mealService.saveMeal(preGlycemia,dosageInsulin,file.getName());
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
        return mealDetailsStep == 1;
    }

    public Boolean isMealProcessFinished() {
        return mealDetailsStep == 2;
    }

}
