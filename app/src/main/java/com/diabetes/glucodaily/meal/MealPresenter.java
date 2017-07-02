package com.diabetes.glucodaily.meal;

import com.diabetes.glucodaily.Helper.DataHelper;
import com.diabetes.glucodaily.core.presenter.Presenter;
import com.diabetes.glucodaily.model.Meal;
import com.diabetes.glucodaily.model.MealHolder;
import com.diabetes.glucodaily.service.OnMealServicePerformed;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MealPresenter extends Presenter<MealView> implements OnMealServicePerformed {

    private String mFileName;

    private String preGlycemia;

    private String posGlycemia;

    private String dosageInsulin;

    private Integer mealTypeIndex;

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

    public void retrieveHistoricMeal(int mealTypeIndex) {
        mealService.retrieveHistoricMeal(this,mealTypeIndex);
    }

    public void saveMeal() {
        if(preGlycemia != null &&
                !preGlycemia.isEmpty() &&
                dosageInsulin != null &&
                !dosageInsulin.isEmpty() &&
                mFileName != null) {
            mealService.saveMeal(Integer.parseInt(preGlycemia), Float.parseFloat(dosageInsulin), mFileName, mealTypeIndex);
            view.navigateToMainFeed();
        } else {
            view.showErrorMessage();
        }
    }

    @Override
    public void loadMeals(List<Meal> items) {
        List<MealHolder> mealHolderList = new ArrayList<>();
        for (Meal meal : items) {
            mealHolderList.add(new MealHolder(meal));
        }
        view.showMealItems(mealHolderList);
    }

    public void setMealTypeIndex(Integer mealTypeIndex) {
        this.mealTypeIndex = mealTypeIndex;
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

    public void setFile(String fileName) {
        this.mFileName = fileName;
    }
}
