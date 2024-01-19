package com.appscraftbd.needasocal.Login;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.EditText;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.appscraftbd.needasocal.MainActivity;
import com.appscraftbd.needasocal.SQLite_data.SQL_LITE;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Login_api {

    Context context;
    EditText username,password;
    LottieAnimationView lottieAnimationView;
    Intent intent;
    public Login_api(Context context, EditText username, EditText password,LottieAnimationView lottieAnimationView,Intent intent){

        this.context = context;
        this.username = username;
        this.password = password;
        this.lottieAnimationView = lottieAnimationView;
        this.intent = intent;

        String suid = username.getText().toString();
        String spass = password.getText().toString();

        try {
            spass = URLEncoder.encode(spass,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }


        user_find_out(suid,spass);
    }
    private void user_find_out(String uid ,String pass){
        String url = "https://mdnahidhossen.com/need/username_query.php?uid="+uid;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        int nuber = Integer.parseInt(response);
                        //if username found then response value 0 up number
                        if(nuber>0){
                            //if response value = 0 up number then user password chack
                            user_chack_pass(uid,pass);
                        }else {
                            lottieAnimationView.setVisibility(View.GONE);
                            username.setError("Invalid username");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(stringRequest);
    }


    //////////////////////////////////////
    private void user_chack_pass(String uid ,String pass){
        String url = "https://mdnahidhossen.com/need/user_chack.php?uid="+uid+"&pass="+pass;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        int nuber = Integer.parseInt(response);
                        //if password is currect then responce value 0 up number
                        if(nuber>0){
                            /*if responce value 0 up number then insert data in SqlLite DataBase
                            and go LooginFrom --> MainActivity
                             */
                            lottieAnimationView.setVisibility(View.GONE);

                            SQL_LITE sqlLite = new SQL_LITE(context);
                            Cursor result = sqlLite.data_result();

                            try {
                                if(result.getCount() ==0) {
                                    long rowid = sqlLite.data_insert(uid,pass);

                                    // collecte the intent in LoginFrom.class
                                    startActivity(context,intent,null);
                                    MainActivity.open=0;

                                }else {
                                    sqlLite.data_update(uid,pass);
                                    // collecte the intent in LoginFrom.class
                                    startActivity(context,intent,null);
                                    MainActivity.open=0;

                                }
                            }catch (Exception e){

                            }

                        }
                        else {
                            lottieAnimationView.setVisibility(View.GONE);
                            password.setError("Invalid Password");
                        }



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(stringRequest);
    }

}
