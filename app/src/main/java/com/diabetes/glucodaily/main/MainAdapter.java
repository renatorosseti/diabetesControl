package com.diabetes.glucodaily.main;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.diabetes.glucodaily.Helper.DataHelper;
import com.diabetes.glucodaily.R;
import com.diabetes.glucodaily.core.ui.BaseActivity;
import com.diabetes.glucodaily.model.Meal;
import com.diabetes.glucodaily.model.MealHolder;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;
import static com.diabetes.glucodaily.core.ui.BaseActivity.HORIZONTAL;
import static com.diabetes.glucodaily.core.ui.BaseActivity.VERTICAL;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private BaseActivity mActivity;

    private List<MealHolder> mealHolders;

    private List<Meal> removeMeals;

    private Boolean mOrientation;

    public MainAdapter(BaseActivity context, List<MealHolder> mealHolders, Boolean orientation) {
        this.mActivity = context;
        this.mealHolders = mealHolders;
        this.removeMeals = new ArrayList<>();
        this.mOrientation = orientation;
    }

    public void setMealsChecked() {
        for (MealHolder mealHolder : mealHolders) {
            mealHolder.setSelected(false);
        }
        removeMeals.clear();
        notifyDataSetChanged();
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
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MealHolder mealHolder = mealHolders.get(position);
        final Meal meal = mealHolder.getMeal();
        holder.preGlycemiaText.setText(meal.getPreGlycemia().toString()  + " mg/dL");
        String posGlycemiaPlaceHolder = isOrientationHorizontal() ? " - " : mActivity.getString(R.string.fill_pos_glycemia);
        holder.posGlycemiaText.setText(meal.getPosGlycemia() == 0 ? posGlycemiaPlaceHolder : meal.getPosGlycemia().toString() + " mg/dL");
        String mealType = mActivity.getMealType(meal.getType());
        holder.mealType.setText(isOrientationHorizontal() ? mealType : DataHelper.removeBreakLine(mealType));
        holder.dosage.setText(meal.getDosageInsulin().toString() + " un");
        holder.itemView.setAlpha(mealHolder.isSelected() ? 0.6f : 1);
        Picasso.with(mActivity)
                .load(DataHelper.getImageFile(mActivity,meal.getPathImage()))
                .fit()
                .into(holder.mealPhoto);
    }

    @Override
    public int getItemCount() {
        return mealHolders.size();
    }

    public List<Meal> getRemoveMeals() {
        return removeMeals;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.mealView) AppCompatImageView mealPhoto;
        @BindView(R.id.preGlycemiaText) TextView preGlycemiaText;
        @BindView(R.id.posGlycemiaText) TextView posGlycemiaText;
        @BindView(R.id.mealType) TextView mealType;
        @BindView(R.id.dosageText) TextView dosage;
        @BindView(R.id.item_meal) View itemView;

        ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
            this.mealPhoto.setMinimumHeight(BaseActivity.getDisplayParam(VERTICAL));
            this.mealPhoto.setMinimumWidth(BaseActivity.getDisplayParam(HORIZONTAL));
        }

        @OnClick(R.id.item_meal)
        public void onClick() {
            if(!isOrientationHorizontal()) {
                MealHolder mealHolder = mealHolders.get(getAdapterPosition());
                Meal meal = mealHolder.getMeal();
                if(mActivity.isGarbageItemVisible()) {
                    if(removeMeals.contains(meal)) {
                        mealHolder.setSelected(false);
                        removeMeals.remove(meal);
                    } else {
                        mealHolder.setSelected(true);
                        removeMeals.add(meal);
                    }
                    notifyDataSetChanged();
                } else if (meal.getPosGlycemia() == 0) {
                    MealDialogFragment dialogFragment = MealDialogFragment.newInstance(meal.getPathImage());
                    dialogFragment.show(mActivity.getFragmentManager(),"");
                }
            }
        }

        @OnLongClick(R.id.item_meal)
        public boolean onItemSelectedToDelete() {
            if(!isOrientationHorizontal()) {
                MealHolder mealHolder = mealHolders.get(getAdapterPosition());
                Meal meal = mealHolder.getMeal();
                mActivity.setVisibleGarbageItem(true);
                mealHolder.setSelected(true);
                removeMeals.add(meal);
                notifyDataSetChanged();
            }
            return true;
        }
    }
}
