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

import com.bumptech.glide.Glide;
import com.example.mangaapp.Prevalent.Prevalent;
import com.example.mangaapp.R;
import com.example.mangaapp.activity.ChapterListActivity;
import com.example.mangaapp.viewholder.MangaViewHolder;
import com.example.mangaapp.model.Mangas;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class FragmentHome extends Fragment {
    private String databseName = "Mangas";
    private String SWITCH_PREF = "switch_pref";
    private String SWITCH_STATE = "switch_state";
    private Boolean switch_state;

    private RecyclerView mangaList;

    private DatabaseReference mangaRef;
    private SharedPreferences switchPref;
    private SharedPreferences.Editor switchEdit;

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

        switchPref = getActivity().getSharedPreferences(SWITCH_PREF, Context.MODE_PRIVATE);
        switchEdit = getActivity().getSharedPreferences(SWITCH_PREF, Context.MODE_PRIVATE).edit();

        switch_state = switchPref.getBoolean(SWITCH_STATE, false);


        mangaRef = FirebaseDatabase.getInstance().getReference().child(databseName);

        FirebaseRecyclerOptions<Mangas> options = new FirebaseRecyclerOptions.Builder<Mangas>()
                .setQuery(mangaRef, Mangas.class).build();

        FirebaseRecyclerAdapter<Mangas, MangaViewHolder> adapter =
                new FirebaseRecyclerAdapter<Mangas, MangaViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull MangaViewHolder mangaViewHolder, int i, @NonNull Mangas mangas) {
                        mangaViewHolder.nameManga.setText(mangas.getNameManga());
                        Glide.with(getContext()).load(mangas.getImgManga()).into(mangaViewHolder.imgManga);

                        mangaViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(getContext(), ChapterListActivity.class);
                                intent.putExtra("mangaName", mangas.getNameManga());
                                startActivity(intent);
                            }
                        });

                        mangaViewHolder.favouriteManga.setChecked(switch_state);
                        mangaViewHolder.favouriteManga.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                if (b) {
                                    Prevalent.favouriteMangas.add(mangas);
                                    switchEdit.putBoolean(SWITCH_STATE, true);
                                    switchEdit.commit();
                                } else {
                                    Prevalent.favouriteMangas.remove(mangas);
                                    switchEdit.remove(SWITCH_STATE);
                                    switchEdit.putBoolean(SWITCH_STATE, false);
                                    switchEdit.commit();
                                }
                            }
                        });
                    }

                    @NonNull
                    @Override
                    public MangaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.manga_item, parent, false);
                        MangaViewHolder holder = new MangaViewHolder(view);
                        return holder;
                    }
                };

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        mangaList.setLayoutManager(layoutManager);
        mangaList.setAdapter(adapter);
        adapter.startListening();
    }
}
