package com.appscraftbd.needasocal.fragment.home_fragment;

import static androidx.swiperefreshlayout.widget.SwipeRefreshLayout.*;

import static com.appscraftbd.needasocal.R.drawable.*;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
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
import android.view.ViewTreeObserver;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.appscraftbd.needasocal.CodeIdentifyANDaction;
import com.appscraftbd.needasocal.PostEdit;
import com.appscraftbd.needasocal.R;
import com.appscraftbd.needasocal.READMORE;


public class HomeFragment extends Fragment {


    RecyclerView recyclerView;
     public static SwipeRefreshLayout swipeRefreshLayout;
     ImageView uploard_post;



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
                Intent intent = new Intent(getContext(), PostEdit.class);
                startActivity(intent);

            }
        });


        recyclerView = view.findViewById(R.id.recyclehome);

        Home_Recycle homeRecycle = new Home_Recycle();
        homeRecycle.recycle_work(getContext(),recyclerView);

        return view;

    }
}