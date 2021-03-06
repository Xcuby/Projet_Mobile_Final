package com.vogella.android.recyclerview.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.vogella.android.recyclerview.model.Card;
import com.vogella.android.recyclerview.view.MainActivity;

import java.lang.reflect.Type;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainController {

    private MainActivity activity;
    private SharedPreferences sharedPreferences;

    public MainController(MainActivity mainActivity) {
        this.activity = mainActivity;
        sharedPreferences = activity.getSharedPreferences(Constants.DATABASE_NAME, Context.MODE_PRIVATE);
    }


    public void onStart(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.hearthstonejson.com/v1/28855/enUS/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        RestCardApi restCardApi = retrofit.create(RestCardApi.class);
            if (!sharedPreferences.contains(Constants.DATABASE_NAME)) {
                Call<List<Card>> call = restCardApi.getListCard();
                call.enqueue(new Callback<List<Card>>() {
                    @Override
                    public void onResponse(Call<List<Card>> call, Response<List<Card>> response) {
                        List<Card> listCard = response.body();
                        //regarder sur chaque élément ->favoris
                        storeData(listCard);
                    }

                    @Override
                    public void onFailure(Call<List<Card>> call, Throwable t) {
                        Log.d("ERROR", "Api ERROR");
                    }

                });
            }
            else {
                String json = sharedPreferences.getString(Constants.DATABASE_NAME, "");
                Type listType = new TypeToken<List<Card>>(){}.getType();
            }
    }

    private void storeData(List<Card> listCard) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
            Gson gson = new Gson();
            String json = gson.toJson(listCard);
            editor.putString(Constants.DATABASE_NAME, json);
            editor.apply();
    }
}

