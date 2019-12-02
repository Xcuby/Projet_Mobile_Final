package com.vogella.android.recyclerview.controller;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.vogella.android.recyclerview.model.Card;
import com.vogella.android.recyclerview.view.FavoritesFragment;
import com.vogella.android.recyclerview.view.HomeFragment;

import java.lang.reflect.Type;
import java.util.List;

public class FavoriteController {
    private FavoritesFragment fragment;
    private SharedPreferences sharedPreferences;
    private List<Card> listCard;

    public FavoriteController(FavoritesFragment favoriteFragment) {
            this.fragment = favoriteFragment;
            this.sharedPreferences = fragment.getContext().getSharedPreferences(Constants.DATABASE_NAME, Context.MODE_PRIVATE);
    }


    public void onStart(){
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            String json = sharedPreferences.getString(Constants.DATABASE_NAME, "");
            Type listType = new TypeToken<List<Card>>(){}.getType();
            listCard = gson.fromJson(json, listType);
            fragment.showListFav(listCard);
    }

    private void storeData(List<Card> listCard) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Gson gson = new Gson();
            String json = gson.toJson(listCard);
            editor.putString(Constants.DATABASE_NAME, json);
            editor.apply();
    }

    public void onClickedFavorite(Card cardClicked) {
            for (Card card : listCard) {
                if(card.getId().equals(cardClicked.getId())){
                    card.changeFav();
                    break;
                }
            }
            fragment.showListFav(listCard);
            storeData(listCard);
    }
}

