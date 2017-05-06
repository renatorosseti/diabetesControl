package com.diabetes.instameal.main;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.diabetes.instameal.Helper.CapturedHelper;
import com.diabetes.instameal.R;
import com.diabetes.instameal.model.Meal;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {


    private Activity mContext;

    private List<Meal> meals;

    private Boolean mOrientation;

    public static final Boolean VERTICAL = false;

    public static final Boolean HORIZONTAL = true;

    public MainAdapter(Activity context, List<Meal> meals, Boolean orientation) {
        this.mContext = context;
        this.meals = meals;
        this.mOrientation = orientation;
    }

    private Boolean isOrientationHorizontal() {
        return mOrientation;
    }

    private int getDisplayParam(Boolean param) {
        Display display = mContext.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return param == HORIZONTAL ? size.x : size.y;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.meal_item, null);
        if(isOrientationHorizontal()) {
            ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(getDisplayParam(HORIZONTAL), ViewGroup.LayoutParams.WRAP_CONTENT);
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

//        holder.mealPhoto.setImageBitmap(bitmap);
//        new Runnable() {
//            public void run() {
//                BitmapFactory.Options options = new BitmapFactory.Options();
//                options.inSampleSize = 2;
//                Bitmap bitmap = BitmapFactory.decodeFile(meal.getPathImage(),options);
//            }
//        };

//        holder.mealPhoto.setImageURI(Uri.fromFile(CapturedHelper.getFile(mContext,meal.getIdImage())));
        Picasso.with(mContext).load("file://"+meal.getPathImage()).resize(getDisplayParam(HORIZONTAL), getDisplayParam(VERTICAL)/2)
                .centerCrop().placeholder(R.mipmap.ic_launcher).into(holder.mealPhoto);
    }


    @Override
    public int getItemCount() {
        return meals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatImageView mealPhoto;
        TextView preGlycemiaText;
        TextView posGlycemiaText;
        TextView mealType;
        public ViewHolder(View v) {
            super(v);
            this.mealPhoto = (AppCompatImageView) v.findViewById(R.id.mealView);
            this.preGlycemiaText = (TextView) v.findViewById(R.id.preGlycemiaText);
            this.posGlycemiaText = (TextView) v.findViewById(R.id.posGlycemiaText);
            this.mealType = (TextView) v.findViewById(R.id.mealType);
        }
    }


}
