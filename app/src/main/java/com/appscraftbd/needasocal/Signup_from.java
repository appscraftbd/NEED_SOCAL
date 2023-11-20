package com.appscraftbd.needasocal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Signup_from extends AppCompatActivity {

    TextView create_btn;
    EditText username_id;
    EditText username,first_name,last_name,birthday,birthmonth,birthyear,mobile,password,confirmpassword;
    String spassword,sconfirm_password,uid,sfirst_name,slast_name,sbirthday,sbirthmonth,sbirthyear,smobile;
    private int QUERY_NUMBER = 0;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_from);

        create_btn = findViewById(R.id.create_btn);
        username_id = findViewById(R.id.username_id);

        username = findViewById(R.id.username);
        first_name = findViewById(R.id.first_name);
        last_name = findViewById(R.id.last_name);
        birthday = findViewById(R.id.birth_day);
        birthmonth = findViewById(R.id.birth_month);
        birthyear = findViewById(R.id.birth_year);
        mobile = findViewById(R.id.mobile_number);
        password = findViewById(R.id.register_password);
        confirmpassword = findViewById(R.id.confirm_password);


        create_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                uid = username_id.getText().toString();
                sfirst_name = first_name.getText().toString();
                slast_name = last_name.getText().toString();
                sbirthday = birthday.getText().toString();
                sbirthmonth = birthmonth.getText().toString();
                sbirthyear = birthyear.getText().toString();
                smobile = mobile.getText().toString();
                spassword = password.getText().toString();
                sconfirm_password = confirmpassword.getText().toString();


                String date_of_birth = sbirthday+"/"+sbirthmonth+"/"+sbirthyear;


                if(uid.length()==0){
                    username_id.setError("username");
                } else if (sfirst_name.length()==0) {
                    first_name.setError("firstname");
                }else if (slast_name.length()==0) {
                    last_name.setError("lastname");
                }else if (sbirthday.length()==0) {
                    birthday.setError("birthday");
                }else if (sbirthmonth.length()==0) {
                    birthmonth.setError("birthmonth");
                }else if (sbirthyear.length()<4) {
                    birthyear.setError("birthyear");
                }else if (smobile.length()<11) {
                    mobile.setError("mobile");
                }else if (spassword.length()==0) {
                    password.setError("password");
                } else if (sconfirm_password.length()==0) {
                    confirmpassword.setError("confirm password");

                } else if (date_of_birth.length()<0) {
                    Toast.makeText(Signup_from.this,"date of birth",Toast.LENGTH_SHORT).show();
                }else {



                if(spassword.contains(sconfirm_password)){
//                    Toast.makeText(Signup_from.this,"password",Toast.LENGTH_SHORT).show();
                    if(sconfirm_password.contains(spassword)){
                        //////////////////////////////////////
                        //////////////////work/////////////
                        uid = username_id.getText().toString();
                        if (uid.length()>0){
                            username_query(uid);
                        }else {
                            username_id.setError("Enter UserName");
                        }

                    }else {
                        password.setText("");
                        confirmpassword.setText("");
                        password.setError("No Match");
                        confirmpassword.setError("No Match");
                    }
                }else {
                    password.setText("");
                    confirmpassword.setText("");
                    password.setError("No Match");
                    confirmpassword.setError("No Match");
                }




                }





            }
        });





    }
    ////.....................................................................................................................................
    public void username_query(String uid ){
        String url = "https://mdnahidhossen.com/need/username_query.php?uid="+uid;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        int nuber = Integer.parseInt(response);
                        if(nuber>0){
                            username_id.setText("");
                            username_id.setError("Already Used");
                            AlertDialog alertDialog = new AlertDialog.Builder(Signup_from.this)
                                    .setTitle("NEED")
                                    .setMessage("This Username already uesd!")
                                    .show();

                        }else {
                            ////////////
                            user_info_add();


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
    /////////////////////////////////////////////////
    public void user_info_add(){
        uid = username_id.getText().toString();
        sfirst_name = first_name.getText().toString();
        slast_name = last_name.getText().toString();
        sbirthday = birthday.getText().toString();
        sbirthmonth = birthmonth.getText().toString();
        sbirthyear = birthyear.getText().toString();
        smobile = mobile.getText().toString();
        spassword = password.getText().toString();

        String date_of_birth = sbirthday+"/"+sbirthmonth+"/"+sbirthyear;


        if(uid.length()==0){
            username_id.setError("username");
        } else if (sfirst_name.length()==0) {
            first_name.setError("firstname");
        }else if (slast_name.length()==0) {
            last_name.setError("lastname");
        }else if (sbirthday.length()==0) {
            birthday.setError("birthday");
        }else if (sbirthmonth.length()==0) {
            birthmonth.setError("birthmonth");
        }else if (sbirthyear.length()<4) {
            birthyear.setError("birthyear");
        }else if (smobile.length()<11) {
            mobile.setError("mobile");
        }else if (spassword.length()==0) {
            password.setError("password");
        } else if (sconfirm_password.length()==0) {
            confirmpassword.setError("confirm password");

        } else if (date_of_birth.length()<0) {
            Toast.makeText(Signup_from.this,"date of birth",Toast.LENGTH_SHORT).show();
        }else {

        String url = "https://mdnahidhossen.com/need/user_info.php?Username="+uid+" &First_name="+sfirst_name+" &Last_name="+slast_name+"" +
                " &Birthday="+date_of_birth+" &moblie_number="+smobile+" &Password_text="+spassword;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        new AlertDialog.Builder(Signup_from.this)
                                .setTitle("NEED")
                                .setMessage("Your Account create Successfully")
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Intent getbeforepage = new Intent(Signup_from.this, Login_from.class);
                                        startActivity(getbeforepage);
                                    }
                                })
                                .setCancelable(false)
                                .show();


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



}