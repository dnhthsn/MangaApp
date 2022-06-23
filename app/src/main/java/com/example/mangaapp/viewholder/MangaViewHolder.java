package com.example.mangaapp.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mangaapp.R;

public class MangaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public ImageView imgManga;
    public TextView nameManga;
    public SwitchCompat favouriteManga;

    public ItemClickListener itemClickListener;

    public MangaViewHolder(@NonNull View itemView) {
        super(itemView);

        imgManga = itemView.findViewById(R.id.manga_image);
        nameManga = itemView.findViewById(R.id.manga_name);
        favouriteManga = itemView.findViewById(R.id.favourite_manga);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(), false);
    }

    public interface ItemClickListener {
        void onClick(View view, int position, boolean isLongClick);
    }
}
