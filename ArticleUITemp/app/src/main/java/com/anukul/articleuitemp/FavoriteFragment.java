package com.anukul.articleuitemp;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {

    RecyclerView rvFavorite;
    AdapterFavorite mAdapter;
    String articleFavorite[] = {"Article 1", "Article 2", "Article 3"};

    public FavoriteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_favorite, container, false);

        rvFavorite = v.findViewById(R.id.rvFavorite);
        rvFavorite.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvFavorite.setLayoutManager(layoutManager);

        ArrayList<String> favArticle = new ArrayList<>();
        favArticle.add(articleFavorite[0]);
        favArticle.add(articleFavorite[1]);
        favArticle.add(articleFavorite[2]);

        mAdapter = new AdapterFavorite(favArticle, getActivity());
        rvFavorite.setAdapter(mAdapter);

        rvFavorite.getAdapter().notifyDataSetChanged();
        rvFavorite.scheduleLayoutAnimation();

        return v;
    }

}
