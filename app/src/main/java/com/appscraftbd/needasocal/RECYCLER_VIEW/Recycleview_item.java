package com.appscraftbd.needasocal.RECYCLER_VIEW;

import static android.graphics.Color.parseColor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.appscraftbd.needasocal.Api_Call_Home;
import com.appscraftbd.needasocal.R;
import com.appscraftbd.needasocal.READMORE;
import com.appscraftbd.needasocal.SQLite_data.SQL_LITE;
import com.appscraftbd.needasocal.TimeAndDate;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

public class Recycleview_item {

    Context context;
    RecyclerView recyclerView ;

    String user,pass;
    public static  HashMap <String,String> hashMap ;
    String nextday;
    long ntime,ptime;
    ArrayList <HashMap <String,String>> arrayList =new ArrayList<>();
    public Recycleview_item(Context context ,ArrayList arrayList, RecyclerView recyclerView1 ){
        this.context = context;
        this.arrayList = arrayList;
        this.recyclerView = recyclerView1;





        MyAdapter myAdapter = new MyAdapter();
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));





    }

    public class MyAdapter extends RecyclerView.Adapter{
        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View myView = LayoutInflater.from(context).inflate(R.layout.post_layout,parent,false);
            return new Post_holder(myView);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            Post_holder myviewholder = (Post_holder) holder;

            hashMap = arrayList.get(position);

            String sid = hashMap.get("Id");
            String sname= ""+hashMap.get("Name");
            String spost_text = ""+hashMap.get("Post_text");
            String stime = ""+hashMap.get("Post_time");
            String sdate = ""+hashMap.get("Post_date");
            String slink_count = ""+hashMap.get("Like_count");
            String scomment_count = ""+hashMap.get("Comment_count");
            String sshare_count = ""+hashMap.get("Share_count");

            try {
                spost_text = URLDecoder.decode(spost_text,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }

            ptime = Long.parseLong((stime));



            TimeAndDate timeAndDate = new TimeAndDate();
            String nowtime = timeAndDate.time();

            ntime = Long.parseLong(nowtime);

            String nowDate = timeAndDate.date();
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                 nextday = timeAndDate.today(sdate);
            }

            if (nowDate.equals(sdate)){
                long iTime = Integer.parseInt(stime);
                long now = Integer.parseInt(nowtime);
                long post_time = now-iTime;
                String time =  timeAndDate.formatTime(post_time);

                myviewholder.date.setText(""+time);


            } else if (nowDate.equals(nextday)) {
                if (ntime > ptime){

                    myviewholder.date.setText(""+sdate);

                }else {
                    long new_time = 86400-ptime;
                    new_time = new_time+ntime;
                    String time =  timeAndDate.formatTime(new_time);
                    myviewholder.date.setText(""+time);
                }


            } else {
                myviewholder.date.setText(""+sdate);
            }

            READMORE readmore = new READMORE();
            readmore.Readmore(context,spost_text,myviewholder.postText,myviewholder.tvReadMore);

            myviewholder.fname.setText(""+sname);
//            myviewholder.postText.setText(""+spost_text);
//            myviewholder.likeTv.setText(""+slink_count);
//            myviewholder.commentTv.setText(""+scomment_count);
//            myviewholder.shareTV.setText(""+sshare_count);

            myviewholder.like_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    myviewholder.like_btn.setBackgroundResource(R.drawable.action_item_blue);
                    myviewholder.liketext.setTextColor(parseColor("#F10099CC"));
                    myviewholder.likeic.setImageResource(R.drawable.ic_unlike2);

                }
            });




        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        public class Post_holder extends RecyclerView.ViewHolder{

            TextView fname,postText,liketext,commentTv,shareTV,date,tvReadMore;
            LinearLayout like_btn;
            ImageView likeic;

            @SuppressLint("UseCompatLoadingForDrawables")
            public Post_holder(@NonNull View itemView) {
                super(itemView);

                fname = itemView.findViewById(R.id.full_name);
                postText = itemView.findViewById(R.id.post_text);
                date = itemView.findViewById(R.id.date);
                liketext = itemView.findViewById(R.id.liketext);
                commentTv = itemView.findViewById(R.id.comment_tv);
                shareTV = itemView.findViewById(R.id.share_tv);
                tvReadMore = itemView.findViewById(R.id.tvReadMore);

                like_btn = itemView.findViewById(R.id.likeButton);
                likeic = itemView.findViewById(R.id.likeic);



            }
        }
    }
}
