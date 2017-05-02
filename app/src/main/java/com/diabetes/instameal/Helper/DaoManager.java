package com.diabetes.instameal.Helper;

import android.content.Context;
import com.diabetes.instameal.model.DaoMaster;
import com.diabetes.instameal.model.DaoSession;
import com.diabetes.instameal.model.Meal;
import com.diabetes.instameal.model.MealDao;
import java.util.List;

/**
 * Created by Renato Rosseti on 27/04/17.
 */

public class DaoManager {

    private DaoSession daoSession;

    private DaoMaster daoMaster;

    private static DaoManager instance;

    public static DaoManager getInstance() {
        if(instance == null) {
            instance = new DaoManager();
        }
        return instance;
    }

    public void init(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "meal-db", null);
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());
        daoSession = daoMaster.newSession();
    }

    public synchronized List<Meal> retrieveMealListType(String mealType) {
        return daoSession.getMealDao().queryBuilder().where(MealDao.Properties.Type.eq(mealType)).orderDesc(MealDao.Properties.PosGlycemia).list();
    }

    public synchronized List<Meal> retrieveAllMeals() {
        return daoSession.getMealDao().queryBuilder().orderAsc(MealDao.Properties.Date).list();
    }

    public void addNewMeal(Meal meal) {
        daoSession.getMealDao().insert(meal);
    }

    public void removeMeal(Meal meal) {
        daoSession.getMealDao().delete(meal);
    }

    public void destroy() {
        daoSession.clear();
    }

}
