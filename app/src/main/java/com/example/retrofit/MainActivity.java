package com.example.retrofit;



import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
RecyclerView recyclerView;
    List<Post> postList;
RecyclerViewAdapter recyclerAdapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    recyclerView=findViewById(R.id.recycler);
        recyclerView = findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        postList=new ArrayList<>();
        recyclerAdapter = new RecyclerViewAdapter(getApplicationContext(),postList);
        recyclerView.setAdapter(recyclerAdapter);
        if (postList!=null){
            Log.d(TAG, "onCreateViewHolder:sucsses ");

        }
        else
            Log.d(TAG, "onCreateViewHolder:failled ");



        contentApi();



/*
        Call<List<Post>> call=apiInterface.getPost(1);

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                Post_titleRetrofit.setText(response.body().get(0).getTitle());
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Post_titleRetrofit.setText(t.getMessage());
            }
        });











        /*
        HashMap<Object,Object>map=new HashMap<>();
        map.put("title","Soad");
        map.put("body","hi is my post");
        map.put("userId",7);

        Call<Post>call=apiInterface.storePost(map);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Post_titleRetrofit.setText(response.body().getTitle());

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Post_titleRetrofit.setText(t.getMessage());

            }
        });

        Call<List<Post>> call=apiInterface.getPost(1);

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                Post_titleRetrofit.setText(response.body().get(0).getTitle());
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Post_titleRetrofit.setText(t.getMessage());
            }
        });

         */
       }
    public void contentApi() {
        APIInterface apiService = ApiClient.getClient().create(APIInterface.class);
        Call<List<Post>> call = apiService.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(@NonNull Call<List<Post>> call, @NonNull Response<List<Post>> response) {
                postList = response.body();
                runOnUiThread(new Runnable() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void run() {
                        recyclerAdapter.setPostList(postList);
                        recyclerAdapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void onFailure(@NonNull Call<List<Post>> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "fail", Toast.LENGTH_SHORT).show();
            }
        });
    }
}