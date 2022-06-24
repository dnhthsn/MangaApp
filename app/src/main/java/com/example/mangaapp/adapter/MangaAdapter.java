package com.example.mangaapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mangaapp.R;
import com.example.mangaapp.model.Mangas;

import java.util.List;

public class MangaAdapter extends RecyclerView.Adapter<MangaAdapter.MangaViewHolder> {
    private List<Mangas> mangas;
    private ItemClickListener itemClickListener;

    public MangaAdapter(List<Mangas> mangas) {
        this.mangas = mangas;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public MangaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MangaViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_manga, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MangaViewHolder holder, int position) {
        Mangas manga = mangas.get(position);
        holder.nameManga.setText(manga.getNameManga());
        Glide.with(holder.itemView.getContext()).load(manga.getImgManga()).into(holder.imgManga);
    }

    @Override
    public int getItemCount() {
        return mangas.size();
    }

    public class MangaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imgManga;
        private TextView nameManga;

        public MangaViewHolder(@NonNull View itemView) {
            super(itemView);

            imgManga = itemView.findViewById(R.id.manga_image);
            nameManga = itemView.findViewById(R.id.manga_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null) {
                itemClickListener.onClick(view, getAdapterPosition(), false);
            }
        }
    }

    public interface ItemClickListener {
        void onClick(View view, int position, boolean isLongClick);
    }
}
