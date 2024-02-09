package com.appscraftbd.needasocal.fragment;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appscraftbd.needasocal.GetDataApi.GetDataFor_Home;
import com.appscraftbd.needasocal.R;
import com.appscraftbd.needasocal.RECYCLER_VIEW.Recycle_view_loading;
import com.appscraftbd.needasocal.SQLite_data.SQL_LITE;


public class GlobalFragment extends Fragment {

    RecyclerView recyclerView;
    public static SwipeRefreshLayout swipeRefreshLayout;



    @SuppressLint({"MissingInflatedId", "ResourceAsColor"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_global, container, false);

        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.white));
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(R.color.black);




        recyclerView = view.findViewById(R.id.recycleglobal);

        Recycle_view_loading globalRecycleView = new Recycle_view_loading();
        globalRecycleView.recycle_work(getContext(),recyclerView);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Handle the refresh action (e.g., fetch new data from a server)
                getRecycleCall();


            }
        });


        /////////////////////////////////////
        getRecycleCall();
        ////////////////////////////////////



        return view;
    }

    public  void getRecycleCall(){



        SQL_LITE sqlLite = new SQL_LITE(getContext());
        Cursor cursor1 = sqlLite.data_result();

        while (cursor1.moveToNext()){
            String user = cursor1.getString(1);
            String pass = cursor1.getString(2);

            String url = "https://mdnahidhossen.com/need/getUpoloadPost.php?uid="+user+"&pass="+pass;
            GetDataFor_Home apiCall = new GetDataFor_Home(url,getContext(),recyclerView,swipeRefreshLayout);
            break;
        }

    }

}