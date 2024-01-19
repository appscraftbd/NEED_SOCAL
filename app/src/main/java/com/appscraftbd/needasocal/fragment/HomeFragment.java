package com.appscraftbd.needasocal.fragment;

import static androidx.swiperefreshlayout.widget.SwipeRefreshLayout.*;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.appscraftbd.needasocal.Api_Call_Home;
import com.appscraftbd.needasocal.RECYCLER_VIEW.Recycle_view_loading;
import com.appscraftbd.needasocal.R;
import com.appscraftbd.needasocal.RECYCLER_VIEW.Recycleview_item;
import com.appscraftbd.needasocal.SQLite_data.SQL_LITE;
import com.appscraftbd.needasocal.SQLite_data.User_InfoSQLite;
import com.appscraftbd.needasocal.TimeAndDate;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class HomeFragment extends Fragment {


    RecyclerView recyclerView;
     public static SwipeRefreshLayout swipeRefreshLayout;
     ImageView uploard_post;
     BottomSheetDialog bottomSheetDialog;

    String username, first_name, last_name,full_name, profile_pic ,password;
    String post_date , post_time , post_like , post_comment , post_share , profile_mode;
    Dialog dialog;
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

                setBottomSheetDialog();


            }
        });


        recyclerView = view.findViewById(R.id.recyclehome);

        Recycle_view_loading homeRecycle = new Recycle_view_loading();
        homeRecycle.recycle_work(getContext(),recyclerView);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the new activity

                Cursor cursor1 = sqlLite.data_result();

                while (cursor1.moveToNext()){
                    String user = cursor1.getString(1);
                    String pass = cursor1.getString(2);

                    String url = "https://mdnahidhossen.com/need/getUpoloadPost.php?uid="+user+"&pass="+pass;
                    Api_Call_Home apiCall = new Api_Call_Home(url,getContext(),recyclerView,swipeRefreshLayout);
                    break;
                }


            }
        }, 1500);


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
        TextView post_submit = bottomSheetDialog.findViewById(R.id.post_submit);
        EditText post_body = bottomSheetDialog.findViewById(R.id.post_body);
        TextView user = bottomSheetDialog.findViewById(R.id.username);

        full_name = first_name+" "+last_name;
        user.setText(""+full_name);

        cancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                bottomSheetDialog.dismiss();

            }
        });

        post_submit.setOnClickListener( view -> {

            bottomSheetDialog.dismiss();
            String body = post_body.getText().toString();
            getANewPost(body);

        });

    }

    private void getANewPost( String post_text){

//        try {
//            post_text = URLEncoder.encode(post_text,"UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            throw new RuntimeException(e);
//        }


        ///////
        dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.loading_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.show();

        ////time_and_date
        TimeAndDate timeAndDate = new TimeAndDate();
        post_time = timeAndDate.time();
        post_date = timeAndDate.date();



         full_name = first_name+" "+last_name;

        String url = "https://mdnahidhossen.com/need/setUploadPost.php?"+"uid="+username+" &pass="+password+"&Name="+full_name+"&Profile_pic="+profile_pic+"&Post_text="+post_text+"&Post_time="+post_time+"&Post_date="+post_date+"&Like_count="+post_like+ "&Comment_count="+post_comment+"&Share_count="+post_share+"&Profile_mode=none";


        String finalPost_text = post_text;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Handle the response from the server
                        dialog.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle the error
                    }
                })

        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Post_text", finalPost_text);
                return params;
            }
        };



        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(stringRequest);


    }


}