package com.example.mangaapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mangaapp.R;
import com.example.mangaapp.model.Mangas;
import com.example.mangaapp.viewholder.FavouriteMangaViewHolder;

import java.util.List;

public class FavouriteMangaAdapter extends RecyclerView.Adapter<FavouriteMangaViewHolder> {
    private Context context;
    private List<Mangas> mangas;

    public FavouriteMangaAdapter(Context context, List<Mangas> mangas) {
        this.context = context;
        this.mangas = mangas;
    }

    @NonNull
    @Override
    public FavouriteMangaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FavouriteMangaViewHolder(LayoutInflater.from(context).inflate(R.layout.favourite_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteMangaViewHolder holder, int position) {
        holder.favouriteName.setText(mangas.get(position).getNameManga());
        Glide.with(context).load(mangas.get(position).getImgManga()).into(holder.favouriteImage);
    }

    @Override
    public int getItemCount() {
        return mangas.size();
    }
}
