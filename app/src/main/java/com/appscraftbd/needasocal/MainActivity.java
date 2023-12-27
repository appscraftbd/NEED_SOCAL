package com.appscraftbd.needasocal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import com.appscraftbd.needasocal.Login.Login_from;
import com.appscraftbd.needasocal.fragment.AboutFragment;
import com.appscraftbd.needasocal.fragment.GlobalFragment;
import com.appscraftbd.needasocal.fragment.home_fragment.HomeFragment;
import com.appscraftbd.needasocal.fragment.NotificationFragment;
import com.appscraftbd.needasocal.fragment.ProfileFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {


    String user, pass;
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    int tab_count = 0;
    int tab_possition = 0;

    public static int open = 0;


    @SuppressLint("MissingInflatedId")
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

        viewPager2 = findViewById(R.id.viewpage);
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
                            viewPager2.setCurrentItem(tab.getPosition());

                        } else if (tab.getPosition()==1) {
                            tab_count = 0;
                            tab_possition = 2;
                            viewPager2.setCurrentItem(tab.getPosition());

                        } else if (tab.getPosition()==2) {
                            tab_count = 0;
                            tab_possition = 3;
                            viewPager2.setCurrentItem(tab.getPosition());

                        } else if (tab.getPosition()==3) {
                            tab_count = 0;
                            tab_possition = 4;
                            viewPager2.setCurrentItem(tab.getPosition());

                        } else if (tab.getPosition()==4) {
                            tab_count = 0;
                            tab_possition = 5;
                            viewPager2.setCurrentItem(tab.getPosition());
                        }

                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                        if (tab.getPosition()==0){
                            if(tab_count >2){
                                HomeFragment homeFragment = new HomeFragment();
//                                homeFragment.homeRefreshing();
                            }
                            tab_count = tab_count+2;
                        }else if (tab.getPosition()==1) {
                            if(tab_count >2){
                                Toast.makeText(MainActivity.this,"global",Toast.LENGTH_SHORT).show();
                            }
                            tab_count = tab_count+2;
                        }


                    }
                });




                viewPager2.setAdapter(new FragmentStateAdapter(MainActivity.this) {
                    @NonNull
                    @Override
                    public Fragment createFragment(int position) {


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
                    public int getItemCount() {
                        return 5;
                    }

                });

                viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                    @Override
                    public void onPageSelected(int position) {
                        super.onPageSelected(position);
                        tabLayout.getTabAt(position).select();

                    }
                });



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
            viewPager2.setCurrentItem(0);
        }else {
            if(open>0){
                super.onBackPressed();
                finishAffinity();
                finish();

            }
        }
    }
}
