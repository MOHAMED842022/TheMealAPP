package com.ensafes.themealapp.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ensafes.themealapp.R;
import com.ensafes.themealapp.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.MealViewHolder> {

    private List<Meal> mealList;
    private List<Meal> mealListFull;

    public MealAdapter(List<Meal> mealList) {
        this.mealList = mealList;
        this.mealListFull = new ArrayList<>(mealList);
    }

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_meal, parent, false);
        return new MealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
        Meal meal = mealList.get(position);
        holder.name.setText(meal.getName());
        Glide.with(holder.itemView.getContext())
                .load(meal.getThumb())
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return mealList.size();
    }

    class MealViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView img;

        public MealViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.mealName);
            img = itemView.findViewById(R.id.mealThumb);
        }
    }

    public void filter(String text) {
        mealList.clear();
        if (text.isEmpty()) {
            mealList.addAll(mealListFull);
        } else {
            text = text.toLowerCase();
            for (Meal meal : mealListFull) {
                if (meal.getName().toLowerCase().contains(text)) {
                    mealList.add(meal);
                }
            }
        }
        notifyDataSetChanged();
    }
}
