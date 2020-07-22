package com.apollo.statussaver.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.apollo.statussaver.R;
import com.bumptech.glide.Glide;

import java.io.File;

public class ImageGallery extends AppCompatActivity {
    ImageView imageView;
    Button share, shareWhatsapp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_gallery);
        imageView = findViewById(R.id.imageView1);
        final String uri = getIntent().getExtras().getString("URI");

        final File f = new File(uri);

        Glide.with(this).load(uri).into(imageView);
        share = findViewById(R.id.share);
        shareWhatsapp = findViewById(R.id.shareWhatsApp);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                    StrictMode.setVmPolicy(builder.build());
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("image/*");
                    intent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + f.getAbsolutePath().replace("/file:", "")));
                    //       Log.d("URI : ", "file://" + f.getAbsolutePath().replace("/file:", ""));
                    startActivity(Intent.createChooser(intent, "Share image"));
                } catch (Exception e) {
                    Toast.makeText(ImageGallery.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
        shareWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                    StrictMode.setVmPolicy(builder.build());
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("image/*");
                    intent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + f.getAbsolutePath().replace("/file:", "")));
                    //  Log.d("URI : ", "file://" + f.getAbsolutePath().replace("/file:", ""));
                    intent.setPackage("com.whatsapp");
                    startActivity(intent);

                } catch (Exception e) {
                    Toast.makeText(ImageGallery.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    //
                }
            }
        });
    }
}
