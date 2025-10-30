package com.ensafes.themealapp.api;

import com.ensafes.themealapp.model.MealResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("search.php?s=chicken") // Endpoint complet ici
    Call<MealResponse> getMeals();
}
