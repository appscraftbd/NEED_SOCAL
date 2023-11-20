package com.appscraftbd.needasocal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    public static String user_id="";
    int onback_count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ////android rotating off
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        bottomNavigationView = findViewById(R.id.bottpm_navigation_id);



        ///////////////////////
        if(user_id.length()==0){
            Intent sign = new Intent(MainActivity.this, Login_from.class);
            startActivity(sign);
        }


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.framelayout, new HomeFragment());
        fragmentTransaction.commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.homeitem){

                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.add(R.id.framelayout, new HomeFragment());
                    fragmentTransaction.commit();


                } else if (item.getItemId() == R.id.globalitem) {

                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.add(R.id.framelayout, new GlobalFragment());
                    fragmentTransaction.commit();

                }else if (item.getItemId() == R.id.profileitem) {

                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.add(R.id.framelayout, new ProfileFragment());
                    fragmentTransaction.commit();

                }else if (item.getItemId() == R.id.notificationitem) {

                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.add(R.id.framelayout, new NotificationFragment());
                    fragmentTransaction.commit();

                }else {

                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.add(R.id.framelayout, new AboutFragment());
                    fragmentTransaction.commit();

                }

                return true;
            }
        });






    }
    ///////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onBackPressed() {
//        super.onBackPressed();

        onback_count = onback_count+1;
        if(onback_count==1){
            Toast.makeText(MainActivity.this,"exit",Toast.LENGTH_SHORT).show();
        }else {
            finishAffinity();
        }
    }
}