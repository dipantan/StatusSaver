package com.apollo.statussaver.Activities;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.apollo.statussaver.Adapters.ViewPagerAdapter;
import com.apollo.statussaver.Fragments.ImageFragment;
import com.apollo.statussaver.Fragments.VideoFragment;
import com.apollo.statussaver.R;
import com.apollo.statussaver.Utils.ImageConstant;
import com.apollo.statussaver.Utils.ImageMethod;
import com.apollo.statussaver.Utils.VideoConstant;
import com.apollo.statussaver.Utils.VideoMethod;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private Toolbar toolbar;


    public void init() {
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        toolbar = findViewById(R.id.toolbar);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ImageConstant.mediaList.clear();
        VideoConstant.mediaList.clear();
    }

    public void getStorage() {
        String allPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/WhatsApp/Media/.Statuses";
        File storage1 = new File(allPath);
        File storage2 = new File(allPath);
        ImageMethod.load_Directory_Files(storage1);
        VideoMethod.load_Directory_Files(storage2);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        setSupportActionBar(toolbar);
        toolbar.setTitle("Status Saver");
        toolbar.setTitleTextColor(Color.WHITE);

        //Fragments are added here
        adapter.addFragments(new ImageFragment(), "IMAGES");
        adapter.addFragments(new VideoFragment(), "VIDEOS");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        getStorage();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Created by Dipantan", Toast.LENGTH_LONG).show();
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        //   MenuItem item = menu.findItem(R.id.myswitch);
        return true;
    }


    public void about(MenuItem item) {
        Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
    }

    public void update(MenuItem item) {
        Toast.makeText(this, "Update", Toast.LENGTH_SHORT).show();
    }
}

