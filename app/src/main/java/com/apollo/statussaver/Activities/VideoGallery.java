package com.apollo.statussaver.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.apollo.statussaver.R;

import java.io.File;

public class VideoGallery extends AppCompatActivity {
    VideoView videoView;
    String uri;
    File f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_gallery);
        uri = getIntent().getStringExtra("URI");
        f = new File(uri);
        videoView = findViewById(R.id.videoView);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(Uri.parse(uri));
        videoView.requestFocus();
        videoView.start();
    }

    public void share(View view) {
        try {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + f.getAbsolutePath().replace("/file:", "")));
            //    Log.d("URI : ", "file://" + f.getAbsolutePath().replace("/file:", ""));
            startActivity(Intent.createChooser(intent, "Share image"));
        } catch (Exception e) {
            Toast.makeText(VideoGallery.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void shareWhatsAppv(View view) {
        try {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + f.getAbsolutePath().replace("/file:", "")));
            //     Log.d("URI : ", "file://" + f.getAbsolutePath().replace("/file:", ""));
            intent.setPackage("com.whatsapp");
            startActivity(intent);

        } catch (Exception e) {
            Toast.makeText(VideoGallery.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
