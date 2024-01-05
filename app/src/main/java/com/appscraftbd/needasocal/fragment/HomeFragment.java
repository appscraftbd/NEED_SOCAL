package com.appscraftbd.needasocal.fragment;

import static androidx.swiperefreshlayout.widget.SwipeRefreshLayout.*;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.appscraftbd.needasocal.Recycle_view;
import com.appscraftbd.needasocal.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;


public class HomeFragment extends Fragment {


    RecyclerView recyclerView;
     public static SwipeRefreshLayout swipeRefreshLayout;
     ImageView uploard_post;
     BottomSheetDialog bottomSheetDialog;



    @SuppressLint({"ResourceAsColor", "MissingInflatedId"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_home, container, false);


        uploard_post = view.findViewById(R.id.uploard_post);

        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.white));
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(R.color.black);


        uploard_post.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                setBottomSheetDialog();


            }
        });


        recyclerView = view.findViewById(R.id.recyclehome);

        Recycle_view homeRecycle = new Recycle_view();
        homeRecycle.recycle_work(getContext(),recyclerView);

        return view;

    }
    //////////////////////
    private void setBottomSheetDialog(){

        bottomSheetDialog = new BottomSheetDialog(getContext());
        View view1 = LayoutInflater.from(getContext()).inflate(R.layout.new_post,
                null);
        bottomSheetDialog.setContentView(view1);


        bottomSheetDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.show();

        TextView cancel = bottomSheetDialog.findViewById(R.id.cancel_post);
        cancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                bottomSheetDialog.dismiss();

            }
        });

    }
}