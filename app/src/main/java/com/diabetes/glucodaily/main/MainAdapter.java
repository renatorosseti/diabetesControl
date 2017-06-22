package com.diabetes.glucodaily.main;

import android.app.Activity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.diabetes.glucodaily.R;
import com.diabetes.glucodaily.core.ui.BaseActivity;
import com.diabetes.glucodaily.model.Meal;
import com.squareup.picasso.Picasso;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

import static com.diabetes.glucodaily.core.ui.BaseActivity.HORIZONTAL;
import static com.diabetes.glucodaily.core.ui.BaseActivity.VERTICAL;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private Activity mContext;

    private List<Meal> meals;

    private Boolean mOrientation;

    private MainPresenter mPresenter;

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
        View view = null;
        if(isOrientationHorizontal()) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.meal_item_h, null);
            ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(BaseActivity.getDisplayParam(HORIZONTAL), ViewGroup.LayoutParams.WRAP_CONTENT);
            view.setLayoutParams(layoutParams);
        } else {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.meal_item, null);
        }
        ViewHolder viewHolder = new ViewHolder(view, position);
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
//
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.mealView) AppCompatImageView mealPhoto;
        @BindView(R.id.preGlycemiaText) TextView preGlycemiaText;
        @BindView(R.id.posGlycemiaText) TextView posGlycemiaText;
        @BindView(R.id.mealType) TextView mealType;
        @BindView(R.id.dosageText) TextView dosage;

        ViewHolder(View v, int position) {
            super(v);
            ButterKnife.bind(this, v);
            this.mealPhoto.setMinimumHeight(BaseActivity.getDisplayParam(VERTICAL));
            this.mealPhoto.setMinimumWidth(BaseActivity.getDisplayParam(HORIZONTAL));
        }

        @OnClick(R.id.item_meal)
        public void onClick(View view) {
            MealDialogFragment dialogFragment = MealDialogFragment.newInstance(meals.get(getAdapterPosition()).getPathImage());
            dialogFragment.show(mContext.getFragmentManager(),"");
        }

        @OnLongClick(R.id.item_meal)
        public void onLongClick(View view) {
            MealDialogFragment dialogFragment = MealDialogFragment.newInstance(meals.get(getAdapterPosition()).getPathImage());
            dialogFragment.show(mContext.getFragmentManager(),"");
        }
    }
}
