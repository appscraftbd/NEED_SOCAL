package com.appscraftbd.needasocal;

import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.appscraftbd.needasocal.RECYCLER_VIEW.Recycleview_item;
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
    ArrayList <HashMap <String,String>> arrayList = new ArrayList<>();

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


                                hashMap = new HashMap<>();
                                hashMap.put("Id",Id);
                                hashMap.put("Name",name);
                                hashMap.put("Profile_pic",Profile_pic);
                                hashMap.put("Post_text",Post_text);
                                hashMap.put("Post_time",Post_time);
                                hashMap.put("Post_date",Post_date);
                                hashMap.put("Like_count",Like_count);
                                hashMap.put("Comment_count",Comment_count);
                                hashMap.put("Share_count",Share_count);
                                hashMap.put("Profile_mode",Profile_mode);
                                arrayList.add(hashMap);


                            }



                        } catch (JSONException e) {
                            new RuntimeException(e);

                        }

                        Recycleview_item recycleviewItem = new Recycleview_item(context,arrayList,recyclerView);
//                        Recycleview_item.hashMap=hashMap;
//                        Recycleview_item.arrayList=arrayList;
                        swipeRefreshLayout.setRefreshing(false);




                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(stringRequest);


    }
    public ArrayList getArray(){
        return arrayList ;
    }

    public HashMap getHashmap(){
        return hashMap;
    }
}
