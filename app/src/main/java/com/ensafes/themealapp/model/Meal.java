package com.ensafes.themealapp.model;

import com.google.gson.annotations.SerializedName;

public class Meal {

    @SerializedName("strMeal")
    private String name;

    @SerializedName("strMealThumb")
    private String thumb;

    public String getName() {
        return name;
    }

    public String getThumb() {
        return thumb;
    }
}
