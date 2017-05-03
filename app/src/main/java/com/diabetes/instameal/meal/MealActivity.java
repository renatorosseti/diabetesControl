package com.diabetes.instameal.meal;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;
import com.diabetes.instameal.R;
import com.diabetes.instameal.model.Meal;
import com.squareup.picasso.Picasso;
import java.io.File;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MealActivity extends AppCompatActivity implements MealView {

    @BindView(R.id.mealViewPager)
    ViewPager viewPager;

    @BindView(R.id.viewFlipper)
    ViewFlipper viewFlipper;

    @BindView(R.id.flipperMeal)
    ViewFlipper viewFlipperMeal;

    @BindView(R.id.imageViewCaptured)
    ImageView imageCaptured;

    @BindView(R.id.listViewPreGlycemia)
    ListView listViewPreGlycemia;

    @BindView(R.id.listViewInsulinDosage)
    ListView listViewInsulinDosage;


    private MealPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);
        bindView();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        presenter = new MealPresenter(this,this);
        if (null == savedInstanceState) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.content_capture, CameraFragment.newInstance())
                    .commit();
        }
        showNextMealDetails();
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
        if (presenter.isMealProcessFinished()) {
            presenter.saveMeal(100,5F);
            finish();
        } else if (presenter.areMealDetailsCompleted()) {
            viewFlipperMeal.showNext();
            presenter.retrieveHistoricMeal("LUNCH");
            Log.i("MealActivity","Is shown: "+viewPager.isShown());

        } else {
            String[] exercises = getResources().getStringArray(R.array.glycemias_array);
            ArrayAdapter<String> glycemiasAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,exercises);
            listViewPreGlycemia.setAdapter(glycemiasAdapter);

            String[] exercises2 = getResources().getStringArray(R.array.dosage_insulin_array);
            ArrayAdapter<String> adapterInsulinDosage=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,exercises2);
            listViewInsulinDosage.setAdapter(adapterInsulinDosage);
        }
        presenter.incrementMealDetailsStep();
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
        Log.i("Path: ",file.getPath());
        Log.i("Name: ",file.getName());
    }

    @Override
    public void showMealItems(List<Meal> meals) {
        MealPagerAdapter adapter = new MealPagerAdapter(this, meals);
        viewPager.setAdapter(adapter);
    }

    public MealPresenter getPresenter() {
        return presenter;
    }
}
