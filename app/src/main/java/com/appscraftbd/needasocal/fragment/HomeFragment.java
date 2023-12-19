package com.appscraftbd.needasocal.fragment;

import static androidx.swiperefreshlayout.widget.SwipeRefreshLayout.*;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.appscraftbd.needasocal.R;


public class HomeFragment extends Fragment {


    public static Boolean boolean1 = true;

    RecyclerView recyclerView;
     public static SwipeRefreshLayout swipeRefreshLayout;

    @SuppressLint({"ResourceAsColor", "MissingInflatedId"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_home, container, false);


        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        // Set the background color for the progress circle
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.white));
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(R.color.black);




        recyclerView = view.findViewById(R.id.recyclehome);
        MyAdapter myAdapter = new MyAdapter();
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));




        // Inflate the layout for this fragment
        return view;
    }public class MyAdapter extends RecyclerView.Adapter{
        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View myView = LayoutInflater.from(getContext()).inflate(R.layout.post_layout,parent,false);

            return new MyviewHolder(myView);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 10;
        }

        public class MyviewHolder extends RecyclerView.ViewHolder{

            public MyviewHolder(@NonNull View itemView) {
                super(itemView);
            }
        }

    }


    public  void homeRefreshing(){
        try {
            swipeRefreshLayout.setRefreshing(true); // or false
            Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Code to be executed after the delay
                    swipeRefreshLayout.setRefreshing(false);
                }
            }, 5000); // Delay in milliseconds (1000 ms = 1 second)
        }catch (Exception e){

        }
    }
}