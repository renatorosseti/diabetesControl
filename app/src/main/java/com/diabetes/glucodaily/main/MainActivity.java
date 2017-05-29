package com.diabetes.glucodaily.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import com.diabetes.glucodaily.R;
import com.diabetes.glucodaily.core.ui.BaseActivity;
import com.diabetes.glucodaily.meal.OnCapturePerformed;
import com.diabetes.glucodaily.model.Meal;
import java.io.File;
import java.util.List;

public class MainActivity extends BaseActivity implements MainView, OnCapturePerformed {

    private RecyclerView mRecyclerView;

    private ProgressBar progressBar;

    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        mRecyclerView = (RecyclerView) findViewById(R.id.listRecycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressBar = (ProgressBar) findViewById(R.id.progress);
        presenter = new MainPresenter(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
        presenter.onResume();

    }

    @Override protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.INVISIBLE);
    }

    @Override public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override public void setItems(List<Meal> items) {
        mRecyclerView.setAdapter(new MainAdapter(this, items, BaseActivity.VERTICAL));
    }

    @Override
    public void loadCapturedFile(File file) {

    }

    @Override
    public void loadPosGlycemiaMeal(String imagePath, String posGlycemia) {
        presenter.updateMeal(imagePath, posGlycemia);
    }
}