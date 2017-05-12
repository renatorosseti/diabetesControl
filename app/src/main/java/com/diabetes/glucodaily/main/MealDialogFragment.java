package com.diabetes.glucodaily.main;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.diabetes.glucodaily.R;
import com.diabetes.glucodaily.meal.OnCapturePerformed;
import com.squareup.picasso.Picasso;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MealDialogFragment extends DialogFragment {

    @BindView(R.id.mealView)
    AppCompatImageView mealPhoto;

    @BindView(R.id.spinnerPosGlycemia)
    MaterialBetterSpinner spinnerPosGlycemia;

    @BindView(R.id.cancelButton)
    Button cancelButton;

    @BindView(R.id.saveButton)
    Button saveButton;

    private String posGlycemia = "";

    private OnCapturePerformed listener;

    private ArrayAdapter<CharSequence> glycemiaAdapter;

    public static MealDialogFragment newInstance(String path) {
        MealDialogFragment fragment = new MealDialogFragment();
        Bundle args = new Bundle();
        args.putString("path", path);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (OnCapturePerformed) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meal, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        final String imagePath = getArguments().getString("path").toString();
        glycemiaAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.glycemia_array, android.R.layout.simple_spinner_item);
        glycemiaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPosGlycemia.setAdapter(glycemiaAdapter);
        spinnerPosGlycemia.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                posGlycemia = glycemiaAdapter.getItem(position).toString();
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!posGlycemia.isEmpty()){
                    listener.loadPosGlycemiaMeal(imagePath,posGlycemia);
                }
                dismiss();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        Picasso.with(getActivity())
                .load("file://" + imagePath)
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
