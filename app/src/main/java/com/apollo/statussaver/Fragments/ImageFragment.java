package com.apollo.statussaver.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apollo.statussaver.Adapters.ImageAdapter;
import com.apollo.statussaver.R;

/**
 * Created by Apollo on 5/29/2020.
 */

public class ImageFragment extends Fragment {
    View v;
    RecyclerView recyclerView;
    ImageAdapter adapter;

    public ImageFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_image, container, false);
        recyclerView = v.findViewById(R.id.recyclerViewImage);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ImageAdapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        recyclerView.setNestedScrollingEnabled(false);
        return v;
    }
}
