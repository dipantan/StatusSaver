package com.apollo.statussaver.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.apollo.statussaver.R;
import com.bumptech.glide.Glide;

public class ImageGallery extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_gallery);
        imageView = findViewById(R.id.imageView1);
        String uri = getIntent().getStringExtra("URI");
        Glide.with(this).load(uri).into(imageView);
    }

    public void share(View view) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("image/*");
        i.putExtra(Intent.EXTRA_STREAM, Uri.parse(getIntent().getStringExtra("URI")));
        startActivity(i);
    }

    public void shareWhatsApp(View view) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("image/*");
        i.putExtra(Intent.EXTRA_STREAM, Uri.parse(getIntent().getStringExtra("URI")));
        i.setPackage("com.whatsapp");
        startActivity(i);
    }
}
