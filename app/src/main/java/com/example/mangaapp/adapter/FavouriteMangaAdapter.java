package com.example.mangaapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mangaapp.R;
import com.example.mangaapp.model.Mangas;
import java.util.List;

public class FavouriteMangaAdapter extends RecyclerView.Adapter<FavouriteMangaAdapter.FavouriteMangaViewHolder> {
    private List<Mangas> mangas;

    public FavouriteMangaAdapter(List<Mangas> mangas) {
        this.mangas = mangas;
    }

    @NonNull
    @Override
    public FavouriteMangaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FavouriteMangaViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.__favourite_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteMangaViewHolder holder, int position) {
        holder.favouriteName.setText(mangas.get(position).getNameManga());
        Glide.with(holder.itemView.getContext()).load(mangas.get(position).getImgManga()).into(holder.favouriteImage);
    }

    @Override
    public int getItemCount() {
        return mangas.size();
    }

    public class FavouriteMangaViewHolder extends RecyclerView.ViewHolder {
        public ImageView favouriteImage;
        public TextView favouriteName;

        public FavouriteMangaViewHolder(@NonNull View itemView) {
            super(itemView);

            favouriteImage = itemView.findViewById(R.id.favourite_manga_image);
            favouriteName = itemView.findViewById(R.id.favourite_manga_name);
        }
    }
}
