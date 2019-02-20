package com.vogella.android.recyclerview;

import com.vogella.android.recyclerview.model.Card;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestCardApi {

    @GET("card")
    Call<List<Card>> getListCard();
}
