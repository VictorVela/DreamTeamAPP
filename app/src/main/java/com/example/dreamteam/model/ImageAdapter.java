package com.example.dreamteam.model;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dreamteam.R;
import com.example.dreamteam.activity.GalleryActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.Imageviewholder> {

    private Context mContext;
    private List<Upload> mUploads;

    public ImageAdapter(Context context,List<Upload> uploads){
        mContext = context;
        mUploads = uploads;
    }

    @NonNull
    @Override
    public Imageviewholder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.image_item, parent,false);
        return new Imageviewholder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull Imageviewholder imageviewholder, int i) {
        Upload uploadCurrent = mUploads.get(i);
        imageviewholder.textViewName.setText(uploadCurrent.getmName());
        Picasso.get().load(uploadCurrent.getmImageUrl()).placeholder(R.mipmap.ic_launcher).fit().centerCrop().into(imageviewholder.imageView);
//          Picasso.get().load(uploadCurrent.getmImageUrl()).into(imageviewholder.imageView);


    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class Imageviewholder extends RecyclerView.ViewHolder{
        public TextView textViewName;
        public ImageView imageView;

        public Imageviewholder(@NonNull View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.text_view_name);
            imageView = itemView.findViewById(R.id.image_view_upload);
        }
    }
}
