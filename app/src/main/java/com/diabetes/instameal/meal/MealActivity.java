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

public class MealActivity extends MealApplication implements MealView, AdapterView.OnItemSelectedListener {

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
        presenter.retrieveHistoricMeal("LUNCH");
        Log.d("MealActivity", "onCreate");
    }

    private void setUpSpinners() {
        spinnerMealType.setOnItemSelectedListener(this);
        spinnerGlycemia.setOnItemSelectedListener(this);


        final ArrayAdapter<CharSequence> dosageAdapter = ArrayAdapter.createFromResource(this,
                R.array.dosage_insulin_array, android.R.layout.simple_spinner_item);
        dosageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDosage.setAdapter(dosageAdapter);

        ArrayAdapter<CharSequence> glycemiaAdapter = ArrayAdapter.createFromResource(this,
                R.array.glycemia_array, android.R.layout.simple_spinner_item);
        glycemiaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGlycemia.setAdapter(glycemiaAdapter);

        ArrayAdapter<CharSequence> mealTypeAdapter = ArrayAdapter.createFromResource(this,
                R.array.meal_type_array, android.R.layout.simple_spinner_item);
        mealTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMealType.setAdapter(mealTypeAdapter);

        spinnerDosage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "Selected "+dosageAdapter.getItem(position), Toast.LENGTH_SHORT).show();
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
                showNextMealDetails();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showNextMealDetails() {
        presenter.saveMeal(100,5F);
        finish();

    }

    @Override protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override public void showProgress() {

    }

    @Override public void hideProgress() {
//        viewPager.setVisibility(View.VISIBLE);
    }


    public void showMealCaptured(File file) {
        viewFlipper.showNext();
        Picasso.with(this).load(file).into(imageCaptured);
        Log.i("Meal: ","Space: "+file.getTotalSpace());
        Log.i("Meal: ",file.getPath());
        Log.i("Meal: ",file.getName());
    }

    @Override
    public void showMealItems(List<Meal> meals) {
        MainAdapter adapter = new MainAdapter(this,meals,MainAdapter.HORIZONTAL);
        recordedMealList.setAdapter(adapter);
    }

    public MealPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.d("MealActivity", "view "+ view.getId());
        Log.d("MealActivity", "position "+ position);
        Log.d("MealActivity", "id "+ id);
        switch (view.getId()) {
            case R.id.spinnerMealType:
                Toast.makeText(this,"Position: " + position,Toast.LENGTH_SHORT).show();
                break;

            case R.id.spinnerGlycemia:
                Toast.makeText(this,"Position: " + position,Toast.LENGTH_SHORT).show();
                break;

            case R.id.spinnerDosage:
                Toast.makeText(this,"Position: " + position,Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
