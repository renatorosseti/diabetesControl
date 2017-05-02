package com.diabetes.instameal.main;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.diabetes.instameal.Helper.CapturedHelper;
import com.diabetes.instameal.R;
import com.diabetes.instameal.model.Meal;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainAdapter extends ArrayAdapter<Meal> {


    private Context mContext;

    private List<Meal> meals;

    private LayoutInflater inflater;

    public MainAdapter(Context context, int resource, List<Meal> meals) {
        super(context, resource, meals);
        this.mContext = context;
        this.inflater = LayoutInflater.from(context);
        this.meals = meals;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        Meal meal = meals.get(position);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.meal_item, null, false);
            holder = new ViewHolder();
            holder.mealPhoto = (ImageView) convertView.findViewById(R.id.mealView);
            holder.preGlycemiaText = (TextView) convertView.findViewById(R.id.preGlycemiaText);
            holder.posGlycemiaText = (TextView) convertView.findViewById(R.id.posGlycemiaText);
            holder.mealType = (TextView) convertView.findViewById(R.id.mealType);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.preGlycemiaText.setText(meal.getPreGlycemia().toString());
        holder.posGlycemiaText.setText(meal.getPosGlycemia().toString());
        holder.mealType.setText(meal.getType());
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;
        Bitmap bitmap = BitmapFactory.decodeFile(CapturedHelper.getPath(mContext, meal.getIdImage()),options);
        holder.mealPhoto.setImageBitmap(bitmap);

//        Picasso.with(mContext).load(CapturedHelper.getPath(mContext, meal.getIdImage())).into(holder.mealPhoto);

        return convertView;
    }

    @Override
    public int getCount() {
        return meals.size();
    }

    class ViewHolder {
        ImageView mealPhoto;
        TextView preGlycemiaText;
        TextView posGlycemiaText;
        TextView mealType;
    }


}
