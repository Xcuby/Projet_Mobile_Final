package com.vogella.android.recyclerview.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.vogella.android.recyclerview.model.Card;
import com.vogella.android.recyclerview.view.HomeFragment;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeController {

    private HomeFragment fragment;
    private SharedPreferences sharedPreferences;

    public HomeController(HomeFragment homeFragment) {
        this.fragment = homeFragment;
        this.sharedPreferences = fragment.getContext().getSharedPreferences(Constants.DATABASE_NAME, Context.MODE_PRIVATE);
    }


    public void onStart(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
                String json = sharedPreferences.getString(Constants.DATABASE_NAME, "");
                Type listType = new TypeToken<List<Card>>(){}.getType();
                List<Card> listCard = gson.fromJson(json, listType);
                fragment.showList(listCard);
    }
}


