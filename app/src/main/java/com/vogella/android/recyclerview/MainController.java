package com.vogella.android.recyclerview;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vogella.android.recyclerview.model.Card;
import com.vogella.android.recyclerview.view.MainActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainController {

    private MainActivity activity;

    public MainController(MainActivity mainActivity) {
        this.activity = mainActivity;
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
        Call<List<Card>> call = restCardApi.getListCard();
        call.enqueue(new Callback<List<Card>>() {
            @Override
            public void onResponse(Call<List<Card>> call, Response<List<Card>> response) {
                List<Card> listCard = response.body();
                activity.showList(listCard);
            }

            @Override
            public void onFailure(Call<List<Card>> call, Throwable t) {
                Log.d("ERROR", "Api ERROR");
            }
        });

    }
}
