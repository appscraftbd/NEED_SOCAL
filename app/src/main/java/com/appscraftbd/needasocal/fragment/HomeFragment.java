package com.appscraftbd.needasocal.fragment;

import static androidx.swiperefreshlayout.widget.SwipeRefreshLayout.*;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.appscraftbd.needasocal.CodeIdentifyANDaction;
import com.appscraftbd.needasocal.PostEdit;
import com.appscraftbd.needasocal.R;


public class HomeFragment extends Fragment {


    public static Boolean boolean1 = true;

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
        // Set the background color for the progress circle
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
            return 6;
        }

        public class MyviewHolder extends RecyclerView.ViewHolder{

            TextView post_text;

            public MyviewHolder(@NonNull View itemView) {
                super(itemView);

                post_text = itemView.findViewById(R.id.post_text);
                ToggleButton likeButton = itemView.findViewById(R.id.likeButton);


                String text_text = "php get method not support spacal chatacter.\n" +
                        "And does not print this character.\n" +
                        "for example:\n" +
                        "!@#%^&*()_+}[:\",./<>?`~|\\\n" +
                        "how i can solve it?\n" +
                        " ---->\n try {\n" +
                        "            spass = URLEncoder.encode(spass,\"UTF-8\");\n" +
                        "        } catch (UnsupportedEncodingException e) {\n" +
                        "            throw new RuntimeException(e);\n" +
                        "        }\n---->";

                CodeIdentifyANDaction codeIdentifyANDaction = new CodeIdentifyANDaction();
                codeIdentifyANDaction.inANDout(getContext(),text_text,post_text);


                likeButton.setButtonDrawable(R.drawable.ic_like);
                likeButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            likeButton.setButtonDrawable(R.drawable.ic_unlike);

                        } else {
                            likeButton.setButtonDrawable(R.drawable.ic_like);


                        }
                    }
                });


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