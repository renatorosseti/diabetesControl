package com.diabetes.glucodaily.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ViewFlipper;

import com.diabetes.glucodaily.R;
import com.diabetes.glucodaily.core.ui.BaseActivity;
import com.diabetes.glucodaily.meal.OnCapturePerformed;
import com.diabetes.glucodaily.model.Meal;
import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainView, OnCapturePerformed {

    @BindView(R.id.listRecycler)
    RecyclerView mRecyclerView;

    @BindView(R.id.viewFlipperMeal)
    ViewFlipper viewFlipper;

    private MainPresenter mPresenter;

    private MainAdapter mMainAdapter;

    private int NO_MEAL_AVAILABLE_VIEW = 1;

    private int HAVE_MEALS_AVAILABLE_VIEW = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        ButterKnife.bind(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mPresenter = new MainPresenter(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setIcon(R.mipmap.ic_diabetes);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), com.diabetes.glucodaily.meal.MealActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override protected void onResume() {
        super.onResume();
        showProgress();
        mPresenter.onResume();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        mGarbageItem = menu.getItem(0);
        setVisibleGarbageItem(false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_action:
                setVisibleGarbageItem(false);
                showProgress();
                mPresenter.removeMeal(mMainAdapter.getRemoveMeals());
                break;
            case android.R.id.home:
                setVisibleGarbageItem(false);
                mMainAdapter.setMealsChecked();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override protected void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }

    @Override public void setItems(List<Meal> items) {
        hideProgress();
        viewFlipper.setDisplayedChild(items.isEmpty() ? NO_MEAL_AVAILABLE_VIEW : HAVE_MEALS_AVAILABLE_VIEW);
        mMainAdapter = new MainAdapter(this, items, BaseActivity.VERTICAL);
        mRecyclerView.setAdapter(mMainAdapter);
    }

    @Override
    public void loadCapturedFile(File file) {

    }

    @Override
    public void notifyDataSetChanged() {
//        mMainAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadPosGlycemiaMeal(String imagePath, String posGlycemia) {
        mPresenter.updateMeal(imagePath, Integer.parseInt(posGlycemia));
    }

}