package com.appscraftbd.needasocal.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.appscraftbd.needasocal.Login.Login_from;
import com.appscraftbd.needasocal.R;
import com.appscraftbd.needasocal.SQL_LITE;


public class AboutFragment extends Fragment {

    LinearLayout logout;



    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_about, container, false);

        logout = view.findViewById(R.id.logout);

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