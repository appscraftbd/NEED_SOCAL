package com.appscraftbd.needasocal.RECYCLER_VIEW;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appscraftbd.needasocal.R;
import com.facebook.shimmer.ShimmerFrameLayout;


public class Recycle_view_loading {
    Context context;
    public void recycle_work( Context context,RecyclerView recyclerView){
        this.context = context;

        MyAdapter myAdapter = new MyAdapter();
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));

    }  public class MyAdapter extends RecyclerView.Adapter{


        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View myView = LayoutInflater.from(context).inflate(R.layout.post_loding,parent,false);
            return new PostHolder(myView);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 5;
        }

        class PostHolder extends RecyclerView.ViewHolder{
            public TextView post_text,readmore;
            ImageView likebtn;

            private ShimmerFrameLayout shimmerLayout;


            public PostHolder(@NonNull View itemView) {
                super(itemView);


                shimmerLayout = itemView.findViewById(R.id.shimmerLayout);
                // Start shimmer animation
                shimmerLayout.startShimmer();

//                post_text = itemView.findViewById(R.id.post_text);
//                readmore = itemView.findViewById(R.id.tvReadMore);
//
//
//                String text_text =
//                        "php get method not support spacal chatacter.\n" +
//                                "And does not print this character.\n" +
//                                "for example:\n" +
//                                "!@#%^&*()_+}[:\",./<>?`~|\\\n" +
//                                "how i can solve it?\n"+
//                                " ---->\n try {\n" +
//                                "            spass = URLEncoder.encode(spass,\"UTF-8\");\n" +
//                                "        } catch (UnsupportedEncodingException e) {\n" +
//                                "        }\n" +
//                                "        }\n"+
//                                "            throw new RuntimeException(e);\n" +
//                                "        }\n---->"
//                ;
//
//
//                post_text.setMaxLines(5);
//                READMORE readmore1 = new READMORE();
//                readmore1.Readmore(context,text_text,post_text,readmore);
//
//                likebtn = itemView.findViewById(R.id.likeButton);
//                likebtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        likebtn.setImageResource(R.drawable.ic_unlike2);
//
//                    }
//                });
//




            }

        }
    }

}
