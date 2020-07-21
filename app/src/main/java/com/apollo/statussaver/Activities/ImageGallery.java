package com.apollo.statussaver.Activities;

import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("image/*");
                        intent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + f.getAbsolutePath().replace("/file:", "")));
                        Log.d("URI : ", "file://" + f.getAbsolutePath());
                        startActivity(Intent.createChooser(intent, "Share image"));
                    /*    Intent intent;
                        Uri uri1;
                        uri1 = FileProvider.getUriForFile(getApplicationContext(), new StringBuffer().append(getApplicationContext().getPackageName()).append(".share").toString(), f);
                        intent = new Intent("android.intent.action.ATTACH-DATA");
                        intent.putExtra("mimeType","image/*");
                        intent.setDataAndType(uri1,"inage/*");
                        startActivity(intent);
                        */
                    } else {
                        share();
                    }
                } catch (Exception e) {
                    Toast.makeText(ImageGallery.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
        shareWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        //   Toast.makeText(ImageGallery.this, "Coming soon", Toast.LENGTH_SHORT).show();
                        File file2 = new File(uri);
                        //        Uri photoUri2 = FileProvider.getUriForFile(ImageGallery.this, getApplicationContext().getPackageName() + ".share", file2);
                        MediaScannerConnection.scanFile(getApplicationContext(), new String[]{file2.getAbsolutePath()}, null, new MediaScannerConnection.OnScanCompletedListener() {
                            @Override
                            public void onScanCompleted(String path, Uri uri) {
                                Log.d("URI2 ", String.valueOf(uri));
                                Intent i = new Intent(Intent.ACTION_SEND);
                                i.setType("image/*");
                                i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                i.putExtra(Intent.EXTRA_STREAM, uri);
                                i.setPackage("com.whatsapp");
                                startActivity(i);
                            }
                        });

                    } else {
                        shareWhatsApp();
                    }
                } catch (Exception e) {
                    Toast.makeText(ImageGallery.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public void share() {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("image/*");
        i.putExtra(Intent.EXTRA_STREAM, Uri.parse(getIntent().getStringExtra("URI")));
        startActivity(i);
    }

    public void shareWhatsApp() {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("image/*");
        i.putExtra(Intent.EXTRA_STREAM, Uri.parse(getIntent().getStringExtra("URI")));
        i.setPackage("com.whatsapp");
        startActivity(i);
    }
}
