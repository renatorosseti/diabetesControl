package com.diabetes.instameal.main;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.diabetes.instameal.R;
import com.diabetes.instameal.core.ui.MealApplication;
import com.squareup.picasso.Picasso;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.diabetes.instameal.core.ui.MealApplication.HORIZONTAL;
import static com.diabetes.instameal.core.ui.MealApplication.VERTICAL;
import static com.diabetes.instameal.core.ui.MealApplication.getDisplayParam;

public class MealDialog extends DialogFragment {

    @BindView(R.id.mealView)
    AppCompatImageView mealPhoto;
//
//    @BindView(R.id.preGlycemiaText)
//    TextView preGlycemiaText;
//
//    @BindView(R.id.posGlycemiaText)
//    TextView posGlycemiaText;
//
//    @BindView(R.id.mealType)
//    TextView mealType;
//
//    @BindView(R.id.dosageText)
//    TextView dosage;

    @BindView(R.id.spinnerPosGlycemia)
    MaterialBetterSpinner spinnerPosGlycemia;

    private ArrayAdapter<CharSequence> glycemiaAdapter;


    public static MealDialog newInstance(String path) {
        MealDialog fragment = new MealDialog();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putString("path", path);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.meal_item_editable, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        glycemiaAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.glycemia_array, android.R.layout.simple_spinner_item);
        glycemiaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPosGlycemia.setAdapter(glycemiaAdapter);
        Picasso.with(getActivity())
                .load("file://" + getArguments().getString("path"))
                .fit()
                .into(mealPhoto);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }
}
