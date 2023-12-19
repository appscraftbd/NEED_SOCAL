package com.appscraftbd.needasocal.Login;

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
import com.appscraftbd.needasocal.MainActivity;
import com.appscraftbd.needasocal.R;
import com.appscraftbd.needasocal.Signup_from;

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
                suid = username.getText().toString();
                spass = password.getText().toString();

                if (suid.length()==0){
                    username.setError("Enter Username");
                }if (spass.length()==0){
                    password.setError("Enter Password");
                }else {

                    lottieAnimationView.setVisibility(View.VISIBLE);
//                    user_find_out(suid);
//                    username_query(suid);
                      Intent main_class = new Intent(Login_from.this, MainActivity.class);
                      Login_api loginApi = new Login_api(Login_from.this,username,password,lottieAnimationView,main_class);

                }

            }
        });

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent signup_class = new Intent(Login_from.this, Signup_from.class);
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
}