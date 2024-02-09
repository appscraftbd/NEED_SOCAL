package com.appscraftbd.needasocal.fragment;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appscraftbd.needasocal.R;
import com.appscraftbd.needasocal.SQLite_data.User_InfoSQLite;


public class ProfileFragment extends Fragment {


    TextView username , fullname, bio_info,work_info,education_info,link;
    String  susername , sfname, slname , sbio_info,swork_info,seducation_info,slink;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

//
//        username = view.findViewById(R.id.username);
//        fullname = view.findViewById(R.id.full_name);
//        bio_info = view.findViewById(R.id.bio_info);
//        work_info = view.findViewById(R.id.work_info);
//        education_info = view.findViewById(R.id.education_info);
//        link = view.findViewById(R.id.link);
//
//        User_InfoSQLite userInfoSQLite = new User_InfoSQLite(getContext());
//
//        Cursor cursor = userInfoSQLite.getShowData();
//
//        while (cursor.moveToNext()){
//             susername = cursor.getString(1);
//             sfname = cursor.getString(2);
//             slname = cursor.getString(3);
//
//             sbio_info = cursor.getString(5);
//             seducation_info = cursor.getString(6);
//             swork_info = cursor.getString(7);
//             slink = cursor.getString(8);
//
//        }
//
//        fullname.setText(""+sfname+" "+slname);
//        username.setText("@"+susername);
//
//        bio_info.setText(""+sbio_info);
//        education_info.setText(""+seducation_info);
//        work_info.setText(""+swork_info);
//        link.setText(""+slink);


        return view;
    }
}