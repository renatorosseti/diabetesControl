package com.diabetes.instameal.main;

import android.app.Activity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.diabetes.instameal.R;
import com.diabetes.instameal.core.ui.MealApplication;
import com.diabetes.instameal.model.Meal;
import com.squareup.picasso.Picasso;
import java.util.List;
import static com.diabetes.instameal.core.ui.MealApplication.HORIZONTAL;
import static com.diabetes.instameal.core.ui.MealApplication.VERTICAL;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private Activity mContext;

    private List<Meal> meals;

    private Boolean mOrientation;

    public MainAdapter(Activity context, List<Meal> meals, Boolean orientation) {
        this.mContext = context;
        this.meals = meals;
        this.mOrientation = orientation;
    }

    private Boolean isOrientationHorizontal() {
        return mOrientation;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.meal_item, null);
        if(isOrientationHorizontal()) {
            ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(MealApplication.getDisplayParam(HORIZONTAL), ViewGroup.LayoutParams.WRAP_CONTENT);
            view.setLayoutParams(layoutParams);
        }
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Meal meal = meals.get(position);
        holder.preGlycemiaText.setText(meal.getPreGlycemia().toString());
        holder.posGlycemiaText.setText(meal.getPosGlycemia().toString());
        holder.mealType.setText(meal.getType());
        holder.dosage.setText(meal.getDosageInsulin().toString());
        Picasso.with(mContext)
                .load("file://" + meal.getPathImage())
                .fit()
                .into(holder.mealPhoto);
        holder.mealPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MealDialog newFragment = MealDialog.newInstance(meal.getPathImage());
                newFragment.show(mContext.getFragmentManager(),"");
            }
        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatImageView mealPhoto;
        TextView preGlycemiaText;
        TextView posGlycemiaText;
        TextView mealType;
        TextView dosage;

        ViewHolder(View v) {
            super(v);
            this.mealPhoto = (AppCompatImageView) v.findViewById(R.id.mealView);
            this.preGlycemiaText = (TextView) v.findViewById(R.id.preGlycemiaText);
            this.posGlycemiaText = (TextView) v.findViewById(R.id.posGlycemiaText);
            this.mealType = (TextView) v.findViewById(R.id.mealType);
            this.dosage = (TextView) v.findViewById(R.id.dosageText);
            this.mealPhoto.setMinimumHeight(MealApplication.getDisplayParam(VERTICAL));
            this.mealPhoto.setMinimumWidth(MealApplication.getDisplayParam(HORIZONTAL));
        }
    }
}
