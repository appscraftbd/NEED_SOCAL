package com.appscraftbd.needasocal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Login_from extends AppCompatActivity {

    TextView signup_btn,login_btn,forgot_btn;
    EditText username,password;
    String suid,spass;
    LottieAnimationView lottieAnimationView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_from);

        signup_btn = findViewById(R.id.signup_btn);
        login_btn = findViewById(R.id.login_btn);
        forgot_btn = findViewById(R.id.forgot_btn);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        lottieAnimationView = findViewById(R.id.loading);





        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lottieAnimationView.setVisibility(View.VISIBLE);

                suid = username.getText().toString();
                //////
                username_query(suid);
            }
        });

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent signup_class = new Intent(Login_from.this,Signup_from.class);
                startActivity(signup_class);
            }
        });
        forgot_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(Login_from.this)
                        .setMessage("it's working next update")
                        .show();

            }
        });



    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onBackPressed() {
//        super.onBackPressed();

        Toast.makeText(Login_from.this,"exit",Toast.LENGTH_SHORT).show();
        finishAffinity();
    }
    ////////////////////////////////////
    public void username_query(String uid ){
        String url = "https://mdnahidhossen.com/need/username_query.php?uid="+uid;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        int nuber = Integer.parseInt(response);
                        if(nuber>0){
                            suid = username.getText().toString();
                            spass = password.getText().toString();
                            //////
                            username_query(suid,spass);

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

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }
    //////////////////////////////////////
    public void username_query(String uid ,String pass){
        String url = "https://mdnahidhossen.com/need/user_chack.php?uid="+uid+"&pass="+pass;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        int nuber = Integer.parseInt(response);
                        if(nuber>0){
                            suid = username.getText().toString();
                            //////
                            lottieAnimationView.setVisibility(View.GONE);
                            MainActivity.user_id=suid;
                            Intent main_class = new Intent(Login_from.this,MainActivity.class);
                            startActivity(main_class);


                        }else {
                            lottieAnimationView.setVisibility(View.GONE);
                            password.setError("Invalid Password");
                        }



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }
}