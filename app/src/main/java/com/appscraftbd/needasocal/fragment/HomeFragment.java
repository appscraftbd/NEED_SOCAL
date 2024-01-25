package com.appscraftbd.needasocal.fragment;

import static androidx.swiperefreshlayout.widget.SwipeRefreshLayout.*;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.appscraftbd.needasocal.Api_Call_Home;
import com.appscraftbd.needasocal.CreatePost;
import com.appscraftbd.needasocal.RECYCLER_VIEW.Recycle_view_loading;
import com.appscraftbd.needasocal.R;
import com.appscraftbd.needasocal.SQLite_data.SQL_LITE;
import com.appscraftbd.needasocal.SQLite_data.User_InfoSQLite;
import com.google.android.material.bottomsheet.BottomSheetDialog;


public class HomeFragment extends Fragment {


    public static RecyclerView recyclerView;
     public static SwipeRefreshLayout swipeRefreshLayout;
     ImageView uploard_post;

    String username, first_name, last_name,full_name, profile_pic ,password;

    SQL_LITE sqlLite;


    @SuppressLint({"ResourceAsColor", "MissingInflatedId"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_home, container, false);


        uploard_post = view.findViewById(R.id.uploard_post);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.white));
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(R.color.black);

        sqlLite = new SQL_LITE(getContext());
        Cursor cursor1 = sqlLite.data_result();

        while (cursor1.moveToNext()){
            password = cursor1.getString(2);
        }



        User_InfoSQLite userInfoSQLite = new User_InfoSQLite(getContext());
        Cursor cursor = userInfoSQLite.getShowData();
        while (cursor.moveToNext()){
            username = cursor.getString(1);
            first_name = cursor.getString(2);
            last_name = cursor.getString(3);
            profile_pic = cursor.getString(4);

        }

        recyclerView = view.findViewById(R.id.recyclehome);
        Recycle_view_loading globalRecycleView = new Recycle_view_loading();
        globalRecycleView.recycle_work(getContext(),recyclerView);
        getRecycleCall();


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Handle the refresh action (e.g., fetch new data from a server)

                Cursor cursor1 = sqlLite.data_result();

                while (cursor1.moveToNext()){
                    String pass = cursor1.getString(2);

                    String url = "https://mdnahidhossen.com/need/getUpoloadPost.php?uid="+username+"&pass="+pass;
                    Api_Call_Home apiCall = new Api_Call_Home(url,getContext(),recyclerView,swipeRefreshLayout);
                    break;
                }
            }
        });


        uploard_post.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                full_name = first_name+" "+last_name;
                CreatePost.name=""+full_name;
                CreatePost.user=""+username;
                CreatePost.pass=""+password;
                CreatePost.pic=""+profile_pic;
                startActivity(new Intent(getContext(), CreatePost.class));
//                setBottomSheetDialog();

            }
        });


//        recyclerView = view.findViewById(R.id.recyclehome);
//        Recycle_view_loading globalRecycleView = new Recycle_view_loading();
//        globalRecycleView.recycle_work(getContext(),recyclerView);
//        getRecycleCall();




        return view;

    }



    //////////////////////


    public void getRecycleCall(){
        sqlLite = new SQL_LITE(getContext());
        Cursor cursor1 = sqlLite.data_result();

        while (cursor1.moveToNext()){
            String user = cursor1.getString(1);
            String pass = cursor1.getString(2);

            String url = "https://mdnahidhossen.com/need/getUpoloadPost.php?uid="+user+"&pass="+pass;
            Api_Call_Home apiCall = new Api_Call_Home(url,getContext(),recyclerView,swipeRefreshLayout);
            break;
        }

    }




}