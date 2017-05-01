package com.diabetes.instameal.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.diabetes.instameal.R;
import com.diabetes.instameal.model.Meal;

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
        ViewHolder holder;

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
//        Bitmap bm = BitmapFactory.decodeByteArray(meals.get(position).getImage(), 0, meals.get(position).getImage().length);
//        DisplayMetrics dm = new DisplayMetrics();

//        holder.mealPhoto.setMinimumHeight(dm.heightPixels);
//        holder.mealPhoto.setMinimumWidth(dm.widthPixels);
        holder.preGlycemiaText.setText(meals.get(position).getPreGlycemia().toString());
        holder.posGlycemiaText.setText(meals.get(position).getPosGlycemia().toString());
        holder.mealType.setText(meals.get(position).getType());
        holder.mealPhoto.setImageDrawable(mContext.getDrawable(android.R.drawable.alert_dark_frame));
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
