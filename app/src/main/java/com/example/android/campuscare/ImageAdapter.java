package com.example.android.campuscare;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;

/**
 * Created by user1 on 10/24/2018.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;


public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    private Context mContext;
    private List<Upload> mUploads;


    public ImageAdapter(Context context, List<Upload> uploads) {
        mContext = context;
        mUploads = uploads;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.image_item, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
       final Upload uploadCurrent = mUploads.get(position);
        holder.textViewName.setText(uploadCurrent.getName());
        holder.Username.setText(uploadCurrent.getUsername());
      //  Toast.makeText(mContext,uploadCurrent.getImageUrl(),Toast.LENGTH_SHORT).show();
         final String chi= uploadCurrent.getImageUrl();
      /*  Glide.with(mContext)
                .load(chi)
                .into(holder.imageView);
*/
        Glide.with(mContext)
                .load(uploadCurrent.getImageUrl())
                .into(holder.imageView);

        /*  Picasso.get()
                .load(uploadCurrent.getImageUrl())
                .into(holder.imageView);
        */System.out.println("--------------------------------------"+uploadCurrent.getImageUrl()+"-------------------");
       /* holder.l.setOnClickListener((view)-> {
            Toast.makeText(mContext,"you clicked "+uploadCurrent.getName(),Toast.LENGTH_SHORT).show();
        });
*/
        holder.l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"you clicked "+uploadCurrent.getName(),Toast.LENGTH_SHORT).show();
                Intent comm=new Intent(mContext,viewing.class);

                comm.putExtra("image_url",uploadCurrent.getImageUrl());
                comm.putExtra("desc",uploadCurrent.getName());
                comm.putExtra("username",uploadCurrent.getUsername());
                comm.putExtra("id",uploadCurrent.getid());
                mContext.startActivity(comm);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName;
        public ImageView imageView;
        public TextView Username;
        public LinearLayout l;
        public ImageViewHolder(View itemView) {
            super(itemView);
            Username=itemView.findViewById(R.id.username);
            textViewName = itemView.findViewById(R.id.post_desc);
            imageView = itemView.findViewById(R.id.post_img);
            l=(LinearLayout)itemView.findViewById(R.id.linear);
        }
    }
}