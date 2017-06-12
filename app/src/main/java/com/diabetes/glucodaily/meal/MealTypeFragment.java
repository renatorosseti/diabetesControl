package com.diabetes.glucodaily.meal;

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
import android.widget.SeekBar;
import android.widget.TextView;

import com.diabetes.glucodaily.Helper.DataHelper;
import com.diabetes.glucodaily.R;
import com.squareup.picasso.Picasso;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MealTypeFragment extends DialogFragment {

    @BindView(R.id.seekBar)
    SeekBar seekBar;

    @BindView(R.id.valueMealType)
    TextView valueTextView;

    @BindView(R.id.cancelButton)
    Button cancelButton;

    @BindView(R.id.saveButton)
    Button saveButton;
    private String posGlycemia = "";

    private OnCapturePerformed listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (OnCapturePerformed) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.input_meal_type, container, false);
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
                if(!posGlycemia.isEmpty()){
                    listener.loadPosGlycemiaMeal(imagePath,posGlycemia);
                }
                dismiss();
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

}
