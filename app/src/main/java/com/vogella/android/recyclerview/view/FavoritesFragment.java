package com.vogella.android.recyclerview.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.vogella.android.recyclerview.controller.FavoriteController;
import com.vogella.android.recyclerview.controller.MyAdapter;
import com.vogella.android.recyclerview.model.Card;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import xavier.albanet.projetprogrammationmobile.R;

import static android.content.Context.MODE_PRIVATE;

public class FavoritesFragment extends Fragment {
    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private FavoriteController controller;
    private static final String PREFS = "PREFS";
    SharedPreferences sharedPreferences;
    ConstraintLayout rootLayout;
    EditText searchInput ;
    CharSequence search="";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view_fav);
        sharedPreferences = getContext().getSharedPreferences(PREFS, MODE_PRIVATE);
        controller = new FavoriteController(this);
        rootLayout = view.findViewById(R.id.root_layout_fav);
        searchInput = view.findViewById(R.id.search_input_fav);
        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mAdapter.getFilter().filter(s);
                search = s;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        controller.onStart();
        return view;
    }

    public void showListFav(List<Card> cardList) {
        List<Card> listFav = new ArrayList<>();
        for (Card card : cardList) {
            if(card.isFav()) {
                listFav.add(card);
            }
        }
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        // define an adapter
        this.mAdapter = new MyAdapter(this.getActivity(), listFav, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Card card) {
                controller.onClickedFavorite(card);
            }
        });
        recyclerView.setAdapter(mAdapter);
    }
}