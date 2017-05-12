package com.diabetes.glucodaily.meal;

import com.diabetes.glucodaily.Helper.DataHelper;
import com.diabetes.glucodaily.core.presenter.Presenter;
import com.diabetes.glucodaily.model.Meal;
import com.diabetes.glucodaily.service.OnMealServicePerformed;
import java.io.File;
import java.util.List;

public class MealPresenter extends Presenter<MealView> implements OnMealServicePerformed {

    private File mFile;

    private String preGlycemia;

    private String posGlycemia;

    private String dosageInsulin;

    private String mealType;

    public MealPresenter(MealView view) {
        super(view);
    }

    @Override
    protected void onResume() {
        mealService.retrieveAllMeals(this);
    }

    @Override
    protected void onDestroy() {
        this.view = null;
    }

    public void retrieveHistoricMeal(String mealType) {
        mealService.retrieveHistoricMeal(this,mealType);
    }

    public void saveMeal() {
        if(preGlycemia != null && dosageInsulin != null && mealType != null && mFile != null) {
            mealService.saveMeal(preGlycemia, Float.parseFloat(dosageInsulin), DataHelper.getPath(mFile.getName()), mealType);
            view.closeActivity();
        } else {
            view.showErrorMessage();
        }
    }

    @Override
    public void loadMeals(List<Meal> items) {
        view.showMealItems(items);
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public void setDosage(String dosage) {
        this.dosageInsulin = dosage;
    }

    public void setPosGlycemia(String posGlycemia) {
        this.posGlycemia = posGlycemia;
    }

    public void setPreGlycemia(String preGlycemia) {
        this.preGlycemia = preGlycemia;
    }

    public void setFile(File file) {
        this.mFile = file;
    }
}
