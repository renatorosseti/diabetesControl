package com.diabetes.instameal.meal;

import android.content.Context;

import com.diabetes.instameal.model.DaoManager;
import com.diabetes.instameal.model.Meal;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.Date;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

/**
 * Created by Renato Rosseti on 27/04/17.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({DaoManager.class})
public class MealPresenterTest {

    @Mock
    private MealView view;

    @Mock
    private DaoManager mealDaoUtils;

    @Mock
    private Context context;

    private MealPresenter mealPresenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mealPresenter = new MealPresenter(view, context);

    }

    @After
    public void tearDown() {
        verifyNoMoreInteractions(view, mealDaoUtils);
    }

    @Test
    public void onResume() {
        mockStatic(DaoManager.class);
        PowerMockito.when(DaoManager.getInstance()).thenReturn(mealDaoUtils);

        mealPresenter.onResume();

    }

    @Test
    public void retrieveHistoricMeal() {

        String mealType = "LUNCH";
        ArrayList<Meal> meals = new ArrayList<>();
        meals.add(new Meal((long) 0, 100, 150, 5.0F, new Date(), 0, "description","idImage",mealType));

        when(mealDaoUtils.retrieveMealListType(mealType)).thenReturn(meals);

        mealPresenter.retrieveHistoricMeal(mealType);

        verify(mealDaoUtils).retrieveMealListType(mealType);
    }


    @Test
    public void onDestroy() {

        mealPresenter.onDestroy();

        verify(mealDaoUtils).destroy();
    }
}
