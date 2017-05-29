package com.diabetes.glucodaily.model;

import com.diabetes.glucodaily.Helper.DataHelper;
import com.diabetes.glucodaily.R;
import com.diabetes.glucodaily.core.ui.BaseActivity;
import java.util.List;

import static com.diabetes.glucodaily.Helper.DataHelper.*;

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
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(BaseActivity.getContext(), "meal-db", null);
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

    public synchronized List<Meal> retrieveMealListType(int mealTypeIndex) {
        String mealType[] = BaseActivity.getContext().getResources().getStringArray(R.array.meal_type_array);
        List<Meal> meals = null;
        if(mealTypeIndex == BREAKFAST || mealTypeIndex == MORNING_SNACK) {
            meals = daoSession.getMealDao().queryBuilder().whereOr(MealDao.Properties.Type.eq(getMealType(mealType[BREAKFAST])),MealDao.Properties.Type.eq(getMealType(mealType[MORNING_SNACK]))).orderDesc(MealDao.Properties.Date).list();
        } else if(mealTypeIndex == LUNCH || mealTypeIndex == DINNER || mealTypeIndex == NIGHT_SNACK) {
            meals = daoSession.getMealDao().queryBuilder().where(MealDao.Properties.Type.eq(getMealType(mealType[mealTypeIndex]))).orderDesc(MealDao.Properties.Date).list();
        } else if(mealTypeIndex == AFTERNOON_COFFEE) {
            meals = daoSession.getMealDao().queryBuilder().where(MealDao.Properties.Type.eq(getMealType(mealType[AFTERNOON_COFFEE]))).orderDesc(MealDao.Properties.Date).list();
        }
        return meals.size() > 0 ? meals : meals;
    }

    private String getMealType(String mealType) {
        return DataHelper.removeBreakLine(mealType);
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
