package com.appscraftbd.needasocal;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.appscraftbd.needasocal.Login.Login_from;
import com.appscraftbd.needasocal.SQLite_data.SQL_LITE;
import com.appscraftbd.needasocal.SQLite_data.User_InfoSQLite;
import com.appscraftbd.needasocal.fragment.HomeFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class App_opening_layout extends AppCompatActivity {

    ImageView imageView,imageView1,imageView2,imageView3;
    Animation animation,animation1,animation2,animation3;

    String user,pass;

    String user_id,First_name,Last_name,Profile_pic,Bio_info,Education_info,Work_info,Link;
    int Sqlite_id;

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


                SQL_LITE sqlLite = new SQL_LITE(App_opening_layout.this);
                Cursor cursor = sqlLite.data_result();

                while (cursor.moveToNext()){
                    String userName = cursor.getString(1);
                    String password = cursor.getString(2);

                    user = userName;
                    pass = password;

                    break;

                }



                chackInfo_and_getData(user,pass);




            }
        }, 1500);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }

    public void chackInfo_and_getData(String user,String pass){


        String url = "https://mdnahidhossen.com/need/getuserinfo.php?uid="+user+"&pass="+pass;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response.equals("fales")){

                            SQL_LITE sqlLite = new SQL_LITE(App_opening_layout.this);
                            sqlLite.data_delete();
                            Intent sign = new Intent(App_opening_layout.this, Login_from.class);
                            startActivity(sign);

                        }else {
                            ////////////////////////////////////////////
                            setData(response);

                            //////////////////////////////////////////////

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {



            }
        });

        RequestQueue queue = Volley.newRequestQueue(App_opening_layout.this);
        queue.add(stringRequest);

    }

    public void setData(String response){


        try {
            JSONArray jsonArray = new JSONArray(response);
            for(int i=0; i<jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                user_id = jsonObject.getString("user_id");
                First_name = jsonObject.getString("First_name");
                Last_name = jsonObject.getString("Last_name");
                Profile_pic = jsonObject.getString("Profile_pic");
                Bio_info = jsonObject.getString("Bio_info");
                Education_info = jsonObject.getString("Education_info");
                Work_info = jsonObject.getString("Work_info");
                Link = jsonObject.getString("Link");
            }

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        User_InfoSQLite userInfoSQLite = new User_InfoSQLite(App_opening_layout.this);
        Cursor cursor = userInfoSQLite.getShowData();

        while (cursor.moveToNext()){
            Sqlite_id = cursor.getInt(0);
        }

        if(cursor.getCount()<1){

            userInfoSQLite.getDataInsert(""+user_id,""+First_name,""+Last_name,
                    ""+Profile_pic,""+Bio_info,""+Education_info,""+Work_info
                    ,""+Link);

        }else {

            userInfoSQLite.getDataDelete(""+Sqlite_id);
            userInfoSQLite.getDataInsert(""+user_id,""+First_name,""+Last_name,
                    ""+Profile_pic,""+Bio_info,""+Education_info,""+Work_info
                    ,""+Link);

        }

        Intent intent = new Intent(App_opening_layout.this, MainActivity.class);
        startActivity(intent);
        MainActivity.open=12;
//        finish();
    }


}