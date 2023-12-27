package com.appscraftbd.needasocal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class App_opening_layout extends AppCompatActivity {

    ImageView imageView,imageView1,imageView2,imageView3;
    Animation animation,animation1,animation2,animation3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening_layout);

        imageView = findViewById(R.id.need_n);
        imageView1 = findViewById(R.id.need_e);
        imageView2 = findViewById(R.id.need_e2);
        imageView3 = findViewById(R.id.need_d);

        animation = AnimationUtils.loadAnimation(App_opening_layout.this,R.anim.left_to_right);
        animation1 = AnimationUtils.loadAnimation(App_opening_layout.this,R.anim.up_from_bottom);
        animation2 = AnimationUtils.loadAnimation(App_opening_layout.this,R.anim.bottom_to_up);
        animation3 = AnimationUtils.loadAnimation(App_opening_layout.this,R.anim.right_to_left);

        imageView.startAnimation(animation);
        imageView1.startAnimation(animation1);
        imageView2.startAnimation(animation2);
        imageView3.startAnimation(animation3);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the new activity

                Intent intent = new Intent(App_opening_layout.this, MainActivity.class);
                startActivity(intent);
                MainActivity.open=12;
            }
        }, 2000);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}