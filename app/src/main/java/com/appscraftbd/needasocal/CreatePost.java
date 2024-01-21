package com.appscraftbd.needasocal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
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
import com.appscraftbd.needasocal.fragment.HomeFragment;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class CreatePost extends AppCompatActivity {


    TextView post_submit,username,textcount;
    ImageView back;
    EditText post_TEXT;
    public static String name="";
    public static String user="";
    public static String pass="";
    public static String pic="";
    String sin;
    int lenthcount;
    String post_body,post_time,post_date,post_like,post_comment,post_share;
    Dialog dialog;



    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        username = findViewById(R.id.username);
        username.setText(""+name);
        textcount = findViewById(R.id.textcount);
        post_TEXT = findViewById(R.id.post_body);
        post_submit = findViewById(R.id.post_submit);


        post_TEXT.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                sin = post_TEXT.getText().toString();
                lenthcount = sin.length();

                if(lenthcount > 2000){
                    post_TEXT.setTextColor(ContextCompat.getColor(CreatePost.this, R.color.Red));
                    textcount.setText(""+lenthcount);

                }else {
                    post_TEXT.setTextColor(ContextCompat.getColor(CreatePost.this, R.color.textcolor));
                    textcount.setText(""+lenthcount);
                }



            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        back = findViewById(R.id.nopost);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });


        post_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                post_body = post_TEXT.getText().toString();

                if (post_TEXT.length()>0){
                    if(lenthcount<=2000 ){
                        getANewPost(post_body);
                    }
                }


            }
        });


    }
    public void getANewPost(String new_post){

        try {
            new_post = URLEncoder.encode(new_post,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }


        ///////
        dialog = new Dialog(CreatePost.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.loading_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.show();

        ////time_and_date
        TimeAndDate timeAndDate = new TimeAndDate();
        post_time = timeAndDate.time();
        post_date = timeAndDate.date();



        String full_name = name;

        String url = "https://mdnahidhossen.com/need/setUploadPost.php?"+"uid="+user+" &pass="+pass+"&Name="+full_name+"&Profile_pic="+pic+"&Post_time="+post_time+"&Post_date="+post_date+"&Like_count="+post_like+ "&Comment_count="+post_comment+"&Share_count="+post_share+"&Profile_mode=none";

        String finalPost_text = new_post;


        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Handle the response from the server
                        dialog.dismiss();
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle the error
                        dialog.dismiss();
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



        RequestQueue queue = Volley.newRequestQueue(CreatePost.this);
        queue.add(stringRequest);


    }

}