package com.ensafes.themealapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;

import com.ensafes.themealapp.api.ApiClient;
import com.ensafes.themealapp.api.ApiService;
import com.ensafes.themealapp.model.Meal;
import com.ensafes.themealapp.model.MealResponse;
import com.ensafes.themealapp.ui.MealAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MealAdapter mealAdapter;
    private ApiService apiService;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisation
        recyclerView = findViewById(R.id.recyclerMeals);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        searchView = findViewById(R.id.searchView);

        apiService = ApiClient.getClient().create(ApiService.class);

        loadMeals();

        // Recherche en temps réel
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (mealAdapter != null) mealAdapter.filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (mealAdapter != null) mealAdapter.filter(newText);
                return false;
            }
        });
    }

    private void loadMeals() {
        Call<MealResponse> call = apiService.getMeals();
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Meal> meals = response.body().getMeals();
                    if (meals != null) {
                        Log.d("API", "Nombre de repas récupérés : " + meals.size());
                        mealAdapter = new MealAdapter(meals);
                        recyclerView.setAdapter(mealAdapter);
                    } else {
                        Log.d("API", "meals est null");
                    }
                } else {
                    Log.d("API", "Réponse KO ou body null");
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
