package com.apollo.statussaver.Adapters;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.apollo.statussaver.ImageAdapter;
import com.apollo.statussaver.R;
import com.apollo.statussaver.Utils.Constant1;
import com.bumptech.glide.Glide;

import org.apache.commons.io.FileUtils;

import java.io.File;

/**
 * Created by Apollo on 5/29/2020.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyViewHolder> {
    private Context mContext;
    // MKPlayer mkPlayer;

    public VideoAdapter(Context mContext) {
        this.mContext = mContext;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.file_list, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, final int i) {
        //  ((FileLayoutHolder) viewHolder).title.setText(Constant.mediaList.get(i).getName());
        final Uri uri = Uri.fromFile(Constant1.mediaList.get(i));
        Glide.with(mContext)
                .load(uri).thumbnail(0.1f).into(viewHolder.thumbnail);

        //On item click

        ((MyViewHolder) viewHolder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, uri.toString(), Toast.LENGTH_LONG).show();
            }
        });

        // on button click


        ((MyViewHolder) viewHolder).btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    File source = new File(uri.getPath());           //source directory
                    File dest = new File(Environment.getExternalStorageDirectory().getPath() + "/" + "StatusSaver/" + source.getName()); //destination directory
                    FileUtils.copyFileToDirectory(source, dest);      //copy operation
                    Toast.makeText(mContext, "Saved to StatusSaver folder  ", Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    e.printStackTrace();
                    displayMessage(e.getMessage());
                }
            }
        });
    }

    private void displayMessage(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
    }


    @Override
    public int getItemCount() {
        return Constant1.mediaList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView thumbnail;
        Button btn;


        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            thumbnail = itemView.findViewById(R.id.thumbnail);
            btn = itemView.findViewById(R.id.btn);
        }
    }
}
