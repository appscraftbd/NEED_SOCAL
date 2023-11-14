package com.appscraftbd.needasocal;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;


public class HomeFragment extends Fragment {


    private SwipeRefreshLayout swipeRefreshLayout;

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_home, container, false);


        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        // Set the background color for the progress circle
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.white));
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(R.color.black);



        // Inflate the layout for this fragment
        return view;
    }
}