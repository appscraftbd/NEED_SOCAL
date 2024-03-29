package com.appscraftbd.needasocal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.appscraftbd.needasocal.Login.Login_from;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Signup_from extends AppCompatActivity {

    LottieAnimationView lottieAnimationView;
    TextView create_btn;
    EditText username_id;
    EditText username,first_name,last_name,gmail,password,confirmpassword;
    TextView birthday,gender;
    String spassword,sconfirm_password,uid,sfirst_name,slast_name,sbirthday,sgender,sgmail;
    int otp_number;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private int QUERY_NUMBER = 0;
    Spinner spinner;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_from);

        create_btn = findViewById(R.id.create_btn);
        username_id = findViewById(R.id.username_id);

//        username = findViewById(R.id.username);
        first_name = findViewById(R.id.first_name);
        last_name = findViewById(R.id.last_name);
        birthday = findViewById(R.id.birth_day);
        gender = findViewById(R.id.gender);
        gmail = findViewById(R.id.gmail_id);
        password = findViewById(R.id.password);
        confirmpassword = findViewById(R.id.confirm_password);

        username_id.setHighlightColor(Color.parseColor("#2DB7BFCD"));
        first_name.setHighlightColor(Color.parseColor("#2DB7BFCD"));
        last_name.setHighlightColor(Color.parseColor("#2DB7BFCD"));
        birthday.setHighlightColor(Color.parseColor("#2DB7BFCD"));
        gender.setHighlightColor(Color.parseColor("#2DB7BFCD"));
        gmail.setHighlightColor(Color.parseColor("#2DB7BFCD"));
        password.setHighlightColor(Color.parseColor("#2DB7BFCD"));
        confirmpassword.setHighlightColor(Color.parseColor("#2DB7BFCD"));


        lottieAnimationView = findViewById(R.id.loading);


        spinner = findViewById(R.id.spinner1);
        String[] tgender = getResources().getStringArray(R.array.numbers);
        ArrayAdapter arrayAdapter = new ArrayAdapter<>(this,
                R.layout.spinner_view,R.id.spinnerText,tgender);

//        arrayAdapter.setDropDownViewResource(android.R.layout.sp);
        spinner.setAdapter(arrayAdapter);







        birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //////
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Signup_from.this,
                        R.style.DatePickerTheme,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                //////////

            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                birthday.setText(date);
            }
        };



        gender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        ///////////////////////////////////////////
        ////////user information push database/////
        ///////////////////////////////////////////
        create_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userinfo_push_database();
            }
        });



    }/////////////close oncreate method ...............
    ///////////////////////////////////////////////////////////


    public void userinfo_push_database(){

        uid = username_id.getText().toString();
        sfirst_name = first_name.getText().toString();
        slast_name = last_name.getText().toString();
        sbirthday = birthday.getText().toString();
        sgender = gender.getText().toString();
        sgmail = gmail.getText().toString();
        spassword = password.getText().toString();
        sconfirm_password = confirmpassword.getText().toString();

        try {
            spassword = URLEncoder.encode(spassword,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        try {
            sconfirm_password = URLEncoder.encode(sconfirm_password,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }



        ////EditText Null Chack////
        if(uid.length()==0){
            username_id.setError("username");
        } else if (uid.length()<5) {
            username_id.setError("Minimum 6 character");
        } else if (sfirst_name.length()==0) {
            first_name.setError("firstname");
        }else if (slast_name.length()==0) {
            last_name.setError("lastname");
        }else if (sbirthday.length()==0) {
            birthday.setText("Date of birth ?");
        }else if (sgmail.length()<11) {
            gmail.setError("gmail");
        }else if (spassword.length()==0) {
            password.setError("password");
        } else if (spassword.length()<5) {
            password.setError("Minimum 6 character");
        } else if (sconfirm_password.length()==0) {
            confirmpassword.setError("confirm password");
        } else if (sgmail.length()==0) {
            gmail.setError("gmail");
        }else {


            ///password confarmation
            if(spassword.contains(sconfirm_password)){
                if(sconfirm_password.contains(spassword)){
                    //////////////////////////////////////
                    uid = username_id.getText().toString();
                    if (uid.length()>0){

                        //////////////////////////
                        ////call username_query///
                        //////////////////////////
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

    public void username_query(String uid ){
        lottieAnimationView.setVisibility(View.VISIBLE);
        String url = "https://mdnahidhossen.com/need/username_query.php?uid="+uid;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        int nuber = Integer.parseInt(response);
                        ///if query user result 1 / up to 1 then found username
                        if(nuber>0){
                            username_id.setText("");
                            username_id.setError("Already Used");
                            AlertDialog alertDialog = new AlertDialog.Builder(Signup_from.this)
                                    .setTitle("NEED")
                                    .setMessage("This Username already uesd!")
                                    .show();
                            lottieAnimationView.setVisibility(View.GONE);
                        }else {


                            ////////////verification gmail and ad data
                            Random random = new Random();
                            otp_number = random.nextInt(999999);
                            gmail_verification(sgmail,otp_number);
                            ////////////////////



                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }//.........username_query..........

    /////////////////////////////////////////////////

    public void gmail_verification(String gmail, int gmail_body){
        String url = "https://mdnahidhossen.com/need/gmail_v/wellcome.php?gmail_id="+gmail+"&body_text="+gmail_body;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        otp_chack();
                        lottieAnimationView.setVisibility(View.GONE);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);


    }//..............gmail_verification............
    public void otp_chack(){

        Dialog dialog = new Dialog(Signup_from.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.otpchacklayout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.show();

        TextView ok_btu = dialog.findViewById(R.id.otp_ok);
        TextView cancel_btn = dialog.findViewById(R.id.otp_cancel);
        EditText otp_edit = dialog.findViewById(R.id.otp_edit);

        ok_btu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sotp = otp_edit.getText().toString();

                try {
                    ///////////////////////
                    if (sotp.length()!=0) {
                        ///////verify
                        int iotp = Integer.parseInt(sotp);

                        if (otp_number == iotp ){
                            Toast.makeText(Signup_from.this,"sucessfully ",Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            user_info_add();
                        } else {
                            otp_edit.setError("No Match");

                        }
                    }else {
                        otp_edit.setError("type otp");

                    }
                    ////////////////////////

                }catch (Exception e){

                }




            }
        });

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


    }

    public void user_info_add(){

        uid = username_id.getText().toString();
        sfirst_name = first_name.getText().toString();
        slast_name = last_name.getText().toString();
        sbirthday = birthday.getText().toString();
        sgender = gender.getText().toString();
        sgmail = gmail.getText().toString();
        spassword = password.getText().toString();
        sgender = spinner.getSelectedItem().toString();

        try {
            spassword = URLEncoder.encode(spassword,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }




            String url = "https://mdnahidhossen.com/need/user_info.php?Username="+uid+" &First_name="+sfirst_name+" &Last_name="+slast_name+"" +
                    " &Birthday="+sbirthday+" &gmail="+sgmail+" &Password_text="+spassword+ " &gen="+sgender;
//            String url = "https://mdnahidhossen.com/need/user_info.php";

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
                            lottieAnimationView.setVisibility(View.GONE);

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(Signup_from.this,"fale ",Toast.LENGTH_SHORT).show();


                }
            });
            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(stringRequest);


    }//....user_info_add..........


}