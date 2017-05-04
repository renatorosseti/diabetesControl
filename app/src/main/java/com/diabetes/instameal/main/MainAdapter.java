package com.diabetes.instameal.main;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.diabetes.instameal.Helper.CapturedHelper;
import com.diabetes.instameal.R;
import com.diabetes.instameal.model.Meal;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {


    private Context mContext;

    private List<Meal> meals;

    public MainAdapter(Context context, List<Meal> meals) {
        this.mContext = context;
        this.meals = meals;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.meal_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Meal meal = meals.get(position);
        holder.preGlycemiaText.setText(meal.getPreGlycemia().toString());
        holder.posGlycemiaText.setText(meal.getPosGlycemia().toString());
        holder.mealType.setText(meal.getType());
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        Bitmap bitmap = BitmapFactory.decodeFile(CapturedHelper.getPath(mContext, meal.getPathImage()),options);
        holder.mealPhoto.setImageBitmap(bitmap);
//        holder.mealPhoto.setImageURI(Uri.fromFile(CapturedHelper.getFile(mContext,meal.getIdImage())));
//        Picasso.with(mContext).load(CapturedHelper.getPath(mContext,meal.getIdImage())).placeholder(R.mipmap.ic_launcher).into(holder.mealPhoto);
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
