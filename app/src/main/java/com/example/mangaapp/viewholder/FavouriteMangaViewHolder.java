package com.example.mangaapp.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mangaapp.R;

public class FavouriteMangaViewHolder extends RecyclerView.ViewHolder {
    public ImageView favouriteImage;
    public TextView favouriteName;

    public FavouriteMangaViewHolder(@NonNull View itemView) {
        super(itemView);

        favouriteImage = itemView.findViewById(R.id.favourite_manga_image);
        favouriteName = itemView.findViewById(R.id.favourite_manga_name);
    }
}
