package com.example.mangaapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mangaapp.adapter.MangaAdapter;
import com.example.mangaapp.R;
import com.example.mangaapp.activity.ChapterListActivity;
import com.example.mangaapp.util.Const;
import com.example.mangaapp.model.Mangas;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment implements MangaAdapter.ItemClickListener {
    private RecyclerView mangaList;

    private MangaAdapter mangaAdapter;
    private List<Mangas> mangas;
    private DatabaseReference mangaRef;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mangaList = view.findViewById(R.id.mangas_list);

        mangas = new ArrayList<>();
        mangaAdapter = new MangaAdapter(mangas);

        mangaRef = FirebaseDatabase.getInstance().getReference().child(Const.Database.manga);

        mangaRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Mangas manga = dataSnapshot.getValue(Mangas.class);
                    mangas.add(manga);
                }
                mangaAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        mangaList.setLayoutManager(layoutManager);
        mangaAdapter.setItemClickListener(FragmentHome.this);
        mangaList.setAdapter(mangaAdapter);
    }

    @Override
    public void onClick(View view, int position, boolean isLongClick) {
        Intent intent = new Intent(getContext(), ChapterListActivity.class);
        intent.putExtra(Const.Sender.mangaName, mangas.get(position).getNameManga());
        startActivity(intent);
    }
}
