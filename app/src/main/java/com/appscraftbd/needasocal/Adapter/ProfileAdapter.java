package com.appscraftbd.needasocal.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProfileAdapter extends RecyclerView.Adapter {

    Context context;

    public class UserInfo extends RecyclerView.ViewHolder{
        public UserInfo(@NonNull View itemView) {
            super(itemView);
        }
    }
    public class PostHolder extends RecyclerView.ViewHolder{

        public PostHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


}
