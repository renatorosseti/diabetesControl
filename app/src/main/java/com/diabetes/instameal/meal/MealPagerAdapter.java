package com.diabetes.instameal.meal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.diabetes.instameal.R;
import com.diabetes.instameal.model.Meal;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class MealPagerAdapter extends PagerAdapter {

    private Context mContext;

    private List<Meal> meals;

    LayoutInflater mLayoutInflater;

    public MealPagerAdapter(Context context, List<Meal> meals) {
        this.mContext = context;
        this.meals = meals;
        this.mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        Meal meal = meals.get(position);
        ViewHolder holder = new ViewHolder();
        View convertView = mLayoutInflater.inflate(R.layout.meal_item, collection, false);

        holder.mealPhoto = (ImageView) convertView.findViewById(R.id.mealView);
        holder.preGlycemiaText = (TextView) convertView.findViewById(R.id.preGlycemiaText);
        holder.posGlycemiaText = (TextView) convertView.findViewById(R.id.posGlycemiaText);
        holder.mealType = (TextView) convertView.findViewById(R.id.mealType);


        holder.preGlycemiaText.setText(meal.getPreGlycemia().toString());
        holder.posGlycemiaText.setText(meal.getPosGlycemia().toString());
        holder.mealType.setText(meal.getType());
//        Picasso.with(mContext).load(getFile(meals.get(position).getIdImage())).into(holder.mealPhoto);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        options.inJustDecodeBounds = false;
        options.inTempStorage = new byte[128];

        Bitmap b = BitmapFactory.decodeFile(getFile(meals.get(position).getIdImage()),options);
        holder.mealPhoto.setImageBitmap(b);


//        holder.mealPhoto.setImageDrawable(mContext.getDrawable(android.R.drawable.alert_dark_frame));
        collection.addView(convertView);
        return convertView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

    @Override
    public int getCount() {
        return meals.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    public String getFile(String imageName) {
        String path = Environment.getExternalStorageDirectory().toString()+"/Android/data/"+mContext.getPackageName()+"/files/"+imageName;
        Log.d("Files", "Path: " + path);
//        File file = new File(path);
        return path;

    }

    class ViewHolder {
        ImageView mealPhoto;
        TextView preGlycemiaText;
        TextView posGlycemiaText;
        TextView mealType;
    }
}
