package com.appscraftbd.needasocal.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.appscraftbd.needasocal.R;
import com.appscraftbd.needasocal.Recycle_view;


public class GlobalFragment extends Fragment {

    RecyclerView recyclerView;
    public static SwipeRefreshLayout swipeRefreshLayout;

    @SuppressLint({"MissingInflatedId", "ResourceAsColor"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_global, container, false);

        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.white));
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(R.color.black);




        recyclerView = view.findViewById(R.id.recycleglobal);

        Recycle_view globalRecycleView = new Recycle_view();
        globalRecycleView.recycle_work(getContext(),recyclerView);




        return view;
    }
}