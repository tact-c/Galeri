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
import java.util.ArrayList;
import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.RecyclerViewHolder> {

    private final Context context;
    private final ArrayList<MediaFile> mediaFiles;
    // Yapıcı metod (constructor, sınıftan nesne oluşunca ilk çalışacak işlemler)
    public PhotoAdapter(ArrayList<MediaFile> mediaFiles, Context context) {
        this.mediaFiles = mediaFiles;
        this.context = context;
    }


    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView, playIcon;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            playIcon = itemView.findViewById(R.id.playIcon);
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
        MediaFile mediaFile = mediaFiles.get(position);

        Glide.with(context).load(mediaFile.getPath()).into(holder.imageView);

        if(mediaFile.getType()==MediaFile.TYPE_VIDEO){
            holder.playIcon.setVisibility(View.VISIBLE);
        } else{
            holder.playIcon.setVisibility(View.GONE);
        }


        holder.itemView.setOnClickListener(v -> {
            if (mediaFile.getType() == MediaFile.TYPE_VIDEO) {
                Intent intent = new Intent(context, VideoPlayerActivity.class);
                intent.putExtra("videoPath", mediaFile.getPath());
                context.startActivity(intent);
            } else {
                Intent intent = new Intent(context, ImageDetailActivity.class);
                intent.putExtra("image_url", mediaFile.getPath());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mediaFiles.size();
    }
}