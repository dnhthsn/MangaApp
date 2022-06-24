package com.example.mangaapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mangaapp.prevalent.Prevalent;
import com.example.mangaapp.R;
import com.example.mangaapp.adapter.FavouriteMangaAdapter;
import com.example.mangaapp.model.Mangas;
import com.example.mangaapp.util.Const;

import java.util.ArrayList;
import java.util.List;

public class FragmentFavourite extends Fragment {
    private RecyclerView favouriteList;

    private FavouriteMangaAdapter adapter;
    private List<Mangas> mangas;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        favouriteList = view.findViewById(R.id.favourite_list);

        mangas = new ArrayList<>();
        mangas = Prevalent.favouriteMangas;

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        favouriteList.setLayoutManager(layoutManager);
        adapter = new FavouriteMangaAdapter(mangas);
        favouriteList.setAdapter(adapter);
    }
}
