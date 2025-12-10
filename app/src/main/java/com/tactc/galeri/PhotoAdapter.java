package com.tactc.galeri;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.RecyclerViewHolder> {
    private final List<String> photoPaths;
    private final Context context;

    // Yapıcı metod (constructor, sınıftan nesne oluşunca ilk çalışacak işlemler)
    public PhotoAdapter(List<String> photoPaths, Context context) {
        this.photoPaths = photoPaths;
        this.context = context;
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
        }
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo, parent, false);

        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        File imgFile = new File(photoPaths.get(position));

        if (imgFile.exists()) {
            Glide.with(context).load(imgFile).into(holder.imageView);

            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, ImageDetailActivity.class);
                intent.putExtra("image_url", photoPaths.get(position));
                context.startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        return photoPaths.size();
    }
}