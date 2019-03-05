package com.vogella.android.recyclerview;

import android.content.SharedPreferences;
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
    SharedPreferences sharedPreferences = activity.getSharedPreferences(Constants.DATABASE_NAME, activity.MODE_PRIVATE);


    public void onStart(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.hearthstonejson.com/v1/28855/enUS/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        RestCardApi restCardApi = retrofit.create(RestCardApi.class);
            if (!sharedPreferences.contains("1")) {

                Call<List<Card>> call = restCardApi.getListCard();
                call.enqueue(new Callback<List<Card>>() {
                    @Override
                    public void onResponse(Call<List<Card>> call, Response<List<Card>> response) {
                        List<Card> listCard = response.body();
                        storeData(listCard);
                        activity.showList(listCard);
                    }

                    @Override
                    public void onFailure(Call<List<Card>> call, Throwable t) {
                        Log.d("ERROR", "Api ERROR");
                    }

                });
            }
            else {
                String json = sharedPreferences.getString("1", "");
                List<Card> listCard = gson.fromJson(json, List.class);
                activity.showList(listCard);
            }
    }

    private void storeData(List<Card> listCard) {


        SharedPreferences.Editor editor = sharedPreferences.edit();
            Gson gson = new Gson();
            String json = gson.toJson(listCard);
            editor.putString("1", json);
            editor.commit();
    }
}

