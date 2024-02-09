package com.appscraftbd.needasocal.Adapter;

import static android.graphics.Color.parseColor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appscraftbd.needasocal.Model.GlobalPostModel;
import com.appscraftbd.needasocal.Model.HomePostModel;
import com.appscraftbd.needasocal.R;
import com.appscraftbd.needasocal.READMORE;
import com.appscraftbd.needasocal.TimeAndDate;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

public class HomePostAdapter extends RecyclerView.Adapter<HomePostAdapter.PostHolder> {

    Context context;
    ArrayList<HomePostModel> arrayList;

    String nextday;
    long ntime,ptime;

    public HomePostAdapter(Context context, ArrayList<HomePostModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    public class PostHolder extends RecyclerView.ViewHolder{

        TextView fname,postText,liketext,commentTv,shareTV,date,tvReadMore;
        LinearLayout like_btn;
        ImageView likeic;
        public PostHolder(@NonNull View itemView) {
            super(itemView);

            fname = itemView.findViewById(R.id.full_name);
            postText = itemView.findViewById(R.id.post_text);
            date = itemView.findViewById(R.id.date);
            liketext = itemView.findViewById(R.id.liketv);
            commentTv = itemView.findViewById(R.id.commenttv);
            shareTV = itemView.findViewById(R.id.sharetv);
            tvReadMore = itemView.findViewById(R.id.tvReadMore);
            like_btn = itemView.findViewById(R.id.likeButton);
            likeic = itemView.findViewById(R.id.likeic);

        }
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View myView = LayoutInflater.from(context).inflate(R.layout.post_layout,parent,false);
        return new PostHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {



        HomePostModel homePostModel = arrayList.get(position);

        String sid = homePostModel.getPost_id();
        String sname= homePostModel.getFull_name();
        String spost_text = homePostModel.getPost_body();
        String stime = homePostModel.getPost_time();
        String sdate = homePostModel.getPost_date();

        String slink_count = "";
        String scomment_count = "";
        String sshare_count = "";

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

            holder.date.setText(""+time);


        } else if (nowDate.equals(nextday)) {
            if (ntime > ptime){

                holder.date.setText(""+sdate);

            }else {
                long new_time = 86400-ptime;
                new_time = new_time+ntime;
                String time =  timeAndDate.formatTime(new_time);
                holder.date.setText(""+time);
            }

        } else {
            holder.date.setText(""+sdate);
        }

        READMORE readmore = new READMORE();
        readmore.Readmore(context,spost_text,holder.postText,holder.tvReadMore);

        holder.fname.setText(""+sname);

        holder.like_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                holder.like_btn.setBackgroundResource(R.drawable.action_item_blue);
                holder.liketext.setTextColor(parseColor("#F10099CC"));
                holder.likeic.setImageResource(R.drawable.ic_unlike2);

            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


}
