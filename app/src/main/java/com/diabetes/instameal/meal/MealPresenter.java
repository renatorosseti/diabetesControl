package com.diabetes.instameal.meal;

import android.content.Context;
import com.diabetes.instameal.Helper.DaoManager;
import com.diabetes.instameal.core.presenter.Presenter;
import com.diabetes.instameal.model.Meal;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * Created by Renato Rosseti on 27/04/17.
 */

public class MealPresenter extends Presenter<MealView> implements OnCapturePerformed {


    private DaoManager mealDaoHelper;

    private int mealDetailsStep = 0;

    private File file;

    private Context mContext;

    public MealPresenter(MealView view) {
        super(view);
    }

    public void onResume(Context context) {
        this.mContext = context;
        mealDaoHelper = DaoManager.getInstance();
        mealDaoHelper.init(context);
    }

    public void onDestroy() {
        mealDaoHelper.destroy();
        this.view = null;
    }

    public List<Meal> retrieveHistoricMeal(String mealType) {
        return mealDaoHelper.retrieveMealList(mealType);
    }

    public void saveMeal(int preGlycemia, float dosageInsulin) {
        Meal meal = new Meal(null,preGlycemia,0,dosageInsulin,new Date(), 0, "", file.getName(),"LUNCH");

        mealDaoHelper.addNewMeal(meal);
    }


    public void setMealDaoHelper(DaoManager mealDaoHelper) {
        this.mealDaoHelper = mealDaoHelper;
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

    @Override
    public void loadCapturedFile(File file) {
        this.file = file;
        view.showMealCaptured(file);
    }
}
