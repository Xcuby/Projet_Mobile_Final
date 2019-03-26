package com.vogella.android.recyclerview.controller;

import com.vogella.android.recyclerview.model.Card;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestCardApi {

    @GET("cards.collectible.json")
    Call<List<Card>> getListCard();
}
