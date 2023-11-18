package com.appscraftbd.needasocal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Login_from extends AppCompatActivity {

    TextView signup_btn,login_btn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_from);

        signup_btn = findViewById(R.id.signup_btn);
        login_btn = findViewById(R.id.login_btn);




        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent signup_class = new Intent(Login_from.this,MainActivity.class);
                startActivity(signup_class);
            }
        });

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent signup_class = new Intent(Login_from.this,Signup_from.class);
                startActivity(signup_class);
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
}