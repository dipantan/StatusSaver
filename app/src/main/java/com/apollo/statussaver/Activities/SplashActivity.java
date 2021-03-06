package com.apollo.statussaver.Activities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.widget.Toast;

import com.apollo.statussaver.R;
import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;

import java.io.File;
import java.util.ArrayList;


public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        Permissions.check(this, permissions, null, null, new PermissionHandler() {
            @Override
            public void onGranted() {

                File saveFolder = new File(Environment.getExternalStorageDirectory().getPath() + "/" + "StatusSaver/");
                saveFolder.mkdir();
                new Handler().postDelayed(new Runnable() {


                    @Override
                    public void run() {
                        Intent i = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                }, 2000);
            }
            @Override
            public void onDenied(Context context, ArrayList<String> permissionsDenied) {
                Toast.makeText(context, "Storage permission needed", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}
