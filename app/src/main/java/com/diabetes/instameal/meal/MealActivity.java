package com.diabetes.instameal.meal;

import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.widget.ViewFlipper;
import com.diabetes.instameal.R;
import com.diabetes.instameal.core.ui.MealApplication;
import com.diabetes.instameal.main.MainAdapter;
import com.diabetes.instameal.model.Meal;
import com.squareup.picasso.Picasso;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;
import java.io.File;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MealActivity extends MealApplication implements MealView {

    @BindView(R.id.mealListHorizontal)
    RecyclerView recordedMealList;

    @BindView(R.id.viewFlipper)
    ViewFlipper viewFlipper;

    @BindView(R.id.imageViewCaptured)
    AppCompatImageView imageCaptured;

    @BindView(R.id.spinnerMealType)
    MaterialBetterSpinner spinnerMealType;

    @BindView(R.id.spinnerGlycemia)
    MaterialBetterSpinner spinnerGlycemia;

    @BindView(R.id.spinnerDosage)
    MaterialBetterSpinner spinnerDosage;

    private MealPresenter presenter;

    private ArrayAdapter<CharSequence> dosageAdapter;

    private ArrayAdapter<CharSequence> glycemiaAdapter;

    private ArrayAdapter<CharSequence> mealTypeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);
        bindView();
        recordedMealList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        presenter = new MealPresenter(this);
        if (null == savedInstanceState) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.content_capture, CameraFragment.newInstance())
                    .commit();
        }
        setUpSpinners();
    }

    private void setUpSpinners() {
        dosageAdapter = ArrayAdapter.createFromResource(this, R.array.dosage_insulin_array, android.R.layout.simple_spinner_item);
        glycemiaAdapter = ArrayAdapter.createFromResource(this, R.array.glycemia_array, android.R.layout.simple_spinner_item);
        mealTypeAdapter = ArrayAdapter.createFromResource(this, R.array.meal_type_array, android.R.layout.simple_spinner_item);

        dosageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDosage.setAdapter(dosageAdapter);

        glycemiaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGlycemia.setAdapter(glycemiaAdapter);

        mealTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMealType.setAdapter(mealTypeAdapter);

        spinnerMealType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String mealType = mealTypeAdapter.getItem(position).toString();
                presenter.setMealType(mealTypeAdapter.getItem(position).toString());
                presenter.retrieveHistoricMeal(mealType);
            }
        });
        spinnerGlycemia.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                presenter.setPreGlycemia(glycemiaAdapter.getItem(position).toString());
            }
        });
        spinnerDosage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                presenter.setDosage(dosageAdapter.getItem(position).toString());
            }
        });
    }

    private void bindView() {
        ButterKnife.bind(this);
    }

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode);
    }

    @Override protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_meal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.confirm_meal:
                saveMealCaptured();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveMealCaptured() {
        presenter.saveMeal();
    }

    @Override
    public void closeActivity() {
        finish();
    }

    @Override protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    public void showMealCaptured(File file) {
        viewFlipper.showNext();
        Picasso.with(this).load(file).into(imageCaptured);
    }

    @Override
    public void showMealItems(List<Meal> meals) {
        MainAdapter adapter = new MainAdapter(this,meals,MainAdapter.HORIZONTAL);
        recordedMealList.setAdapter(adapter);
    }

    @Override
    public void showErrorMessage() {
        showToastMessage(getString(R.string.select_item_error));
    }

    public MealPresenter getPresenter() {
        return presenter;
    }

}
