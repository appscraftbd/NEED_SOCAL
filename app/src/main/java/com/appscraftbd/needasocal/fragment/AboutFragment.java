package com.appscraftbd.needasocal.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.appscraftbd.needasocal.Login.Login_from;
import com.appscraftbd.needasocal.R;
import com.appscraftbd.needasocal.SQLite_data.SQL_LITE;
import com.appscraftbd.needasocal.SQLite_data.User_InfoSQLite;


public class AboutFragment extends Fragment {

    LinearLayout logout;
    TextView username , fullname;
    String  susername , sfname ,slname;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_about, container, false);

        logout = view.findViewById(R.id.logout);
        username = view.findViewById(R.id.username);
        fullname = view.findViewById(R.id.full_name);

        User_InfoSQLite userInfoSQLite = new User_InfoSQLite(getContext());

        Cursor cursor = userInfoSQLite.getShowData();

        while (cursor.moveToNext()){
            susername = cursor.getString(1);
            sfname = cursor.getString(2);
            slname = cursor.getString(3);

        }

        fullname.setText(""+sfname+" "+slname);
        username.setText("@"+susername);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SQL_LITE sqlLite = new SQL_LITE(getContext());
                sqlLite.data_delete();
                Intent sign = new Intent(getContext(), Login_from.class);
                startActivity(sign);
            }
        });




        return view;
    }
}