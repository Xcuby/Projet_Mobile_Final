package com.vogella.android.recyclerview.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.google.android.material.internal.NavigationMenuView;
import com.vogella.android.recyclerview.controller.HomeController;
import com.vogella.android.recyclerview.controller.MyAdapter;
import com.vogella.android.recyclerview.model.Card;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import xavier.albanet.projetprogrammationmobile.R;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment {
     private RecyclerView recyclerView;
        private RecyclerView.Adapter mAdapter;
        private RecyclerView.LayoutManager layoutManager;
        private HomeController controller;
        private static final String PREFS = "PREFS";
        SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_home, container, false);
            recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
            sharedPreferences = getContext().getSharedPreferences(PREFS, MODE_PRIVATE);
            controller = new HomeController(this);
            controller.onStart();
            return view;
    }

    public void showList(List<Card> card) {
            recyclerView.setHasFixedSize(true);
            // use a linear layout manager
            layoutManager = new LinearLayoutManager(this.getActivity());
            recyclerView.setLayoutManager(layoutManager);
            // define an adapter
            mAdapter = new MyAdapter(this.getActivity(), card, new MyAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(Card card) {
                    controller.onClickedFavorite(card);
                }
            });
            recyclerView.setAdapter(mAdapter);
        }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                controller.getFilter().filter(newText);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }
}