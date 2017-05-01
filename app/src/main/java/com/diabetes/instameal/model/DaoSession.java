package com.diabetes.instameal.model;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.diabetes.instameal.model.Meal;

import com.diabetes.instameal.model.MealDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig mealDaoConfig;

    private final MealDao mealDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        mealDaoConfig = daoConfigMap.get(MealDao.class).clone();
        mealDaoConfig.initIdentityScope(type);

        mealDao = new MealDao(mealDaoConfig, this);

        registerDao(Meal.class, mealDao);
    }
    
    public void clear() {
        mealDaoConfig.getIdentityScope().clear();
    }

    public MealDao getMealDao() {
        return mealDao;
    }

}
