package com.ensafes.themealapp.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MealResponse {

    @SerializedName("meals")
    private List<Meal> meals;

    public List<Meal> getMeals() {
        return meals;
    }
}
