package com.example.retrofit;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.HolderViewModel> {

    final Context context;
    List<Post> postList;

     RecyclerViewAdapter(Context context, List<Post> postList) {
        this.context = context;
        this.postList = postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;

    }


    @NonNull
    @Override
    public HolderViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
        return new HolderViewModel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderViewModel holder, int position) {

        holder.userTV.setText(postList.get(position).getUserId());
        holder.titleTV.setText(postList.get(position).getTitle());

        holder.bodyTV.setText(postList.get(position).getBody());

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class HolderViewModel extends RecyclerView.ViewHolder {
        final TextView userTV;
        final TextView titleTV;
        final TextView bodyTV;
        public HolderViewModel(@NonNull View itemView) {

            super(itemView);
            userTV=itemView.findViewById(R.id.userId_tv);
            titleTV=itemView.findViewById(R.id.title_tv);
            bodyTV=itemView.findViewById(R.id.body_tv);

        }
    }

}
