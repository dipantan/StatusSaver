package com.apollo.statussaver.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import com.apollo.statussaver.R;

public class VideoGallery extends AppCompatActivity {
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_gallery);
        String uri = getIntent().getStringExtra("URI");
        videoView = findViewById(R.id.videoView);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(Uri.parse(uri));
        videoView.requestFocus();
        videoView.start();
    }

    public void share(View view) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("video/*");
        i.putExtra(Intent.EXTRA_STREAM, Uri.parse(getIntent().getStringExtra("URI")));
        startActivity(i);
    }

    public void shareWhatsAppv(View view) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("video/*");
        i.putExtra(Intent.EXTRA_STREAM, Uri.parse(getIntent().getStringExtra("URI")));
        i.setPackage("com.whatsapp");
        startActivity(i);
    }
}
