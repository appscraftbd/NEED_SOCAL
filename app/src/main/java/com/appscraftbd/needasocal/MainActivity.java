package com.appscraftbd.needasocal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.splashscreen.SplashScreen;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;

import com.appscraftbd.needasocal.Login.Login_from;
import com.appscraftbd.needasocal.SQLite_data.SQL_LITE;
import com.appscraftbd.needasocal.fragment.AboutFragment;
import com.appscraftbd.needasocal.fragment.GlobalFragment;
import com.appscraftbd.needasocal.fragment.HomeFragment;
import com.appscraftbd.needasocal.fragment.NotificationFragment;
import com.appscraftbd.needasocal.fragment.ProfileFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {


    private String user, pass;
    TabLayout tabLayout;
    private   ViewPager viewPager;
    private int tab_count = 0;
    public  int tab_possition = 0;

    public static int open = 0;


    @SuppressLint({"MissingInflatedId", "ResourceType", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(open<1){
            Intent opening = new Intent(MainActivity.this, App_opening_layout.class);
            startActivity(opening);
        }


        ////android rotating off
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);



        viewPager = findViewById(R.id.viewpage);
        tabLayout = findViewById(R.id.tablayout);



        /////////MY sql lite database create
        SQL_LITE sqlLite = new SQL_LITE(MainActivity.this);
        SQLiteDatabase sqLiteDatabase = sqlLite.getWritableDatabase();


        ///////user search
        Cursor result = sqlLite.data_result();

        try {

            if (result.getCount() == 0) {
                Intent sign = new Intent(MainActivity.this, Login_from.class);
                startActivity(sign);
            } else {
                /// data read
                StringBuffer stringBuffer = new StringBuffer();
                StringBuffer stringBuffer1 = new StringBuffer();

                while (result.moveToNext()) {
                    stringBuffer.append(result.getString(1));
                    stringBuffer1.append(result.getString(2));
                    this.user = "" + stringBuffer;
                    this.pass = "" + stringBuffer1;
                    break;
                }
            }
        } catch (Exception e) {
        }

        try {


            ///////////////
            if (user.length() <= 3) {
                Intent sign = new Intent(MainActivity.this, Login_from.class);
                startActivity(sign);
            } else {

                ///////////////////

                tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        if (tab.getPosition() ==0){
                            tab_count = 0;
                            tab_possition = 1;
                            viewPager.setCurrentItem(tab.getPosition());

                        } else if (tab.getPosition()==1) {
                            tab_count = 0;
                            tab_possition = 2;
                            viewPager.setCurrentItem(tab.getPosition());

                        } else if (tab.getPosition()==2) {
                            tab_count = 0;
                            tab_possition = 3;
                            viewPager.setCurrentItem(tab.getPosition());

                        } else if (tab.getPosition()==3) {
                            tab_count = 0;
                            tab_possition = 4;
                            viewPager.setCurrentItem(tab.getPosition());

                        } else if (tab.getPosition()==4) {
                            tab_count = 0;
                            tab_possition = 5;
                            viewPager.setCurrentItem(tab.getPosition());
                        }
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
                });

                viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
                    @NonNull
                    @Override
                    public Fragment getItem(int position) {
                        switch (position){

                            case (0):
                                tab_count = 2;
                                tab_possition = 1;
                                return new HomeFragment();

                            case (1):

                                tab_count = 0;
                                tab_possition = 2;
                                return new GlobalFragment();

                            case (2):
                                tab_count = 0;
                                tab_possition = 3;
                                return new ProfileFragment();
                            case (3):

                                tab_count = 0;
                                tab_possition = 4;
                                return new NotificationFragment();
                            case (4):

                                tab_count = 0;
                                tab_possition = 5;
                                return new AboutFragment();

                            default:
                                tab_count = 2;
                                tab_possition = 1;
                                return new HomeFragment();

                        }
                    }

                    @Override
                    public int getCount() {
                        return 5;
                    }
                });



                viewPager.setOffscreenPageLimit(5);
                viewPager.setCurrentItem(0,false);
                viewPager.clearAnimation();
                tabLayout.setTabMode(TabLayout.MODE_FIXED);
                tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
                tabLayout.setupWithViewPager(viewPager);
                tabLayout.getTabAt(0).setIcon(R.drawable.home_icone);
                tabLayout.getTabAt(1).setIcon(R.drawable.global_icone);
                tabLayout.getTabAt(2).setIcon(R.drawable.profile_icone);
                tabLayout.getTabAt(3).setIcon(R.drawable.notification_icone);
                tabLayout.getTabAt(4).setIcon(R.drawable.more_icone);


                //////////////////
            }
        }catch (Exception e){

        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onBackPressed() {

        if(tab_possition >1){
            tab_count = 0;
            tab_count = tab_count + 4;
            viewPager.setCurrentItem(0);
        }else {
            if(open>0){
                super.onBackPressed();
                finishAffinity();
                finish();

            }
        }
    }

    public void slideUp(RelativeLayout view){
        TranslateAnimation animate = new TranslateAnimation(0, 0,
                view.getHeight(), 0);
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
        view.setVisibility(View.VISIBLE);
    }
    // slide the view from its current position to below itself
    public void slideDown(RelativeLayout view){
        TranslateAnimation animate = new TranslateAnimation(0, 0,
                0, view.getHeight());
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
        view.setVisibility(View.GONE);
    }

}
