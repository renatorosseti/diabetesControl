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
import android.widget.EditText;

import com.diabetes.glucodaily.Helper.DataHelper;
import com.diabetes.glucodaily.R;
import com.diabetes.glucodaily.meal.OnCapturePerformed;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MealDialogFragment extends DialogFragment {

    @BindView(R.id.mealView)
    AppCompatImageView mealPhoto;

    @BindView(R.id.spinnerPosGlycemia)
    EditText editTextPosGlycemia;

    @BindView(R.id.cancelButton)
    Button cancelButton;

    @BindView(R.id.saveButton)
    Button saveButton;

    private OnCapturePerformed listener;

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

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String posGlycemia = editTextPosGlycemia.getText().toString();
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
