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
//        mealService.retrieveAllMeals(this);
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
                mealType != null &&
                !mealType.isEmpty() &&
                mFile != null) {
            mealService.saveMeal(Integer.parseInt(preGlycemia), Float.parseFloat(dosageInsulin), DataHelper.getPath(mFile.getName()), mealType);
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
