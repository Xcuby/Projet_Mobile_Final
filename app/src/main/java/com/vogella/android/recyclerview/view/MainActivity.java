package com.vogella.android.recyclerview.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vogella.android.recyclerview.MyAdapter;
import com.vogella.android.recyclerview.RestCardApi;
import com.vogella.android.recyclerview.model.Card;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xavier.albanet.projetprogrammationmobile.R;

public class MainActivity extends Activity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        // use this setting to
        // improve performance if you know that changes
        // in content do not change the layout size
        // of the RecyclerView
        //Appel serveur

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
                showList(listCard);
            }

            @Override
            public void onFailure(Call<List<Card>> call, Throwable t) {
                Log.d("ERROR", "Api ERROR");
            }
        });
    }
    public void showList(List<Card> card){
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // define an adapter
        mAdapter = new MyAdapter(this, card);
        recyclerView.setAdapter(mAdapter);
    }
}