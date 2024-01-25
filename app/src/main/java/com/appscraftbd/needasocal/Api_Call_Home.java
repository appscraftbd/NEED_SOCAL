package com.appscraftbd.needasocal;

import android.content.Context;
import android.database.Cursor;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.appscraftbd.needasocal.Adapter.PostAdapter;
import com.appscraftbd.needasocal.Model.PostModel;
import com.appscraftbd.needasocal.SQLite_data.SQL_LITE;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Api_Call_Home {

    String DATA_INFO = "";
    Context context ;
    HashMap<String,String> hashMap ;
    ArrayList<PostModel> itemList = new ArrayList<>();

    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    public static String surl;
    public Api_Call_Home(String url, Context context, RecyclerView recyclerView, SwipeRefreshLayout swipeRefreshLayout){
        this.context=context;
        this.recyclerView = recyclerView;
        this.swipeRefreshLayout = swipeRefreshLayout;


        SQL_LITE sqlLite = new SQL_LITE(context);
        Cursor cursor1 = sqlLite.data_result();

        while (cursor1.moveToNext()){
            String user = cursor1.getString(1);
            String pass = cursor1.getString(2);

            surl = "https://mdnahidhossen.com/need/getUpoloadPost.php?uid="+user+"&pass="+pass;

            break;
        }


        StringRequest stringRequest = new StringRequest(Request.Method.GET, surl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            for (int i=0; i<jsonArray.length(); i++) {

                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                String Id = jsonObject.getString("Id");
                                String name = jsonObject.getString("Name");
                                String Profile_pic = jsonObject.getString("Profile_pic");
                                String Post_text = jsonObject.getString("Post_text");
                                String Post_time = jsonObject.getString("Post_time");
                                String Post_date = jsonObject.getString("Post_date");
                                String Like_count = jsonObject.getString("Like_count");
                                String Comment_count = jsonObject.getString("Comment_count");
                                String Share_count = jsonObject.getString("Share_count");
                                String Profile_mode = jsonObject.getString("Profile_mode");



                                itemList.add(new PostModel(Id,"",""+name,
                                        ""+Post_date,""+Post_time,""+Post_text));

                            }

                        } catch (JSONException e) {
                            new RuntimeException(e);

                        }

                        swipeRefreshLayout.setRefreshing(false);
                        PostAdapter postAdapter = new PostAdapter(context,itemList);
                        recyclerView.setAdapter(postAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(context));





                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(stringRequest);


    }

    public HashMap getHashmap(){
        return hashMap;
    }
}
