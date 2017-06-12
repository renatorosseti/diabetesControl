package com.diabetes.glucodaily.meal;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ViewFlipper;
import com.diabetes.glucodaily.Helper.DataHelper;
import com.diabetes.glucodaily.R;
import com.diabetes.glucodaily.core.ui.BaseActivity;
import com.diabetes.glucodaily.main.MainAdapter;
import com.diabetes.glucodaily.model.Meal;
import com.squareup.picasso.Picasso;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;
import java.io.File;
import java.util.Date;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MealActivity extends BaseActivity implements OnCapturePerformed, MealView {

    @BindView(R.id.mealListHorizontal)
    RecyclerView recordedMealList;

    @BindView(R.id.viewFlipper)
    ViewFlipper viewFlipper;

    @BindView(R.id.imageViewCaptured)
    AppCompatImageView imageCaptured;

    @BindView(R.id.spinnerMealType)
    TextView spinnerMealType;

    @BindView(R.id.spinnerGlycemia)
    TextView spinnerGlycemia;

    @BindView(R.id.spinnerDosage)
    TextView spinnerDosage;

    @BindView(R.id.mealCaptured)
    View mViewCaptured;

    int mealTypeIndex;

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

    }

    private void setUpSpinners() {
        dosageAdapter = ArrayAdapter.createFromResource(this, R.array.dosage_insulin_array, R.layout.spinner_item);
        glycemiaAdapter = ArrayAdapter.createFromResource(this, R.array.glycemia_array, R.layout.spinner_item);
        mealTypeAdapter = ArrayAdapter.createFromResource(this, R.array.meal_type_array, R.layout.spinner_item);
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
        setUpSpinners();
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
                presenter.saveMeal();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void closeActivity() {
        finish();
    }

    @Override protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void loadCapturedFile(File file) {
        presenter.setFile(file);
        viewFlipper.showNext();
        Picasso.with(this).load(file).into(imageCaptured);
        mealTypeIndex = DataHelper.getMealTypeRecommendation(new Date());
        String mealType = mealTypeAdapter.getItem(mealTypeIndex).toString();
        spinnerMealType.setText(mealType);
        presenter.setMealType(DataHelper.removeBreakLine(mealType));
        presenter.retrieveHistoricMeal(mealTypeIndex);
    }

    @Override
    public void loadPosGlycemiaMeal(String imagePath, String posGlycemia) {

    }

    @OnClick(R.id.mealGlycemia)
    public void setMealPreGlycemia() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.input_meal, null);
        final EditText glycemiaBefore = (EditText) view.findViewById(R.id.valueInput);
        glycemiaBefore.setHint(R.string.glycemia_before);
        builder.setView(view);
        builder.setPositiveButton(
                "Confirmar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String preGlycemya = glycemiaBefore.getText().toString();
                        spinnerGlycemia.setText(preGlycemya);
                        presenter.setPreGlycemia(preGlycemya);
                    }
                });
        builder.show();
    }

    @OnClick(R.id.mealDosage)
    public void setMealDosage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.input_meal, null);
        final EditText dosage = (EditText) view.findViewById(R.id.valueInput);
        dosage.setHint(R.string.glycemia_before);
        builder.setView(view);
        builder.setPositiveButton(
                "Confirmar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String dosageValue = dosage.getText().toString();
                        spinnerDosage.setText(dosageValue);
                        presenter.setDosage(dosageValue);
                    }
                });
        builder.show();
    }

    @OnClick(R.id.mealType)
    public void setMealType() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.input_meal_type, null);
        final SeekBar seekBar = (SeekBar) view.findViewById(R.id.seekBar);
        final TextView mealTypeValue = (TextView) view.findViewById(R.id.valueMealType);
        mealTypeIndex = DataHelper.getMealTypeRecommendation(new Date());
        mealTypeValue.setText(DataHelper.removeBreakLine(mealTypeAdapter.getItem(mealTypeIndex).toString()));

        seekBar.setProgress(mealTypeIndex);
        builder.setView(view);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int position, boolean b) {
                mealTypeValue.setText(DataHelper.removeBreakLine(mealTypeAdapter.getItem(position).toString()));
                mealTypeIndex = position;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        builder.setPositiveButton(
                "Confirmar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String mealType = mealTypeAdapter.getItem(seekBar.getProgress()).toString();
                        spinnerMealType.setText(mealType);
                        presenter.setMealType(mealType);
                        presenter.retrieveHistoricMeal(mealTypeIndex);
                    }
                });
        builder.show();
    }

    @Override
    public void showMealItems(List<Meal> meals) {
        MainAdapter adapter = new MainAdapter(this,meals, HORIZONTAL);
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
