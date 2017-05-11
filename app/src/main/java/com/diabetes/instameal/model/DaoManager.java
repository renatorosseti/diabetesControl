package com.diabetes.instameal.model;

import com.diabetes.instameal.core.ui.MealApplication;
import java.util.List;

public class DaoManager {

    private DaoSession daoSession;

    private static DaoManager instance;

    public static DaoManager getInstance() {
        if(instance == null) {
            instance = new DaoManager();
        }
        return instance;
    }

    public void init() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(MealApplication.getContext(), "meal-db", null);
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());
        daoSession = daoMaster.newSession();

    }

    public synchronized void updateMeal(String imagePath, String posGlycemia) {
        Meal meal = daoSession.getMealDao().queryBuilder().where(MealDao.Properties.PathImage.eq(imagePath)).unique();
        if(meal != null) {
            meal.setPosGlycemia(posGlycemia);
            daoSession.getMealDao().update(meal);
        }
    }

    public synchronized List<Meal> retrieveMealListType(String mealType) {
        return daoSession.getMealDao().queryBuilder().where(MealDao.Properties.Type.eq(mealType)).orderDesc(MealDao.Properties.PosGlycemia).orderDesc(MealDao.Properties.Date).list();
    }

    public synchronized List<Meal> retrieveAllMeals() {
        return daoSession.getMealDao().queryBuilder().orderDesc(MealDao.Properties.Date).list();
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
